package model;

import java.time.LocalDate;

public class Visite {

	private Integer numero;
	private Integer id_patient;
	private Integer id_medecin;
	private int prix=20;
	private Integer salle;
	private LocalDate date_visite;
	
	public Visite(Integer numero, Integer id_patient, Integer id_medecin, int prix, Integer salle,
			LocalDate date_visite) {
		super();
		this.numero = numero;
		this.id_patient = id_patient;
		this.id_medecin = id_medecin;
		this.prix = prix;
		this.salle = salle;
		this.date_visite = date_visite;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getId_patient() {
		return id_patient;
	}

	public Integer getId_medecin() {
		return id_medecin;
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

	public void setId_patient(Integer id_patient) {
		this.id_patient = id_patient;
	}

	public void setId_medecin(Integer id_medecin) {
		this.id_medecin = id_medecin;
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
		return "Visite [numero=" + numero + ", id_patient=" + id_patient + ", id_medecin=" + id_medecin + ", prix="
				+ prix + ", salle=" + salle + ", date_visite=" + date_visite + "]";
	}
	
	
	
	
}
