package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day0054 {

    static String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    static int[] cardsNoms = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public static void main(String[] args) {
        solution(1000000);
    }

    // How many hands does Player 1 win
    static long solution(long limit) {
        List<List<String>> parsedData = parseInput(inputLines("files/problem_0054.txt"));
        for(List<String> parsedRow : parsedData) {
            PlayerCards playerCards = splitToPlayers(parsedRow);
            List<CardOnHand> cardOnHands = calcNominal(playerCards.cards2);
            long calcSumma = calcSumma(cardOnHands);
            System.out.println(cardOnHands);
            System.out.println(calcSumma);
        }
        return 0;
    }

    static long calcSumma(List<CardOnHand> cardOnHands) {
        long summa = 0L;
        outer:
        for(int i = 0; i < cardOnHands.size(); i++) {
            if(cardOnHands.get(i).category != null) {
                return (long) (cardOnHands.get(i).sum * Math.pow(10, cardOnHands.get(i).category));
            } else {
//                if(cardOnHands.get(i).cnt == 4) {
//                    summa += (cardOnHands.get(i).sum * Math.pow(10, 8));
//                } else if () {
//
//                }
                switch (cardOnHands.get(i).cnt) {
                    case 4: summa += (cardOnHands.get(i).sum * Math.pow(10, 8)); break;
//                    case 3:
//                        if(cardOnHands.size() == 2) {
//                            summa += (cardOnHands.get(i).sum * Math.pow(10, 3));
//                        } else {
//                            summa += (cardOnHands.get(i).sum * Math.pow(10, 3));
//                        }
//                        break;
//                    case 2: summa += (cardOnHands.get(i).sum * Math.pow(10, 2)); break;
                    case 1: summa += (cardOnHands.get(i).sum * Math.pow(10, 1)); break;
                    default:
                }
            }
        }
        return summa;
    }

    static List<CardOnHand> calcNominal(List<String> playerCards) {
        Map<Character, Integer> cardSuit = new HashMap<>();
        Map<Integer, Integer> cardNomMap = new HashMap<>();
        List<Integer> vals = new ArrayList<>();
        int[] retInt = new int[2];
        for (String card : playerCards) {
            char suitCard = card.charAt(card.length() - 1);
            String intCard = card.substring(0, card.length() - 1);
            cardSuit.put(suitCard, cardSuit.getOrDefault(suitCard, 0) + 1);
            int cdNomInt = transformToVal(intCard);
            cardNomMap.put(cdNomInt, cardNomMap.getOrDefault(cdNomInt, 0) + 1);
            vals.add(cdNomInt);
        }
        //System.out.println(cardSuit);
        //System.out.println();
        //System.out.println(cardNomMap);
        int howManyDiffNoms = cardNomMap.size();

        // same suit
        if (isSameSuit(cardSuit)) {
            int summa = vals.stream().reduce(Integer::sum).get();
            // Royal flush
            if (summa == (11 + 12 + 13 + 14)) {
                return List.of(new CardOnHand(howManyDiffNoms, summa, true, 10));
            } else {
                boolean ordered = isOrdered(vals);
                // Straight Flush
                if (ordered) {
                    return List.of(new CardOnHand(howManyDiffNoms, summa, true, 9));
                } else {
                    //Flush
                    return List.of(new CardOnHand(howManyDiffNoms, summa, false, 6));
                }
            }
        } else {
            List<CardOnHand> cardOnHands = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : cardNomMap.entrySet()) {
                int summa = vals.stream().filter(p -> p.equals(entry.getKey())).reduce(Integer::sum).get();
                boolean isConsequent = entry.getValue() == 5 && isOrdered(vals);
                CardOnHand cardOnHand = new CardOnHand(entry.getValue(), summa, isConsequent,
                        isConsequent ? 5 : null);
                cardOnHands.add(cardOnHand);
            }
            return cardOnHands;
        }
    }


    static boolean isOrdered(List<Integer> vals) {
        List<Integer> sortedCards = vals.stream().sorted().toList();
        boolean ordered = true;
        for (int i = 1; i < sortedCards.size(); i++) {
            int diff = Math.abs(sortedCards.get(i) - sortedCards.get(i - 1));
            if (diff != 1) {
                ordered = false;
                return false;
            }
        }
        return true;
    }
    static boolean isSameSuit(Map<Character, Integer> cardSuit) {
        return (cardSuit.size() == 1);
    }

    static int transformToVal(String cardType) {
        for(int i = 0; i < cards.length; i++) {
            if(cards[i].equals(cardType)) {
                return cardsNoms[i];
            }
        }
        return 0;
    }

    static List<List<String>> parseInput(String str) {
        List<List<String>> list = new ArrayList<>();
        for(String line : str.split("\n")) {
            //PlayerCards cards;
            String[] split = line.split(" ");
            List<String> player = new ArrayList<>();

            for(int i = 0; i < split.length; i++) {
                player.add(split[i]);
            }
            list.add(player);
        }
        //System.out.println(list);
        return list;
    }

    public static String inputLines(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }

    static PlayerCards splitToPlayers(List<String> cards) {
        List<String> p1 = new ArrayList<>();
        List<String> p2 = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++) {
            if(i < 5) {
                p1.add(cards.get(i));
            } else {
                p2.add(cards.get(i));
            }
        }
        return new PlayerCards(p1, p2);
    }

    record PlayerCards (List<String> cards1, List<String> cards2){};

    record CardOnHand (int cnt, int sum, boolean ordered, Integer category) {}
}
