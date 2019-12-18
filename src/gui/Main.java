package gui;
	
import java.io.FileInputStream;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// Path to the FXML File
	        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
	        
	        // Create the Scene
	        Scene scene = new Scene(root);
	        // Set the Scene to the Stage
	        stage.setScene(scene);
	        stage.setTitle("Bank Account Application: Login");
	        // Display the Stage
	        stage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
