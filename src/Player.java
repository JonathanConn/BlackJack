import java.util.ArrayList;

public class Player{
    String name = "";
    ArrayList<String> cards = new ArrayList<String>();
    int total = 0;

    boolean bust = false;

    public Player(){
        setName("???");
    }

    public Player(String name){
        setName(name);
    }
    public ArrayList getCards(){
        return cards;
    }
    public void setCards(ArrayList<String> cards){
        this.cards = cards;
    }
    public void addCard(String card){
        cards.add(card);
        updateTotal();
    }
    public void resetCards(){
        cards = new ArrayList<String>();
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void printCards(){
        System.out.println(this.getName() + ": " + cards.toString() + " = " + this.getTotal());
    }

    public void updateTotal(){
        int aceCount = 0;
        total = 0;
        for(String s : cards){
           String temp = s.substring(0,1);
           String tempTEN = s.substring(0,2);
           if(temp.equals("K") || temp.equals("Q") || temp.equals("J") || tempTEN.equals("10")){
               total += 10;
           }else if(!temp.equals("A")){
                total += Integer.parseInt(temp);
           }else if(temp.equals("A")){
               aceCount++;
           }
        }

        while(aceCount > 0) {
            int aceCheck = total + 11;
            if (aceCheck <= 21) {
                total += 11;
            } else {
                total += 1;
            }
            aceCount--;
        }

    }

    public int getTotal(){
        return total;
    }
    public void setTotal(int x){
        total = x;
    }

    public void busted(){
        bust = true;
    }
    public void setBust(boolean bust){
        this.bust = bust;
    }
    public boolean bustStatus(){
        return bust;
    }

}
