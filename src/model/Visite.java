package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Visite implements Serializable {

	private Integer numero;
	private Compte compte;
	private Patient patient;
	private int prix=20;
	private Integer salle;
	private LocalDate date_visite;
	

	public Visite () {
		
	}
	
	public Visite(Integer numero, Compte compte, Patient patient, Integer salle, LocalDate date_visite) {
		super();
		this.numero = numero;
		this.compte = compte;
		this.patient = patient;
		this.salle = salle;
		this.date_visite = date_visite;
	}

	public Integer getNumero() {
		return numero;
	}



	public int getPrix() {
		return prix;
	}

	public Integer getSalle() {
		return salle;
	}

	public LocalDate getDate_visite() {
		return date_visite;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}



	public Compte getCompte() {
		return compte;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public void setSalle(Integer salle) {
		this.salle = salle;
	}

	public void setDate_visite(LocalDate date_visite) {
		this.date_visite = date_visite;
	}

	@Override
	public String toString() {
		return "Visite [numero=" + numero + ", compte=" + compte + ", patient=" + patient + ", prix=" + prix
				+ ", salle=" + salle + ", date_visite=" + date_visite + "]";
	}


	
	
	
	
}
