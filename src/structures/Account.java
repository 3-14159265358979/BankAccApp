package structures;

/**
 * Account Class
 * 
 * This class is responsible for maintaining balance and level of the customer
 * at any given moment This class is mutable obviously
 * 
 * Abstraction Function: balance is any double value level is defined as silver,
 * gold, or platinum
 * 
 * Rep Invariant: 0 <= double <= Double.MAX_VALUE
 * level.getClass().equals("Silver"), 0 <= balance < 10000
 * level.getClass().equals("Gold"), 10000 <= balance < 20000
 * level.getClass().equals("Platinum"), balance >= 20000
 * 
 */

public class Account {

	Level level;
	double balance = 100;

	public Account() {
		updateLevel();
	}

	/**
	 * @param amount
	 *            The amount to be deposited
	 * 
	 *            Effects: increases account balance by amount Modifies: None
	 *            Requires: None
	 */
	public void deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
			updateLevel();
		} else {
			System.out.println("Deposited amount cannot be negative.");
		}
	}

	/**
	 * @param amount
	 *            The amount to be deposited
	 * 
	 *            Effects: decreases account balance by amount Modifies: None
	 *            Requires: None
	 */
	public boolean withdraw(double amount) {
		if (amount < 0) {
			System.out.println("Amount withdrawn cannot be negative.");
		} else if (amount > balance) {
			System.out.println("Amount withdrawn cannot be larger than your balance.");
		} else {
			updateLevel();
			balance -= amount;
			return true;
		}
		return false;
	}

	/**
	 * @param amount
	 *            The amount to be deposited
	 * 
	 *            Effects: Deducts balance by amount + some amount based on level of
	 *            the customer Modifies: None Requires: None
	 */
	public Boolean onlinePurchase(double amount) {
		updateLevel();
		if (amount < 50) {
			System.out.println("Online purchase amount is less than $50");
		} else {
			if (amount + level.getFee() > balance) {
				System.out.println("Transaction failed due to insufficient funds");
			} else {
				balance -= amount + level.getFee();
				return true;
			}
		}
		return false;
	}

	/**
	 * Effects: Updates the level of the customer Modifies: None Requires: None
	 */
	public void updateLevel() {
		if (balance < 10000) {
			level = new Silver();
		} else if (balance > 10000 && balance < 20000) {
			level = new Gold();
		} else {
			level = new Platinum();
		}
		level.setFee();
	}

	/**
	 * @param balance
	 *            The amount the balance should be set to
	 * 
	 *            Effects: changes this.balance to balance Modifies: None Requires:
	 *            None
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Effects: returns balance Modifies: None Requires: None
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Effects: returns level Modifies: None Requires: None
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Effects: Checks for validity of object
	 * Modifies: None
	 * Requires: None
	 */
	public boolean repOK() {
		if (balance > 0) {
			if (level.getClass().equals(Silver.class) || level.getClass().equals(Gold.class)
					|| level.getClass().equals(Platinum.class)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Effects: returns a String representation of the Account 
	 * Modifies: None
	 * Requires: None
	 */
	public String toString() {
		return "Account balance is: %" + balance + "\nAccount level is: " + level;
	}
	
}
