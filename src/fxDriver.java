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
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//        cardHbox.getChildren().add(imageView1);

public class fxDriver extends Application {

    static boolean gameRunning = true;
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

    VBox buttonVbox = new VBox();

    Image image = null;

    @Override
    public void start(Stage stage) {

        cardHbox.setPadding(new Insets(15,15,15,15));
        cardHbox.setStyle("-fx-background-color: #005e1d");
        cardHbox.setAlignment(Pos.CENTER);

        dealerHbox.setPadding(new Insets(15,15,15,15));
        dealerHbox.setStyle("-fx-background-color: #99130D");
        dealerHbox.setAlignment(Pos.CENTER);

        playerHbox.setPadding(new Insets(15,15,15,15));
        playerHbox.setStyle("-fx-background-color: #0D9995");
        playerHbox.setAlignment(Pos.CENTER);

        dealerPane.setCenter(dealerHbox);
        playerPane.setCenter(playerHbox);
        cardPane.setCenter(cardHbox);

        tablePane.setTop(dealerPane);
        tablePane.setCenter(cardPane);
        tablePane.setBottom(playerPane);

        Button dealButton = new Button("Start");
        Button hitButton = new Button("Hit");
        Button checkButton = new Button("Check");

        EventHandler<ActionEvent> dealEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                deal();
            }
        };
        EventHandler<ActionEvent> hitEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                hit(playersList.get(0));
            }
        };
        EventHandler<ActionEvent> checkEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){

            }
        };

        dealButton.setOnAction(dealEvent);
        hitButton.setOnAction(hitEvent);
        checkButton.setOnAction(checkEvent);

        dealButton.setPadding(new Insets(15,15,15,15));
        hitButton.setPadding(new Insets(15,15,15,15));
        checkButton.setPadding(new Insets(15,15,15,15));


        buttonVbox.setPadding(new Insets(15,15,15,15));
        buttonVbox.setStyle("-fx-background-color: #8b0099");
        buttonVbox.setAlignment(Pos.CENTER);

        buttonVbox.getChildren().addAll(dealButton, hitButton, checkButton);

        tablePane.setRight(buttonVbox);

        Scene scene = new Scene(tablePane);
        stage.setTitle("BlackJack");
        stage.setWidth(1000);
        stage.setHeight(800);

        stage.setScene(scene);
        stage.show();

    }
    public static void main(String args[]){
        playersList.add(new Player("Bob"));
        launch(args);
    }

    public void addPlayerCardsGUI(Player p, ImageView cardView){
        playerHbox.getChildren().add(cardView);
    }

    public void resetPlayerCardsGUI(){
        playerHbox.getChildren().removeAll();
    }

    public void addDealerCardsGUI(ImageView cardView){
        dealerHbox.getChildren().add(cardView);
    }
    public void resetDealerCardsGUI(){
        dealerHbox.getChildren().removeAll();
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
        }

        tempCard = deck.dealCard();
        addDealerCardsGUI(cardToImage("gray_back"));
        dealer.addCard(tempCard);

        tempCard = deck.dealCard();
        addDealerCardsGUI(cardToImage(tempCard));
        dealer.addCard(tempCard);

        dealer.updateTotal();
    }

    public void hit(Player p){
        if(p.bustStatus() != true) {
            tempCard = deck.dealCard();
            addPlayerCardsGUI(p, cardToImage(tempCard));
            p.addCard(tempCard);
        }
        p.updateTotal();
        if(p.getTotal() > 21){
            p.busted();
            System.out.println("bust");
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

}