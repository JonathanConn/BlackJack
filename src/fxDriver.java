import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class fxDriver extends Application {
    static ArrayList<Player> playersList = new ArrayList<Player>();

    static Deck deck = new Deck();
    Dealer dealer = new Dealer();
    private String tempCard = "";

    BorderPane tablePane = new BorderPane();
    BorderPane playerPane = new BorderPane();
    BorderPane dealerPane = new BorderPane();
    BorderPane cardPane = new BorderPane();

    HBox cardHbox = new HBox();
    HBox dealerHbox = new HBox();
    HBox playerHbox = new HBox();
    HBox buttonHbox = new HBox();

    VBox scoreVbox = new VBox();

    Image image = null;

    Text playerScore = new Text();
    Text dealerScore = new Text();
    Text currentTurn = new Text();

    Button dealButton = new Button("Start");
    Button hitButton = new Button("Hit");
    Button checkButton = new Button("Check");

    @Override
    public void start(Stage stage) {
        setHBoxStyle(cardHbox);
        setHBoxStyle(dealerHbox);
        setHBoxStyle(playerHbox);

        setVBoxStyle(scoreVbox);

        setTextStyle(playerScore);
        setTextStyle(dealerScore);
        setTextStyle(currentTurn);

        dealerPane.setCenter(dealerHbox);
        playerPane.setCenter(playerHbox);
        cardPane.setCenter(cardHbox);

        scoreVbox.getChildren().addAll(playerScore, dealerScore, currentTurn);

        tablePane.setTop(dealerPane);
        tablePane.setCenter(cardPane);
        tablePane.setBottom(playerPane);
        tablePane.setRight(scoreVbox);

        hitButton.setVisible(false);
        hitButton.setManaged(false);

        checkButton.setVisible(false);
        checkButton.setManaged(false);

        EventHandler<ActionEvent> dealEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                start();
            }
        };
        EventHandler<ActionEvent> hitEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                hit(playersList.get(0));
            }
        };
        EventHandler<ActionEvent> checkEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                check(playersList.get(0));
            }
        };

        dealButton.setOnAction(dealEvent);
        hitButton.setOnAction(hitEvent);
        checkButton.setOnAction(checkEvent);

        setHBoxStyle(buttonHbox);

        buttonHbox.getChildren().addAll(dealButton, hitButton, checkButton);

        tablePane.setCenter(buttonHbox);

        Scene scene = new Scene(tablePane);
        stage.setTitle("BlackJack");
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();

    }
    public static void main(String args[]){
        playersList.add(new Player("Bob"));
        launch(args);
    }

    public void setHBoxStyle(HBox box){
        box.setPadding(new Insets(15,15,15,15));
        box.setStyle("-fx-background-color: #119b00");
        box.setAlignment(Pos.CENTER);
    }
    public void setVBoxStyle(VBox box){
        box.setPadding(new Insets(15,15,15,15));
        box.setStyle("-fx-background-color: #119b00");
        box.setAlignment(Pos.CENTER);
    }
    public void setTextStyle(Text text){
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    }

    public void addPlayerCardsGUI(Player p, ImageView cardView){
        playerHbox.getChildren().add(cardView);
    }

    public void resetPlayerCardsGUI(){
        playerHbox.getChildren().clear();
    }

    public void addDealerCardsGUI(ImageView cardView){
        dealerHbox.getChildren().add(cardView);
    }
    public void resetDealerCardsGUI(){
        dealerHbox.getChildren().clear();
    }
    public void showDealerCardsGUI(){
        resetDealerCardsGUI();
        ArrayList<String> dealerCards = dealer.getCards();
        for(String s : dealerCards){
            addDealerCardsGUI(cardToImage(s));
        }
    }

    public ImageView cardToImage(String card){
        try {
            image = new Image(new FileInputStream("assets/" + card + ".png"));
        }catch (Exception e){
            System.out.println("file not found");
        }

        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(150);
        imageView1.setPreserveRatio(true);
        return imageView1;
    }


    public void deal(){
        for(Player p : playersList) {
            tempCard = deck.dealCard();
            addPlayerCardsGUI(p, cardToImage(tempCard));
            p.addCard(tempCard);

            tempCard = deck.dealCard();
            addPlayerCardsGUI(p, cardToImage(tempCard));
            p.addCard(tempCard);

            p.updateTotal();
            updatePlayerScore(p);

        }

        tempCard = deck.dealCard();
        addDealerCardsGUI(cardToImage("gray_back"));
        dealer.addCard(tempCard);

        tempCard = deck.dealCard();
        addDealerCardsGUI(cardToImage(tempCard));
        dealer.addCard(tempCard);

        dealer.updateTotal();
        updateDealerScore(dealer);
    }

    public void hit(Player p){
        if(p.bustStatus() != true) {
            tempCard = deck.dealCard();
            addPlayerCardsGUI(p, cardToImage(tempCard));
            p.addCard(tempCard);
        }
        p.updateTotal();
        updatePlayerScore(p);
        if(p.getTotal() > 21){
            p.busted();
            findWinner();
        }
    }

    public void check(Player p){
        if(p.bustStatus() == false) {
            dealersTurn();
        }else{
            findWinner();
        }
    }
    public void dealersTurn(){
        while(dealer.logicHit() == true){
            tempCard = deck.dealCard();
            dealer.addCard(tempCard);
            addDealerCardsGUI(cardToImage(tempCard));

            dealer.updateTotal();
            updateDealerScore(dealer);
        }
        findWinner();

    }

    public void findWinner(){
        boolean dealerwin = true;
        String winnerName = "";
        if(dealer.bustStatus()){
            dealerwin = false;
        }
        for(Player p : playersList){
            if(!dealerwin){
                winnerName = p.getName();

            }
            if(p.getTotal() > dealer.getTotal() && p.bustStatus() == false){
                winnerName = p.getName();
                dealerwin = false;
            }
        }

        if(dealerwin){
            currentTurn.setText("Dealer wins");
        }else{
            currentTurn.setText(winnerName + " wins!");
        }

        showDealerCardsGUI();

        showStartButton();
    }

    public void updatePlayerScore(Player p){
        playerScore.setText("Player: " + p.getTotal());
    }
    public void updateDealerScore(Dealer d){
        dealerScore.setText("Dealer: " + d.getTotal());
    }

    public void hideStartButton(){
        hitButton.setVisible(true);
        hitButton.setManaged(true);

        checkButton.setVisible(true);
        checkButton.setManaged(true);

        dealButton.setVisible(false);
        dealButton.setManaged(false);
    }
    public void showStartButton(){
        hitButton.setVisible(false);
        hitButton.setManaged(false);

        checkButton.setVisible(false);
        checkButton.setManaged(false);

        dealButton.setVisible(true);
        dealButton.setManaged(true);
    }

    public void start(){
        hideStartButton();
        currentTurn.setText("");

        dealer.setBust(false);
        dealer.resetCards();
        dealer.setTotal(0);
        updateDealerScore(dealer);

        for(Player p : playersList){
            p.setBust(false);
            p.resetCards();
            p.setTotal(0);
            updatePlayerScore(p);
        }

        resetDealerCardsGUI();
        resetPlayerCardsGUI();

        deck.shuffle();

        deal();
    }
}