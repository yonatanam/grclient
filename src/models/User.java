package models;

import java.util.ArrayList;

public class User extends AbstractModel {
	
	private String firstName;
	/**last name of user   */
	private String lastName;
	/**username of user  */
	private String userName;
	/**password of user  */
	private String password;
	/**email of user  */
	private String email ;
	private String permission;
	private String status;
	private ArrayList<Book> cart;
	


	public User(String firstName, String lastName, String userName, String email, String permission, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.permission = permission;
		this.status=status;
		this.cart = new ArrayList<Book>();
	}
	
	public User(String username, String password, String fname, String lname, String email, String permission, String status)
	{
		super();
		this.userName = username;
		this.password = password;
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
		this.permission = permission;
		this.status=status;
		this.cart = new ArrayList<Book>();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<Book> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Book> cart) {
		this.cart = cart;
	}
	
}


	
