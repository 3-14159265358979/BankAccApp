package gui;

import actions.Login;
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

public class LoginController {
	@FXML
	private Button btn_exit;
	@FXML
	private Button btn_login;
	@FXML
	private TextField txtF_user;
	@FXML
	private TextField txtF_pass;
	@FXML
	private Text txt_loginFail;

	@FXML
	protected void handleLoginButtonAction(ActionEvent event) {
		String user = txtF_user.getText();
		String pass = txtF_pass.getText();
		Account acc = Login.login(user, pass);
		Parent root = null;
		// checks for whether login was a success or not
		try {
			if (acc == null) {
				System.out.println("No account found");
				txt_loginFail.setText("Login Failed");
			} else if ((int)acc.getBalance() == -1) {
				// Paths to the respective FXML files
				root = FXMLLoader.load(getClass().getResource("ManagerGUI.fxml"));
			} else {
				root = FXMLLoader.load(getClass().getResource("CustomerGUI.fxml"));
			}

			// Create the Scene
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			// Set the Scene to the Stage
			stage.setScene(scene);
			stage.setTitle("Bank Account Application");
			// Display the Stage
			stage.show();

			CustomerController.init(user, pass, acc);

			// switch the scene to the customer GUI
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@FXML
	protected void handleExitButtonAction(ActionEvent event) {
		Stage stage = (Stage) btn_exit.getScene().getWindow();
		stage.close();
	}

}