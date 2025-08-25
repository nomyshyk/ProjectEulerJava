package kg.euler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Day0054 {

    static String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    static int[] cardsNoms = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public static void main(String[] args) {
        solution();
    }

    // How many hands does Player 1 win
    static long solution() {
        List<List<String>> parsedData = parseInput(inputLines("files/problem_0054.txt"));
        int winsOfP1 = 0;
        for(List<String> parsedRow : parsedData) {
            PlayerCards playerCards = splitToPlayers(parsedRow);
            CardOnHand cardOnHands1 = calcNominal(playerCards.cards1);
            CardOnHand cardOnHands2 = calcNominal(playerCards.cards2);
            int winnerId = cardWinner(cardOnHands1, cardOnHands2);
            System.out.println("winner is player " + winnerId + "\n");
            if(winnerId == 1) {
                winsOfP1++;
            }
        }
        System.out.println("Result is " + winsOfP1);
        return winsOfP1;
    }

    // 1 - p1, 2 - p2
    static int cardWinner(CardOnHand p1, CardOnHand p2) {
        System.out.print("p1 = ");
        System.out.println(p1);
        System.out.print("p2 = ");
        System.out.print(p2);
        System.out.println();
        int ordP1 = p1.category.ordinal();
        int ordP2 = p2.category.ordinal();

        if(ordP1 < ordP2) {
            return 1;
        } else if (ordP1 > ordP2) {
            return 2;
        }
        // check biggest card
        for (int i = 0; i < p1.cards.size(); i++) {
            if(p1.cards.get(i) > p2.cards.get(i)) {
                return 1;
            } else if (p1.cards.get(i) < p2.cards.get(i)) {
                return 2;
            }
        }
        return 0;
    }

    static CardOnHand calcNominal(List<String> playerCards) {
        Map<Character, Integer> cardSuit = new HashMap<>();
        Map<Integer, Integer> cardNomMap = new HashMap<>();
        Map<Integer, List<Integer>> nominalCountListMap = new HashMap<>();
        List<Integer> vals = new ArrayList<>();
        //int[] retInt = new int[2];
        for (String card : playerCards) {
            char suitCard = card.charAt(card.length() - 1);
            String intCard = card.substring(0, card.length() - 1);
            cardSuit.put(suitCard, cardSuit.getOrDefault(suitCard, 0) + 1);
            int cdNomInt = transformToVal(intCard);
            cardNomMap.put(cdNomInt, cardNomMap.getOrDefault(cdNomInt, 0) + 1);

            List<Integer> tempArr = nominalCountListMap.getOrDefault(cdNomInt, new ArrayList<Integer>());
            tempArr.add(cdNomInt);
            nominalCountListMap.put(cdNomInt, nominalCountListMap.getOrDefault(cdNomInt, tempArr));
            vals.add(cdNomInt);
        }

        //Getting Pairs
        int[] pairs = new int[2];
        int idx = 0;

        List<Integer> orderedCards = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> entry : nominalCountListMap.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> valList = entry.getValue();
            orderedCards.add(key * (int)Math.pow(10, valList.size()-1));
        }
        orderedCards.sort((x, y) -> Integer.compare(y, x));

        PriorityQueue<Integer> orderedRepeatVals = new PriorityQueue<>(cardNomMap.values());
        while (!orderedRepeatVals.isEmpty()) {
            int firstVal = orderedRepeatVals.poll();
            if(firstVal >= 2) {
                pairs[idx] = firstVal;
                idx++;
            }
        }

        // same suit
        if (isSameSuit(cardSuit)) {
            int summa = vals.stream().reduce(Integer::sum).get();
            // Royal flush
            if (summa == (10 + 11 + 12 + 13 + 14)) {
                return new CardOnHand(CardComboNames.ROYAL_FLUSH, orderedCards, playerCards);
            } else {
                boolean ordered = isOrdered(vals);
                // Straight Flush
                if (ordered) {
                    return new CardOnHand(CardComboNames.STRAIGHT_FLUSH, orderedCards, playerCards);
                } else {
                    //Flush
                    return new CardOnHand(CardComboNames.FLUSH, orderedCards, playerCards);
                }
            }
        } else {
            // not same suit
            boolean ordered = isOrdered(orderedCards);
            if(ordered) {
                return new CardOnHand(CardComboNames.STRAIGHT, orderedCards, playerCards);
            }

            if(pairs[0] == 4) {
                return new CardOnHand(CardComboNames.FOUR_OF_A_KIND, orderedCards, playerCards);
            }
            if(pairs[0] == 3 && pairs[1] == 2) {
                return new CardOnHand(CardComboNames.FULL_HOUSE, orderedCards, playerCards);
            }
            if(pairs[0] == 3) {
                return new CardOnHand(CardComboNames.THREE_OF_A_KIND, orderedCards, playerCards);
            }
            if(pairs[0] == 2 && pairs[1] == 2) {
                return new CardOnHand(CardComboNames.TWO_PAIRS, orderedCards, playerCards);
            }
            if(pairs[0] == 2) {
                return new CardOnHand(CardComboNames.ONE_PAIR, orderedCards, playerCards);
            }
            return new CardOnHand(CardComboNames.HIGH_CARD, orderedCards, playerCards);
        }
    }

    static boolean isOrdered(List<Integer> vals) {
        List<Integer> sortedCards = vals.stream().sorted().toList();
        for (int i = 1; i < sortedCards.size(); i++) {
            int diff = Math.abs(sortedCards.get(i) - sortedCards.get(i - 1));
            if (diff != 1) {
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

    record CardOnHand (CardComboNames category, List<Integer> cards, List<String> initCards) {}

    enum CardComboNames {
        ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH,
        STRAIGHT, THREE_OF_A_KIND, TWO_PAIRS, ONE_PAIR, HIGH_CARD
    }
}
