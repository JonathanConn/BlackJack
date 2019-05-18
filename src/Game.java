import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    static boolean gameRunning = true;
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static Deck deck = new Deck();

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
        for(Player p : playersList){
            p.addCard(deck.dealCard());
            p.addCard(deck.dealCard());
            p.printCards();
        }
    }
    public void nextTurn(){
        for(Player p : playersList){
            boolean complete = false;
            System.out.print(p.getName());
            do {
                System.out.println(" hit or check");
                String dec = scan.nextLine();

                if (dec.toLowerCase().equals("hit")) {
                    p.addCard(deck.dealCard());
                    complete = true;
                } else if (dec.toLowerCase().equals("check")) {
                    complete = true;
                } else {
                    System.out.println("bad input try again");
                }

            }while(!complete);
            p.printCards();
        }
    }

}
