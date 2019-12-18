package gui;

import actions.Modify;
import entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.Account;

public class ManagerController {

	@FXML
	private TextField txtF_user;
	@FXML
	private TextField txtF_pass;
	@FXML
	private Text txt_msg;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_add;
	@FXML
	private Button btn_rem;

	@FXML
	protected void handleAddButtonAction(ActionEvent event) {
		if(Modify.addCustomer(new Customer(txtF_user.getText(), txtF_pass.getText(), new Account()))) {
			txt_msg.setText("Successfully added Customer");
		}else {
			txt_msg.setText("Failed to add Customer");
		}
	}
	
	@FXML
	protected void handleRemoveButtonAction(ActionEvent event) {
		if(Modify.removeCustomer(new Customer(txtF_user.getText(), txtF_pass.getText(), new Account()))) {
			txt_msg.setText("Successfully removed Customer");
		}else {
			txt_msg.setText("Failed to remove Customer");
		}
		
	}
	
	
	@FXML
	protected void handleLogoutButtonAction(ActionEvent event) {

		try {

			// Path to the FXML File
			Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
			// Create the Scene
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// Set the Scene to the Stage
			stage.setScene(scene);
			stage.setTitle("Bank Account Application: Login");
			// Display the Stage
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
