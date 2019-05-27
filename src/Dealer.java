import java.util.ArrayList;

public class Dealer extends Player{

    public Dealer(){

    }

    public boolean logicHit(){
        if(this.getTotal() <= 16){
            return true;
        }
        return false;
    }
}
