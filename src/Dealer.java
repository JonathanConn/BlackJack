import java.util.ArrayList;

public class Dealer extends Player{

    public Dealer(){

    }

    public boolean logicHit(){
        this.updateTotal();
        if (this.getTotal() > 21) {
            this.busted();
            return false;
        }
        if(this.getTotal() <= 16 && this.bustStatus() == false){
            return true;
        }
        return false;
    }
}
