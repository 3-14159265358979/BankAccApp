package actions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import entities.Customer;

public class Modify {

	public static void updateCustomerBalance(Customer c) {

		Scanner sc = null;
		File file = null;
		FileOutputStream fileOut = null;
		try {
			// takes the text file and converts to string
			file = new File("./src/userData.txt");
			sc = new Scanner(file);
			String text = "";
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// take the customer and basically update its balance
				if (line.contains(c.getUser())) {
					String[] elem = line.split("-");
					line = elem[0] + "-" + elem[1] + "-" + c.getAccount().getBalance();
				}
				text += line + "\n";
			}
			// rewrites the text file with the change
			fileOut = new FileOutputStream("./src/userData.txt");
			fileOut.write(text.getBytes());
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

	public static Boolean addCustomer(Customer c) {
		Scanner sc = null;
		File file = null;
		FileOutputStream fileOut = null;
		try {
			// takes the text file and converts it to a String
			file = new File("./src/userData.txt");
			sc = new Scanner(file);
			String text = "";
			String line = "";
			while (sc.hasNextLine()) {
				line = sc.nextLine() + "\n";
				if (line.split("-")[0].equals(c.getUser())) {
					System.out.println("user already exist");
					throw new Exception();
				}
			}
			// add the customer info to the end of the text file
			text += c.getUser() + "-" + c.getPass() + "-" + c.getAccount().getBalance();
			fileOut = new FileOutputStream("./src/userData.txt");
			fileOut.write(text.getBytes());
			fileOut.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sc.close();
		}
	}
	
	public static Boolean removeCustomer(Customer c) {
		Scanner sc = null;
		File file = null;
		FileOutputStream fileOut = null;
		try {
			// takes the text file and converts to string
			file = new File("./src/userData.txt");
			sc = new Scanner(file);
			String text = "";
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				// skips adding the customer to be removed to text
				if (line.contains(c.getUser())) {
					continue;
				}
				text += line + "\n";
			}
			
			// rewrites the text while skipping the removed customer
			fileOut = new FileOutputStream("./src/userData.txt");
			fileOut.write(text.getBytes());
			fileOut.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sc.close();
		}
	}
}
