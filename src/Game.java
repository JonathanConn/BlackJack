import javafx.application.Application;

import java.util.ArrayList;
public class Game {
    static boolean gameRunning = true;
    static int curTurn = 0;
    static ArrayList<String> trackTurn = new ArrayList<String>(){
        {
            add("start");
            add("player");
            add("dealer");
            add("end");
        }
    };

    public Game(){

    }
    public void setTurn(String turn){
        if(!trackTurn.contains(turn.toLowerCase())){
            System.out.println("no turn option");
        }
        for(int i = 0; i < trackTurn.size(); i++){
            if(trackTurn.get(i).equalsIgnoreCase(turn)){
                curTurn = i;
            }
        }
    }

    public String getTurn(){
        return trackTurn.get(curTurn);
    }

    public void nextTurn(){
        curTurn++;
        if(curTurn >= trackTurn.size()){
            curTurn = 0;
        }
    }

}

