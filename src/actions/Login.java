package actions;

import structures.Account;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Login {

	public static Account login(String user, String pass) {

		Scanner sc = null;
		File file = null;
		String text = "";
		String[] accInfo = new String[4];
		Account acc = new Account();

		try {
			// takes the text file and converts to string
			file = new File("./src/userData.txt");
			sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				text += sc.nextLine() + "\n";
			}
			
			// checks whether user and pass exist and if so, create account for it
			if (validate(user, pass, text, accInfo)) {
				acc.setBalance(Double.parseDouble(accInfo[2]));
				return acc;
			}else {
				return null;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Check file path");
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("You messed up pretty bad, huh (null value somewhere)");
			
		} finally {
			sc.close();
		}
		return null;
	}

	public static Boolean validate(String user, String pass, String text, String[] accInfo) {
		// arr converts text into an array containing all entities in the system
		String[] arr = text.split("\n");
		String[] temp;
		
		// splits array and checks user and pass
		for (int i = 0; i < arr.length; i++) {
			temp = arr[i].split("-");
			
			if (temp[0].equals(user) && temp[1].equals(pass)) {
				// assigns array element by element to pass values back to method caller
				for (int j = 0; j < temp.length; j++) {
					accInfo[j] = temp[j];
				}
				if (temp[2].equals("NA")) {
					accInfo[2] = "-1";
				}
				
				return true;
			}

		}
		return false;
	}
}
