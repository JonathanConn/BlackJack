import java.util.ArrayList;

public class Player {
    String name = "";
    ArrayList<String> playerCards = new ArrayList<String>();

    public Player(){
        setName("???");
    }

    public Player(String name){
        setName(name);
    }
    public ArrayList getCards(){
        return playerCards;
    }
    public void setCards(ArrayList<String> cards){
        this.playerCards = cards;
    }
    public void addCard(String card){
        playerCards.add(card);
    }
    public void resetCards(){
        playerCards = new ArrayList<String>();
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void printCards(){
        System.out.println(this.getName() + ": " + playerCards.toString());
    }

}
