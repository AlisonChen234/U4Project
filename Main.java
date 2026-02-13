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
        int wildJacks=0;

        ArrayList<String> cardData = getFileData("src/Data");
        int totalBidValue = 0;

        for (int i = 0; i < cardData.size(); i++) {
            String line = cardData.get(i);
            int bar = line.indexOf("|");
            String cards = line.substring(0, bar) + ",";
            int bidvalue = Integer.parseInt(line.substring(bar + 1));

            String[] card = new String[5];
            int position = 0;
            for (int a = 0; a < 5; a++) {
                int comma = cards.indexOf(",", position);
                card[a] = cards.substring(position, comma);
                position = comma + 1;
            }

            int handStrength = calculateStrength(card);

            if (handStrength == 7) { 
                fiveOfKind++; }
            else if (handStrength == 6) { 
                fourOfKind++; }
            else if (handStrength == 5) { 
                fullHouse++; }
            else if (handStrength == 4) { 
                threeOfKind++; }
            else if (handStrength == 3) {
                twoPairs++; }
            else if (handStrength == 2) { 
                onePair++; }
            else { 
                highCards++; }

            int rank = 1;
            for (int j = 0; j < cardData.size(); j++) {
                if (i != j) {
                    String line1 = cardData.get(j);
                    int bar1 = line1.indexOf("|");
                    String otherCardsStr = line1.substring(0, bar1) + ",";

                    String[] otherCard = new String[5];
                    int otherHands = 0;
                    for (int b = 0; b < 5; b++) {
                        int c = otherCardsStr.indexOf(",", otherHands);
                        otherCard[b] = otherCardsStr.substring(otherHands, c);
                        otherHands = c + 1;
                    }

                    if (compareHands(card, otherCard) == 1) {
                        rank++;
                    }
                }
            }
            totalBidValue += bidvalue * rank;
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

    public static int compareHands(String[] hand1, String[] hand2) {
        int result = 0;
        int str1 = calculateStrength(hand1);
        int str2 = calculateStrength(hand2);

        if (str1 > str2) {
            result = 1;
        } else if (str1< str2) {
            result = -1;
        } else {
            int i = 0;
            while (i < 5 && result == 0) {
                int val1 = getVal(hand1[i]);
                int val2 = getVal(hand2[i]);
                if (val1 > val2) {
                    result = 1;
                } else if (val1 < val2) {
                    result = -1;
                }
                i++;
            }
        }
        return result;
    }

    public static int getVal(String card) {
        int value = 2;
        if (card.equals("Ace")) { value = 14; }
        else if (card.equals("King")) { value = 13; }
        else if (card.equals("Queen")) { value = 12; }
        else if (card.equals("Jack")) { value = 11; }
        else if (card.equals("10")) { value = 10; }
        else if (card.equals("9")) { value = 9; }
        else if (card.equals("8")) { value = 8; }
        else if (card.equals("7")) { value = 7; }
        else if (card.equals("6")) { value = 6; }
        else if (card.equals("5")) { value = 5; }
        else if (card.equals("4")) { value = 4; }
        else if (card.equals("3")) { value = 3; }
        return value;
    }

    public static int calculateStrength(String[] card) {
        int numFives = 0;
        int numFours = 0;
        int numThrees = 0;
        int numPairs = 0;
        int finalStrength = 1;

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

        if (numFives == 1) { finalStrength = 7; }
        else if (numFours == 1) { finalStrength = 6; }
        else if (numThrees == 1 && numPairs == 1) { finalStrength = 5; }
        else if (numThrees == 1) { finalStrength = 4; }
        else if (numPairs == 2) { finalStrength = 3; }
        else if (numPairs == 1) { finalStrength = 2; }

        return finalStrength;
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
        } catch (FileNotFoundException e) {
        }
        return fileData;
    }
}
