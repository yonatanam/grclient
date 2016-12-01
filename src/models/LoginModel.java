package models;

import java.io.Serializable;
/**
 * this class is Login Model  which saves the Login Model fields data ,which extends AbstractModel
 *
 */

public class LoginModel extends AbstractModel {
	/**user Name of employee*/
	private String userName;
	/**password of employee */
	private String Password;
	/**
	 * constructor
	 */
	public LoginModel(){
		
		userName = null;
		Password = null;
	}
	
/**
 * get User Name
 * @return String
 */
	public String getUserName() {
		return userName;
	}
/**
 * set new User Name
 * @param userName
 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
/**
 * get Password
 * @return String
 */
	public String getPassword() {
		return Password;
	}
/**
 * set new Password
 * @param password
 */
	public void setPassword(String password) {
		Password = password;
	}

}


