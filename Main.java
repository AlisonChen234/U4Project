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
        int rank =0;

        ArrayList<String> cardData = getFileData("src/Data");


        //convert everything to a num so jack =11, Queen=12, king=13, and Ace=14 and rest is the rest 2=2, 3=3, etc.
        // Once you convert it then check for the smallest one and give that a rank of 1 and then so fourth until ypu get the largets bid value
        // loop it for each time as maybe like data points have 2 then check for the second num
        //then you can keep on like doing it till no 2s, the go tp three
        // create two arrays one for the cards
//        // you multiply the rank and the bid value
//        find total bid value


        for (int i = 0; i < cardData.size(); i++) {
            String line = cardData.get(i);
            int bar = line.indexOf("|");
            String cards = line.substring(0, bar) + ",";

            String[] card = new String[5];
            int position = 0;
            for (int a = 0; a < 5; a++) {
                int comma = cards.indexOf(",", position);
                card[a] = cards.substring(position, comma);
                position = comma + 1;
            }

            int [] bidValue = new int[3];

            int totalvalue = totalValue+ rank* bidValue.Of[x]

                if (card.equals("Ace")) { return 14; }
                else if (card.equals("King")) { return 13; }
                else if (card.equals("Queen")) { return 12; }
                else if (card.equals("Jack")) { return 11; }
                else if (card.equals("10")) { return 10; }
                else if (card.equals("9")) { return 9; }
                else if (card.equals("8")) { return 8; }
                else if (card.equals("7")) { return 7; }
                else if (card.equals("6")) { return 6; }
                else if (card.equals("5")) { return 5; }
                else if (card.equals("4")) { return 4; }
                else if (card.equals("3")) { return 3; }
                return 2;
            }



            int numFives = 0;
            int numFours = 0;
            int numThrees = 0;
            int numPairs = 0;


            // HAVE TO CHANGE EVERYTHING FROM /EQUALS  TO ==

            int count1 = 1;
            if (card[0]==(card[1])) { count1++; }
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
                if (card[3].equals(card[4])) {
                    count4++;
                }
                if (count4 == 2) {
                    numPairs++;
                }
            }


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