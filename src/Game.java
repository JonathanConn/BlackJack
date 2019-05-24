import javafx.application.Application;

import java.util.ArrayList;
public class Game {
    static boolean gameRunning = true;
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static Deck deck = new Deck();
    Dealer dealer = new Dealer();

    fxDriver gui = new fxDriver();

    public Game(){

    }

    public Game(int numPlayers){
        for(int i = 0; i < numPlayers; i++){
            playersList.add(new Player());
        }
        Application.launch(gui.getClass());
    }

    public Game(int numPlayers, ArrayList<String> pNames){
        for(int i = 0; i < numPlayers; i++){
            playersList.add(new Player(pNames.get(i)));
        }
    }

    public ArrayList<Player> getPlayersList(){
        return playersList;
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
            do {

            }while(!complete);
        }
    }

    public void nextDealerTurn(){

    }

    public void findWinner(){
        boolean dealerwin = true;

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
