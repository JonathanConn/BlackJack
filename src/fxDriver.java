import javafx.application.Application;
import javafx.collections.ObservableList;
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


public class fxDriver extends Application {
    @Override
    public void start(Stage stage) {

        BorderPane table = new BorderPane();
        BorderPane player = new BorderPane();

        Image image = null;
        try {
            image = new Image(new FileInputStream("assets/2C.png"));
        }catch (Exception e){
            System.out.println("file not found");
        }

        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(150);
        imageView1.setPreserveRatio(true);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15,15,15,15));
        hbox.setStyle("-fx-background-color: #336699");
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().add(imageView1);

        player.setCenter(hbox);
        table.setBottom(player);

        Scene scene = new Scene(table);

        stage.setTitle("BlackJack");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}