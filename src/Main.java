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

        for (int i = 0; i < cardData.size(); i++) {
            String line = cardData.get(i);
            int bar = line.indexOf("|");
            String cards = line.substring(0, bar) + ",";

            String[] hand = new String[5];
            int position = 0;
            for (int a = 0; a < 5; a++) {
                int comma = cards.indexOf(",", position);
                hand[a] = cards.substring(position, comma);
                position = comma + 1;
            }

            int numFives = 0;
            int numFours = 0;
            int numThrees = 0;
            int numPairs = 0;

            int count1 = 1;
            if (hand[0].equals(hand[1])) { count1++; }
            if (hand[0].equals(hand[2])) { count1++; }
            if (hand[0].equals(hand[3])) { count1++; }
            if (hand[0].equals(hand[4])) { count1++; }
            if (count1 == 5) { numFives++; }
            if (count1 == 4) { numFours++; }
            if (count1 == 3) { numThrees++; }
            if (count1 == 2) { numPairs++; }


            if (!hand[1].equals(hand[0])) {
                int count2 = 1;
                //if (hand[1].equals(hand[0])) { count2++; }
                if (hand[1].equals(hand[2])) { count2++; }
                if (hand[1].equals(hand[3])) { count2++; }
                if (hand[1].equals(hand[4])) { count2++; }
                //if (count2 == 5) { numFives++; }
                if (count2 == 4) { numFours++; }
                if (count2 == 3) { numThrees++; }
                if (count2 == 2) { numPairs++; }
            }


            if (!hand[2].equals(hand[0]) && !hand[2].equals(hand[1])) {
                int count3 = 1;
//                if (hand[2].equals(hand[0])) { count3++; }
//                if (hand[2].equals(hand[1])) { count3++; }
                if (hand[2].equals(hand[3])) { count3++; }
                if (hand[2].equals(hand[4])) { count3++; }
//                if (count3 == 5) { numFives++; }
//                if (count3 == 4) { numFours++; }
                if (count3 == 3) { numThrees++; }
                if (count3 == 2) { numPairs++; }
            }


            if (!hand[3].equals(hand[0]) && !hand[3].equals(hand[1]) && !hand[3].equals(hand[2])) {
                int count4 = 1;
//                if (hand[3].equals(hand[0])) { count4++; }
//                if (hand[3].equals(hand[1])) { count4++; }
//                if (hand[3].equals(hand[2])) { count4++; }
                if (hand[3].equals(hand[4])) { count4++; }
//                if (count4 == 5) { numFives++; }
//                if (count4 == 4) { numFours++; }
//                if (count4 == 3) { numThrees++; }
                if (count4 == 2) { numPairs++; }
            }


//            if (!hand[4].equals(hand[0]) && !hand[4].equals(hand[1]) && !hand[4].equals(hand[2]) && !hand[4].equals(hand[3])) {
//                int count5 = 1;
//                if (hand[4].equals(hand[0])) { count5++; }
//                if (hand[4].equals(hand[1])) { count5++; }
//                if (hand[4].equals(hand[2])) { count5++; }
//                if (hand[4].equals(hand[3])) { count5++; }
//                if (count5 == 5) { numFives++; }
//                if (count5 == 4) { numFours++; }
//                if (count5 == 3) { numThrees++; }
//                if (count5 == 2) { numPairs++; }
//            }


            if (numFives == 1) {
                fiveOfKind++;
            } else if (numFours == 1) {
                fourOfKind++;
            } else if (numThrees == 1 && numPairs == 1) {
                fullHouse++;
            } else if (numThrees == 1) {
                threeOfKind++;
            } else if (numPairs == 2) {
                twoPairs++;
            } else if (numPairs == 1) {
                onePair++;
            } else {
                highCards++;
            }
        }


        System.out.println("Number of five of a kind hands: " + fiveOfKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourOfKind);
        System.out.println("Number of three of a kind hands: " + threeOfKind);
        System.out.println("Number of two pair hands: " + twoPairs);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCards);
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

