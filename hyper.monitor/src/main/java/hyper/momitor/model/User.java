package hyper.momitor.model;

import java.io.Serializable;

/**
 * 用户
 *
 * @author qinsc (mailto:qinscx@gmail.com)
 */

public class User implements Serializable{
	private static final long serialVersionUID = -41637819376528794L;
	 
	private String userName;
	private String password;

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
