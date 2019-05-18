import java.util.ArrayList;

public class Player {
    String name = "";
    ArrayList<String> playerCards = new ArrayList<String>();
    int total = 0;

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
        updateTotal();
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
        System.out.println(this.getName() + ": " + playerCards.toString() + " = " + this.getTotal());
    }

    public void updateTotal(){
        total = 0;
        for(String s : playerCards){
           String temp = s.substring(1);
           if(temp.equals("K") || temp.equals("Q") || temp.equals("J") || temp.equals("10")){
               total += 10;
           }else if(!temp.equals("A")){
                total += Integer.parseInt(temp);
           }else{
               if(total >= 21 && temp.equals("A")){
                   total += 1;
               }
               if(total < 21 && temp.equals("A")){
                   total += 11;
               }
           }
        }
    }

    public int getTotal(){
        return total;
    }

}
