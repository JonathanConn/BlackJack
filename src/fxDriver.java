import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//        cardHbox.getChildren().add(imageView1);

public class fxDriver extends Application {
    BorderPane tablePane = new BorderPane();
    BorderPane playerPane = new BorderPane();
    BorderPane dealerPane = new BorderPane();

    HBox cardHbox = new HBox();

    Image image = null;

    @Override
    public void start(Stage stage) {

        try {
            image = new Image(new FileInputStream("assets/2C.png"));
        }catch (Exception e){
            System.out.println("file not found");
        }

        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(150);
        imageView1.setPreserveRatio(true);

        cardHbox.setPadding(new Insets(15,15,15,15));
        cardHbox.setStyle("-fx-background-color: #005e1d");
        cardHbox.setAlignment(Pos.CENTER);

        cardHbox.getChildren().add(imageView1);

        playerPane.setCenter(cardHbox);
        tablePane.setBottom(playerPane);

        Button testButton = new Button("FLIP");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){

            }
        };
        testButton.setOnAction(event);

        tablePane.setTop(testButton);

        Scene scene = new Scene(tablePane);

        stage.setTitle("BlackJack");
        stage.setWidth(1000);
        stage.setHeight(800);


        stage.setScene(scene);

        //maybe?
        //stage.setMaximized(true);

        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }

    public void updatePlayerCards(String card){
        try {
            image = new Image(new FileInputStream("assets/" + card + ".png"));
        }catch (Exception e){
            System.out.println("file not found");
        }
        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(150);
        imageView1.setPreserveRatio(true);

        cardHbox.getChildren().add(imageView1);

    }
    public void updateDealerCards(){

    }


}