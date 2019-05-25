import javafx.application.Application;

import java.util.ArrayList;
public class Game{
    static boolean gameRunning = true;
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static Deck deck = new Deck();
    Dealer dealer = new Dealer();

    //fxDriver gui = new fxDriver();

    private String tempCard = "";

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
/*
    public void launchGui(){
        Application.launch(gui.getClass());

    }
*/
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
/*        tempCard = deck.dealCard();
        dealer.addCard(tempCard);

        tempCard = deck.dealCard();
        dealer.addCard(tempCard);

        for(Player p : playersList){
            tempCard = deck.dealCard();
            p.addCard(tempCard);
            gui.addPlayerCards(tempCard);

            tempCard = deck.dealCard();
            p.addCard(tempCard);
            gui.addPlayerCards(tempCard);
*/
            System.out.println("Cards delt");

     //   }
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
