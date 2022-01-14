package model;

import java.util.ArrayList;
import java.util.List;

public class Medecin extends Compte {


	private List<Visite> visites = new ArrayList();
	private int salle;
	
	
	public Medecin(Integer id, String login, String password) {
		super(id, login, password);
		
	}


	public List<Visite> getVisites() {
		return visites;
	}


	public int getSalle() {
		return salle;
	}


	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}


	public void setSalle(int salle) {
		this.salle = salle;
	}
	
	
}
