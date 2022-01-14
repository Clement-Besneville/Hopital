package model;

import java.io.Serializable;

public class Compte implements Serializable {

	private Integer id;
	private String login;
	private String password; 
	private typeCompte type_compte;
	
	
	public Compte(Integer id, String login, String password, typeCompte type_compte) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.type_compte = type_compte;
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


	public typeCompte getType_compte() {
		return type_compte;
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


	public void setType_compte(typeCompte type_compte) {
		this.type_compte = type_compte;
	}


	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + ", type_compte=" + type_compte
				+ "]";
	}
	
	

}
