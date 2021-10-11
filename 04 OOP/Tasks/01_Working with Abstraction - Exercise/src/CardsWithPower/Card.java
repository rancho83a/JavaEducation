package CardsWithPower;

public class Card {
    private CardSuit suit;
    private CardRank rank;
    private int power;


    Card( CardRank rank, CardSuit suit){
        this.suit = suit;
        this.rank=rank;
        this.power = setPower();
    }

    private int setPower(){
        return this.suit.getPower()+this.rank.getPower();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d",this.rank, this.suit, this.power);
    }
}
