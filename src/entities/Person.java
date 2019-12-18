package entities;

public abstract class Person {

	
	String username;
	String pass;
	
	public Person(String username, String pass){
		this.username = username;
		this.pass = pass;
	}
	
	public String getUser() {
		return username;
	}
	
	public String getPass() {
		return pass;
	}
	

}
