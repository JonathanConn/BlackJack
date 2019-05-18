import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    static boolean gameRunning = true;
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static Deck deck = new Deck();
    Dealer dealer = new Dealer();

    Scanner scan = new Scanner(System.in);

    public Game(){

    }

    public Game(int numPlayers){
        for(int i = 0; i < numPlayers; i++){
            playersList.add(new Player());
        }
    }

    public Game(int numPlayers, ArrayList<String> pNames){
        for(int i = 0; i < numPlayers; i++){
            playersList.add(new Player(pNames.get(i)));
        }
    }

    public void removePlayer(Player name){
        try {
            if (playersList.contains(name)) {
                playersList.remove(name);
            }
        }catch (Exception e){
            System.out.println("failed to remove player \n" + e);
        }
    }

    public void addPlayer(Player name){
        try{
            playersList.add(name);
        }catch (Exception e){
            System.out.println("failed to add player \n" + e);
        }
    }

    public int getNumPlayers(){
        return playersList.size();
    }

    public boolean isGameRunning(){
        return gameRunning;
    }

    public void deal(){
        dealer.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        dealer.printSneakCard();

        for(Player p : playersList){
            p.addCard(deck.dealCard());
            p.addCard(deck.dealCard());
            p.printCards();
        }
    }
    public void nextPlayerTurn(){
        for(Player p : playersList){
            boolean complete = false;
            p.printCards();
            do {
                System.out.println(" hit or check");
                String dec = scan.nextLine();

                if (dec.toLowerCase().equals("hit")) {
                    p.addCard(deck.dealCard());
                    p.printCards();
                } else if (dec.toLowerCase().equals("check")) {
                    complete = true;
                } else {
                    System.out.println("bad input try again");
                }

                if(p.getTotal() > 21){
                    System.out.println("Bust\n");
                    p.busted();
                    complete = true;
                }

            }while(!complete);
        }
    }

    public void nextDealerTurn(){
        dealer.printSneakCard();
        while(dealer.logicHit()){
            System.out.println("Dealer hits: ");
            dealer.addCard(deck.dealCard());
            dealer.printSneakCard();
        }
        System.out.println("Dealer Checks");
    }

    public void findWinner(){
        boolean dealerwin = true;

        dealer.printCards();

        for(Player p : playersList){
            if(p.getTotal() > dealer.getTotal() && p.bustStatus() == false){
                System.out.println(p.getName() + " wins!");
                dealerwin = false;
            }
        }

        if(dealerwin){
            System.out.println("Dealer wins");
        }

    }

}
