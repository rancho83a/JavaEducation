package cardsWithPower;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cardRank = scan.nextLine();
        String cardSuit = scan.nextLine();

      Card card = new Card(CardRank.valueOf(cardRank),CardSuit.valueOf(cardSuit));
        System.out.println(card.toString());

    }
}
