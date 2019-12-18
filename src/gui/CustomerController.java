package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import actions.Modify;
import entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.Account;

public class CustomerController {
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_getBalance;
	@FXML
	private Button btn_withdraw;
	@FXML
	private Button btn_deposit;
	@FXML
	private Button btn_makePurchase;
	@FXML
	private TextField txtF_transaction;
	@FXML
	private Text txt_balance;
	@FXML
	private TextArea txtA_log;
	@FXML
	private Text txt_name;

	static Customer c;

	protected static void init(String user, String pass, Account a) {
		c = new Customer(user, pass, a);
	}

	protected void update(String s) {
		txt_balance.setText(c.getAccount().getBalance() + "");
		txtA_log.setText(txtA_log.getText() + s + ". Your current balance is: " + c.getAccount().getBalance() + "\n");
	}

	// checks for validity of input
	protected Boolean checkInput(String s) {
		try {
			if (Double.parseDouble(s) < 0) {
				txtA_log.setText(
						txtA_log.getText() + "You've entered a negative number, please keep it strictly positive.\n");
				return false;
			} else {
				return true;
			}
		} catch (IllegalArgumentException e) {
			txtA_log.setText(txtA_log.getText() + "Invalid input.\n");
			return false;
		}
	}

	@FXML
	protected void handleGetBalanceButtonAction(ActionEvent event) {
		txt_balance.setText(c.getAccount().getBalance() + "");
		update("");

	}

	@FXML
	protected void handleDepositButtonAction(ActionEvent event) {
		String amount = txtF_transaction.getText();
		String s;
		
		if (checkInput(amount)) {
			c.deposit(Double.parseDouble(txtF_transaction.getText()));
			s = "You've deposited " + txtF_transaction.getText();
			update(s);
		}
	}

	@FXML
	protected void handleWithdrawButtonAction(ActionEvent event) {
		String amount = txtF_transaction.getText();
		String s;

		if (checkInput(amount)) {
			if (c.withdraw(Double.parseDouble(txtF_transaction.getText()))) {
				s = "You've withdrawn " + txtF_transaction.getText();
				update(s);
			} else {
				txtA_log.setText(txtA_log.getText() + "Transaction failed due to insufficient funds.\n");
			}
		}
	}

	@FXML
	protected void handleMakePurchaseButtonAction(ActionEvent event) {
		String amount = txtF_transaction.getText();
		String s;

		if (checkInput(amount)) {
			if (c.onlinePurchase((Double.parseDouble(txtF_transaction.getText())))) {
				s = "You've made an online purchase of $" + txtF_transaction.getText();
				s += ". A charge of $" + (c.getAccount().getLevel().getFee()) + "was applied.\n";
				update(s);
			} else {
				txtA_log.setText(txtA_log.getText() + "Transaction failed due to insufficient funds.\n");
			}
		}
	}

	@FXML
	protected void handleLogoutButtonAction(ActionEvent event) {

		try {
			Modify.updateCustomerBalance(c);

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
