import java.util.ArrayList;

public class Dealer{

    ArrayList<String> dealerCards = new ArrayList<String>();
    public Dealer(){

    }
    public ArrayList getCards(){
        return dealerCards;
    }

    public void setCards(ArrayList<String> cards){
        this.dealerCards = cards;
    }

    public void addCard(String card){
        dealerCards.add(card);
    }

    public void resetCards(){
        dealerCards = new ArrayList<String>();
    }

    public void startDeal(){
        Deck curDeck = new Deck();

    }
}
