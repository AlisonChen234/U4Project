import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int fiveOfKind = 0;
        int fullHouse = 0;
        int fourOfKind = 0;
        int threeOfKind = 0;
        int twoPairs = 0;
        int onePair = 0;
        int highCards = 0;

        ArrayList<String> cardData = getFileData("src/Data");
        int totalBidValue = 0;

        for (int i = 0; i < cardData.size(); i++) {
            String line = cardData.get(i);


            int bar = line.indexOf("|");
            String cards = line.substring(0, bar) + ",";
            int bid = Integer.parseInt(line.substring(bar + 1));

            String[] card = new String[5];
            int position = 0;
            for (int a = 0; a < 5; a++) {
                int comma = cards.indexOf(",", position);
                card[a] = cards.substring(position, comma);
                position = comma + 1;
            }

            int currentStrength = calculateStrength(card);

            if (currentStrength == 7) fiveOfKind++;
            else if (currentStrength == 6) fourOfKind++;
            else if (currentStrength == 5) fullHouse++;
            else if (currentStrength == 4) threeOfKind++;
            else if (currentStrength == 3) twoPairs++;
            else if (currentStrength == 2) onePair++;
            else highCards++;


            int rank = 1;
            for (int j = 0; j < cardData.size(); j++) {
                if (i != j) {
                    String otherLine = cardData.get(j);
                    int otherBar = otherLine.indexOf("|");
                    String otherCardsStr = otherLine.substring(0, otherBar) + ",";

                    String[] otherCard = new String[5];
                    int otherPos = 0;
                    for (int b = 0; b < 5; b++) {
                        int c = otherCardsStr.indexOf(",", otherPos);
                        otherCard[b] = otherCardsStr.substring(otherPos, c);
                        otherPos = c + 1;
                    }


                    if (isStronger(card, otherCard)) {
                        rank++;
                    }
                }
            }
            totalBidValue += bid * rank;
        }

        System.out.println("Number of five of a kind hands: " + fiveOfKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourOfKind);
        System.out.println("Number of three of a kind hands: " + threeOfKind);
        System.out.println("Number of two pair hands: " + twoPairs);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCards);
        System.out.println("Total Bid Value: " + totalBidValue);
    }


    public static boolean isStronger(String[] hand1, String[] hand2) {
        int s1 = calculateStrength(hand1);
        int s2 = calculateStrength(hand2);

        if (s1 != s2) return s1 > s2;


        for (int i = 0; i < 5; i++) {
            int v1 = getVal(hand1[i]);
            int v2 = getVal(hand2[i]);
            if (v1 != v2) return v1 > v2;
        }
        return false;
    }

    public static int getVal(String card) {
        if (card.equals("Ace")) return 14;
        if (card.equals("King")) return 13;
        if (card.equals("Queen")) return 12;
        if (card.equals("Jack")) return 11;
        if (card.equals("10")) return 10;
        if (card.equals("9")) return 9;
        if (card.equals("8")) return 8;
        if (card.equals("7")) return 7;
        if (card.equals("6")) return 6;
        if (card.equals("5")) return 5;
        if (card.equals("4")) return 4;
        if (card.equals("3")) return 3;
        return 2;
    }

    public static int calculateStrength(String[] card) {
        int numFives = 0;
        int numFours = 0;
        int numThrees = 0;
        int numPairs = 0;

        int count1 = 1;
        if (card[0].equals(card[1])) { count1++; }
        if (card[0].equals(card[2])) { count1++; }
        if (card[0].equals(card[3])) { count1++; }
        if (card[0].equals(card[4])) { count1++; }
        if (count1 == 5) { numFives++; }
        if (count1 == 4) { numFours++; }
        if (count1 == 3) { numThrees++; }
        if (count1 == 2) { numPairs++; }

        if (!card[1].equals(card[0])) {
            int count2 = 1;
            if (card[1].equals(card[2])) { count2++; }
            if (card[1].equals(card[3])) { count2++; }
            if (card[1].equals(card[4])) { count2++; }
            if (count2 == 4) { numFours++; }
            if (count2 == 3) { numThrees++; }
            if (count2 == 2) { numPairs++; }
        }

        if (!card[2].equals(card[0]) && !card[2].equals(card[1])) {
            int count3 = 1;
            if (card[2].equals(card[3])) { count3++; }
            if (card[2].equals(card[4])) { count3++; }
            if (count3 == 3) { numThrees++; }
            if (count3 == 2) { numPairs++; }
        }

        if (!card[3].equals(card[0]) && !card[3].equals(card[1]) && !card[3].equals(card[2])) {
            int count4 = 1;
            if (card[3].equals(card[4])) { count4++; }
            if (count4 == 2) { numPairs++; }
        }

        if (numFives == 1) return 7;
        if (numFours == 1) return 6;
        if (numThrees == 1 && numPairs == 1) return 5;
        if (numThrees == 1) return 4;
        if (numPairs == 2) return 3;
        if (numPairs == 1) return 2;
        return 1;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals("")) {
                    fileData.add(line);
                }
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }
}