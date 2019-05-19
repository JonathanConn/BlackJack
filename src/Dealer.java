import java.util.ArrayList;

public class Dealer{

    ArrayList<String> dealerCards = new ArrayList<String>();
    int total;

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
        updateTotal();
    }

    public void printSneakCard(){
        String temp = "";
        for(int i = 1; i < dealerCards.size(); i++){
            temp += dealerCards.get(i) + ",";
        }
        System.out.println("Dealer: [X," + temp + "] ");
    }

    public void printCards(){
        System.out.println("Dealer: " + dealerCards.toString() + " = " + this.getTotal());
    }

    public void updateTotal(){
        total = 0;
        for(String s : dealerCards){
            String temp = s.substring(0,1);
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

    public boolean logicHit(){
        if(this.getTotal() <= 17){
            return true;
        }
        return false;
    }
}
