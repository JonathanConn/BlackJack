import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Bob");

        Game curGame = new Game(2, names);

        /*
        while(curGame.isGameRunning()){
        }
        */

        curGame.deal();
        System.out.println("\n------------\n");
        curGame.nextPlayerTurn();
        System.out.println("\n------------\n");
        curGame.nextDealerTurn();
        System.out.println("\n------------\n");
        curGame.findWinner();
    }

}
