import java.util.ArrayList;

public class Deck {
    static int numdecks = 0;

    //4 suites, 13 a suit, 52 deck
    //Spades, Hearts, Clubs, Diamonds
    String[][] deck = {
            {"AC","2C","3C","4C","5C","6C","7C","8C","9C","10C"
                    ,"JC","QC","KC"},
            {"AD","2D","3D","4D","5D","6D","7D","8D","9D","10D"
                    ,"JD","QD","KD"},
            {"AH","2H","3H","4H","5H","6H","7H","8H","9H","10H"
                    ,"JH","QH","KH"},
            {"AS","2S","3S","4S","5S","6S","7S","8S","9S","10S"
                    ,"JS","QS","KS"},
    };
    ArrayList<String> usedCards = new ArrayList<>();

    public Deck(){
        numdecks++;
    }

    public String[][] getDeck(){
        return deck;
    }
    public void setDeck(String[][] deck){
        this.deck = deck;
    }

    public String getCard(int row, int col){
        return deck[row][col];
    }

    public String dealCard(){
        String card;
        if(usedCards.size() >= 52){
            card = "out of cards";
        }else {
            do {
                card = getCard(randRow(), randCol());
            } while (usedCards.contains(card));
        }
        usedCards.add(card);
        return card;
    }

    public int randRow() {
        return (int) (Math.random() * 4);
    }
    public int randCol(){
        return (int)(Math.random() * 13);
    }

    public void shuffle(){
        usedCards = new ArrayList<>();
    }
}
