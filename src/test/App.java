package test;

import java.util.Scanner;

import dao.DAOCompte;
import dao.DAOPatient;
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
		case 1 : menuPatient();break;
		case 2 : showAllFileAttente();break;
		case 3 : Pause();break;
		case 4 : connected=null;menuPrincipal();break;
		}

		menuSecretaire();

	}
	
	
	private static void Pause() 
	{
		System.out.println("Menu pause Secretaire");
		System.out.println("1 - Partir en pause");
		System.out.println("2 - Revenir de la pause ");
		System.out.println("3 - Retour au menu principale de secretaire");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : stockerFilAttentePause();menuPrincipal();break;
		case 2 : listAttenteAvantPause();menuSecretaire();break;
		case 3 : menuSecretaire();break;

		}

		Pause();
	}
	
	
	private static void listAttenteAvantPause() {
		FileInputStream fis=new FileInputStream(f);
		ObjectInputStream ois=new ObjectInputStream(fis);
		listAttente ois.readObject();

	}

	private static void stockerFilAttentePause() {
		File f=new File( "File d attente .txt");
		FileOutputStream fos=new FileOutputStream(f);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(listAttente);
		oos.close();

	}

	private static void showAllFileAttente() 
	{
		System.out.println("La liste actuelle de la fil d'attente est : ");
		for(Patient p : ListAttente)
		{
			System.out.println(p);
		}
		
	}


	private static void menuPatient() {
		System.out.println("Menu verification patient dans la BDD");
		System.out.println("1 - Si c est votre permiere visite ");
		System.out.println("2 - si vous etes connu par notre systeme  ");
		System.out.println("3 - aucun des cas");

		int choix = saisieInt("Choisir une option");

		switch(choix) 
		{
		case 1 : AjoutPatientBDD();break;
		case 2 : addPatientFil();break;
		case 3 :menuSecretaire();break;
		}

		menuPatient();
		
	}
	
	

	private static void addPatientFil() {
		Patient p;
//		daoP.findById(p.getID);
		System.out.println("Menu verification patient dans la file d attente");
		System.out.println("1 - ID Patient ");
		System.out.println("2 - Retour au menu principale de secretaire  ");
		

		int choix = saisieInt("Choisir une option");

		switch(choix) 
		{
		case 1 : listAttente.add(daoP.findById(saisieInt("votre ID patient")));break;
		case 2 : menuSecretaire();break;
		}

		addPatientFil();
	}

	private static void AjoutPatientBDD() {
		
		Patient p;
		
		System.out.println("Ajout patient dans la BDD");
		System.out.println("1 - Votre nom ");
		System.out.println("2 - Votre prenom  ");
		System.out.println("3 - Ajout patient dans la BDD ");
		System.out.println("4 - Retour au menu add patient liste d attente");

		int choix = saisieInt("Choisir une action");

		switch(choix) 
		{
		case 1 :p.getNom(saisieString("votre nom"));break;
		case 2 :p.getPrenom(saisieString("votre Prenom"));break;
		case 3 :daoP.insert(p);System.out.println("ID du patient "+ p.getPrenom+" "+p.getNom+"est :"+p.getID);break;
		case 5 :addPatientFil();break;
		}

		AjoutPatientBDD();
		
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
