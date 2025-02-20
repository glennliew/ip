package grennite;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import grennite.exception.GrenniteException;

/**
 * A GUI for Grennite using FXML.
 */
public class Main extends Application {

    private Grennite grennite;

    @Override
    public void start(Stage stage) {
        try {
            // Ensure Grennite is initialized safely
            grennite = new Grennite();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Set stage properties
            stage.setScene(scene);
            stage.setTitle("Grennite - Your Task Assistant");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setResizable(false);

            // Inject Grennite instance into controller
            fxmlLoader.<MainWindow>getController().setGrennite(grennite);
            
            stage.show();
        } catch (GrenniteException e) {
            System.err.println("Error initializing Grennite: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: /view/MainWindow.fxml");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
