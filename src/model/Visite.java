package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Visite implements Serializable {

	private Integer numero;
	private Medecin medecin;
	private Patient patient;
	private int prix;
	private Integer salle;
	private LocalDate date_visite;
	

	public Visite () {
		
	}
	
	public Visite(Integer numero, Medecin compte, Patient patient, Integer salle, LocalDate date_visite,int prix) {
		this.numero = numero;
		this.medecin = compte;
		this.patient = patient;
		this.salle = salle;
		this.date_visite = date_visite;
		this.prix=prix;
	}
	
	public Visite(Medecin compte, Patient patient) {
	
		this.medecin = compte;
		this.patient = patient;
		this.salle = compte.getSalle();
		this.date_visite = LocalDate.now();
		this.prix=20;
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



	public Medecin getCompte() {
		return medecin;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setCompte(Medecin compte) {
		this.medecin = compte;
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
		return "Visite [numero=" + numero + ", compte=" + medecin + ", patient=" + patient + ", prix=" + prix
				+ ", salle=" + salle + ", date_visite=" + date_visite + "]";
	}


	
	
	
	
}
