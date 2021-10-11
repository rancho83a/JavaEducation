package cardSuit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        for (CardRank item : CardRank.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", item.ordinal(), item);

        }
    }
}
