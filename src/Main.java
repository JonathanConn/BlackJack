import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Bob");
        names.add("Dave");

        Game curGame = new Game(2, names);

        /*
        while(curGame.isGameRunning()){
        }
        */

        curGame.deal();
        curGame.nextTurn();
    }

}
