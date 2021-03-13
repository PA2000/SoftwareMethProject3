package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the main class that runs everything and sets up the stage for the GUI
 */
public class Main extends Application {

    /**
     * This function sets the UI controls in a scene and displays it in a stage
     * @param primaryStage the primary stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Payroll Processor");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This function overrides the start method above, and calls launch
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
