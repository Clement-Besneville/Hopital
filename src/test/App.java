package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dao.DAOCompte;
import dao.DAOPatient;
import dao.DAOVisite;
import model.Compte;
import model.Patient;
import model.Visite;
import model.typeCompte;



public class App {

	static Compte connected = null;
	static DAOCompte daoC = new DAOCompte();
	static DAOPatient daoP = new DAOPatient();
	static DAOVisite daoV = new DAOVisite();
	static LinkedList<Patient> listeAttente = new LinkedList();
	static List<Visite> listeVisiteMedecin = new ArrayList();
	static Patient actuelPatient = new Patient();
	static Visite actuelleVisite = null;

	
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

		if(connected.getType_compte()==typeCompte.Secretaire) {menuSecretaire();}
		else if(connected.getType_compte()==typeCompte.Medecin) {menuMedecin();}
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
		System.out.println("3 - Afficher la liste de visite du patient ");
		System.out.println("4 - Partir en pause");
		System.out.println("5 - Se deconnecter");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : menuPatient();break;
		case 2 : showAllFileAttente();break;
		case 3 : System.out.println(daoV.findAllByIdPatient(saisieInt("Votre ID")));break;
		case 4 : Pause();break;
		case 5 : connected=null;menuPrincipal();break;
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
/*
		switch(choix) 
		{
		case 1 : stockerFilAttentePause();menuPrincipal();break;
		case 2 : listAttenteAvantPause();menuSecretaire();break;
		case 3 : menuSecretaire();break;

		}
*/
		Pause();
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
     /*   
    
	private static void listAttenteAvantPause() {
		File f=new File( "File d attente .txt");
		FileInputStream fis=new FileInputStream(f);
		ObjectInputStream ois=new ObjectInputStream(fis);
		listeAttente = ois.readObject();

	}

	private static void stockerFilAttentePause() {
		File f=new File( "File d attente .txt");
		FileOutputStream fos=new FileOutputStream(f);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(listeAttente);
		oos.close();

	}
	*/

	private static void showAllFileAttente() 
	{
		System.out.println("La liste actuelle de la fil d'attente est : ");
		for(Patient p : listeAttente)
		{
			System.out.println(p);
		}
		
	}
	
	

	private static void addPatientFil() {
		
		System.out.println("Menu verification patient dans la file d attente");
		System.out.println("1 - ID Patient ");
		System.out.println("2 - Retour au menu principale de secretaire  ");
		

		int choix = saisieInt("Choisir une option");

		switch(choix) 
		{
		case 1 : listeAttente.add(daoP.findById(saisieInt("votre ID patient")));
		System.out.println("Ajout du patient "+listeAttente.peekLast());
		menuSecretaire();
		break;
		case 2 : menuSecretaire();break;
		}

		addPatientFil();
	}

	private static void AjoutPatientBDD() {
		

		
		System.out.println("Ajout patient dans la BDD");
		System.out.println("1 - Votre nom ");
		System.out.println("2 - Votre prenom  ");
		System.out.println("3 - Ajout patient dans la BDD ");
		System.out.println("4 - Retour au menu add patient liste d attente");

		int choix = saisieInt("Choisir une action");

		switch(choix) 
		{
		case 1 :actuelPatient.setNom(saisieString("votre nom"));break;
		case 2 :actuelPatient.setPrenom(saisieString("votre Prenom"));break;
		case 3 :daoP.insert(actuelPatient);
		System.out.println("ID du patient "+ actuelPatient.getPrenom()+" "+actuelPatient.getNom()+"est :"+actuelPatient.getId());
		break;
		case 4 :addPatientFil();break;
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
		case 2 : afficherListePatients();break;
		case 3 : afficherProchainPatient();break;
		case 4 : sauvegardeListeVisites();break;
		case 5 : connected=null;menuPrincipal();break;
		}

		menuMedecin();
	}
	
	
	public static void rendreSalleDisponible() {
		
		
		
		System.out.println("Menu salle dispo");
		System.out.println("1 - Appeler le prochain patient");
		System.out.println("2 - Retour vers le menu Medecin");
		
		

		int choix = saisieInt("Choisir votre action");

		switch(choix) 
		{
		case 1 : 
			
		if (listeVisiteMedecin.size()==0) {
			actuelPatient=listeAttente.peekFirst();
			System.out.println("Information du nouveau patient");
			System.out.println(actuelPatient);
		} else if(listeVisiteMedecin.size()==10) {
			
			for (Visite v : listeVisiteMedecin) {daoV.insert(v);};
			listeVisiteMedecin.clear();
			actuelPatient=listeAttente.peekFirst();
			System.out.println("Information du nouveau patient");
			System.out.println(actuelPatient);
			
			
		} else {
			actuelPatient =listeAttente.remove();
			listeVisiteMedecin.add(actuelleVisite);
			actuelPatient=listeAttente.peekFirst();
			System.out.println("Information du nouveau patient");
			System.out.println(actuelPatient);
		}
		break;
		
		case 2 : menuMedecin();break;
		}
	
		rendreSalleDisponible();
	}
	
	public static void afficherListePatients() {
		
		System.out.println("Menu affichage liste des patients");
		System.out.println("1 - afficher la liste des patients");
		System.out.println("2 - Retour vers le menu Medecin");
		

		int choix = saisieInt("Choisir votre action");

		switch(choix) 
		{
		case 1 : System.out.println(listeAttente);break;
		
		case 2 : menuMedecin();break;
		}
		
		afficherListePatients();
	}
	
	public static void afficherProchainPatient() {
		
		System.out.println("Menu affichage prochains patients");
		System.out.println("1 - afficher le prochain patient");
		System.out.println("2 - Retour vers le menu Medecin");
		
		int choix = saisieInt("Choisir votre action");


		switch(choix) 
		{
		case 1 : actuelPatient=listeAttente.get(listeAttente.indexOf(listeAttente.peek())+1);
		System.out.println(actuelPatient);
		break;
		
		case 2 : menuMedecin();break;
		}
		afficherProchainPatient();
		
	}
	
	public static void sauvegardeListeVisites() {
		
		System.out.println("Menu sauvegarde liste visites");
		System.out.println("1 - sauvegarder la liste des visites");
		System.out.println("2 - Retour vers le menu Medecin");
		
		int choix = saisieInt("Choisir votre action");

		

		switch(choix) 
		{
		case 1 : for (Visite v : listeVisiteMedecin) {daoV.insert(v);};
		listeVisiteMedecin.clear();
		break;
		
		case 2 : menuMedecin();break;
		
		}
		sauvegardeListeVisites();
		
	}
	
	
	

	
	//-----------------------------------
		
		
}
