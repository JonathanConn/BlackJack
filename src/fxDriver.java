import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class fxDriver extends Application {
    @Override
    public void start(Stage stage) {
        //creating a text field
        TextField textField = new TextField();

        //Creating the play button
        Button hitButton = new Button("Hit");

        //Creating the stop button
        Button checkButton = new Button("Check");

        //Instantiating the HBox class
        HBox hbox = new HBox();

        //Setting the space between the nodes of a HBox pane
        hbox.setSpacing(10);

        //Setting the margin to the nodes
        hbox.setMargin(textField, new Insets(20, 20, 20, 20));
        hbox.setMargin(hitButton, new Insets(20, 20, 20, 20));
        hbox.setMargin(checkButton, new Insets(20, 20, 20, 20));

        //retrieving the observable list of the HBox
        ObservableList list = hbox.getChildren();

        //Adding all the nodes to the observable list (HBox)
        list.addAll(textField, hitButton, checkButton);

        //Creating a scene object
        Scene scene = new Scene(hbox);

        //Setting title to the Stage
        stage.setTitle("BlackJack");

        //Adding scene to the stage
        stage.setScene(scene);
        stage.setMaximized(true);
        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}