package test;

import java.util.Scanner;

import dao.DAOCompte;
import model.Admin;
import model.Client;
import model.Vendeur;


public class App {

	static Compte connected = null;
	static DAOCompte daoC = new DAOCompte();
	static DAOPatient daoP = new DAOPatient();
	static DAOVisite daoV = new DAOVisite();
	

	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}

	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}

	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}
	
	public static void main(String[] args) {
		menuPrincipal();
	}
	
	public static void menuPrincipal() {

		System.out.println("Menu principal");
		System.out.println("1 - Se connecter");
		System.out.println("2 - Fermer l'appli");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : connexion();break;
		case 2 : System.exit(0);break;
		}
		menuPrincipal();
	}
	
	
	public static void connexion() {

		System.out.println("Connexion");
		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");
		connected= daoC.seConnecter(login, password);

		if(connected.getTypeCompte=typeCompte.Secretaire) {menuSecretaire();}
		else if(connected.getTypeCompte=typeCompte.Medecin) {menuMedecin();}
		else if(connected ==null) 
		{
			System.out.println("Identifiants invalides !");
		}
		menuPrincipal();
	}
	
	//-----------------------------------
	
	public static void menuSecretaire() 
	{
		System.out.println("Menu Secretaire");
		System.out.println("1 - Ajouter un patient a la file d attente");
		System.out.println("2 - Afficher l etat de la file d attente ");
		System.out.println("3 - Partir en pause");
		System.out.println("4 - Se deconnecter");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : addPatient();break;
		case 2 : showAllFileAttente();break;
		case 3 : Pause();break;
		case 4 : connected=null;menuPrincipal();break;
		}

		menuSecretaire();

	}
	
	
	private static void Pause() 
	{
		// TODO Auto-generated method stub
		
	}

	private static void showAllFileAttente() 
	{
		// TODO Auto-generated method stub
		
	}

	private static void addPatient() 
	{
		// TODO Auto-generated method stub
		
	}
	

	//-----------------------------------
	
	
	public static void menuMedecin() {
		
		System.out.println("Menu Medecin");
		System.out.println("1 - Rendre la salle disponible");
		System.out.println("2 - Voir la liste des patients ");
		System.out.println("3 - Voir le prochain patient");
		System.out.println("4-  Sauvegarder la liste des visites ");
		System.out.println("5 - Se deconnecter");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : rendreSalleDisponible();break;
		case 2 : addFicherListePatients();break;
		case 3 : afficherProchainPatient();break;
		case 4 : sauvegardeListeVisites();break;
		case 5 : connected=null;menuPrincipal();break;
		}

		menuMedecin();
	}
	
	
	public static void rendreSalleDisponible() {
		
		rendreSalleDisponible();
	}
	
	public static void addFicherListePatients() {
		
		addFicherListePatients();
	}
	
	public static void afficherProchainPatient() {
		
		afficherProchainPatient();
	}
	
	public static void sauvegardeListeVisites() {
		
		sauvegardeListeVisites();
	}
	

	
	//-----------------------------------
		
		
}
