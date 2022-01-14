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
	
	public static void menuSecretaire() {
		
	}
	
	
	//-----------------------------------
	
	
	public static void menuMedecin() {
		
	}

	
	//-----------------------------------
		
		
}