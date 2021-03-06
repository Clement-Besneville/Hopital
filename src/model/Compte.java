package model;

import java.io.Serializable;

public class Compte implements Serializable {

	private Integer id;
	private String login;
	private String password; 
	
	
	public Compte(Integer id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		
	}


	public Integer getId() {
		return id;
	}


	public String getLogin() {
		return login;
	}


	public String getPassword() {
		return password;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	
	

}
