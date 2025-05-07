package kg.euler;

public class Day0019 {

    public static void main(String[] args) {
        solution("01-01-1901", "31-12-2000");
    }

    // How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
    static long solution(String dateBeg, String dateEnd) {
        int[] dmyBeg = dmyArray(dateBeg);
        int[] dmyEnd = dmyArray(dateEnd);

        countSpecificDayOfAWeekInPeriod(7, dmyBeg, dmyEnd, 1);

        return 0L;
    }

    static int daysInMonths(int year, int month) {
        return switch (month) {
            case 2 -> daysInFeb(year);
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }

    static int[] dmyArray(String date) {
        String[] dateBegStr = date.split("-");
        return new int[] {Integer.parseInt(dateBegStr[0]),
                Integer.parseInt(dateBegStr[1]),
                Integer.parseInt(dateBegStr[2])};
    }

    // No local-date solution
    static int countSpecificDayOfAWeekInPeriod(int dayOfWeek, int[] dmyBeg, int[] dmyEnd, int dateCounted) {

        String aliasToCompare = "%04d-%02d-%02d";
        int plusStepsToStart = dayOfWeek - 1;
        int[] curDate = {1,1,1900};
        curDate[0] = curDate[0] + plusStepsToStart;
        String dateCurStr = String.format(aliasToCompare, curDate[2], curDate[1], curDate[0]);
        String dateMaxStr = String.format(aliasToCompare, dmyEnd[2], dmyEnd[1], dmyEnd[0]);
        String dateBegStr = String.format(aliasToCompare, dmyBeg[2], dmyBeg[1], dmyBeg[0]);
        int cntNeededDates = 0;
        while(dateCurStr.compareTo(dateMaxStr) <= 0) {
            if(curDate[0] == dateCounted && dateCurStr.compareTo(dateBegStr) > 0) {
                cntNeededDates++;
            }
            int dVal = curDate[0] + 7;
            int dMaxDays = daysInMonths(curDate[1], curDate[2]);
            if (dVal > dMaxDays) {
                curDate[0] = Math.abs(dMaxDays - dVal);
                if(curDate[1] == 12) {
                    curDate[1] = 1;
                    curDate[2] = curDate[2] + 1;
                } else {
                    curDate[1] = curDate[1] + 1;
                }
            } else {
                curDate[0] = dVal;
            }
            dateCurStr = String.format(aliasToCompare, curDate[2], curDate[1], curDate[0]);
        }
        System.out.println("the final result is " + cntNeededDates);
        return cntNeededDates;
    }

    static int daysInFeb(int year) {
        if(year % 400 == 0) {
            return 29;
        }

        if(year % 100 == 0) {
            return 28;
        }

        if(year % 4 == 0) {
            return 29;
        }
        else return 28;
    }
}