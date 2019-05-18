import java.util.ArrayList;

public class Deck {
    static int numdecks = 0;

    //4 suites, 13 a suit, 52 deck
    //Spades, Hearts, Clubs, Diamonds
    String[][] deck = {
            {"CA","C2","C3","C4","C5","C6","C7","C8","C9","C10"
                    ,"CJ","CQ","CK"},
            {"DA","D2","D3","D4","D5","D6","D7","D8","D9","D10"
                    ,"DJ","DQ","DK"},
            {"HA","H2","H3","H4","H5","H6","H7","H8","H9","H10"
                    ,"HJ","HQ","HK"},
            {"SA","S2","S3","S4","S5","S6","S7","S8","S9","S10"
                    ,"SJ","SQ","SK"},
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
}
