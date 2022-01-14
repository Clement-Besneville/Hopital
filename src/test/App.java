package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import dao.DAOCompte;
import dao.DAOPatient;
import dao.DAOVisite;
import model.Compte;
import model.Medecin;
import model.Patient;
import model.Secretaire;
import model.Visite;



public class App {

	static Compte connected = null;
	static DAOCompte daoC = new DAOCompte();
	static DAOPatient daoP = new DAOPatient();
	static DAOVisite daoV = new DAOVisite();
	static LinkedList<Patient> listeAttente = new LinkedList();
	static Patient actuelPatient = new Patient();
	static Visite actuelleVisite;

	
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

		if(connected instanceof Secretaire) {menuSecretaire();}
		else if(connected instanceof Medecin) {choixSalle();}
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
		//System.out.println("2 - Revenir de la pause ");
		System.out.println("2 - Retour au menu principale de secretaire");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : stockerFilAttentePause();menuPause();break;
		//case 2 : listAttenteAvantPause();menuSecretaire();break;
		case 2 : menuSecretaire();break;

		}

		Pause();
	}
	
	private static void menuPause() 
	{
		System.out.println("Secretaire en pause");
		System.out.println("1 - Revenir de la pause");
		System.out.println("2 - Se deconnecter");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : listAttenteAvantPause();menuSecretaire();break;
		case 2 : menuPrincipal();break;

		}

		menuPause();
	}
	
	
	
	
	
	private static void menuPatient() {
        System.out.println("Menu verification patient dans la BDD");
      

        int id = saisieInt("Votre id ?");
        if(daoP.findById(id)==null) {AjoutPatientBDD();}
        else {addPatientFil(id);}
	}
     
    
	private static void listAttenteAvantPause() {
		File f=new File( "FileAttente.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			ObjectInputStream ois=new ObjectInputStream(fis);
			listeAttente = (LinkedList<Patient>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void stockerFilAttentePause() {
		File f=new File( "FileAttente.txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(f);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(listeAttente);
			listeAttente.clear();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	private static void showAllFileAttente() 
	{
		System.out.println("La liste actuelle de la file d'attente est : ");
		for(Patient p : listeAttente)
		{
			System.out.println(p);
		}
		
	}
	
	

	private static void addPatientFil(int id) {
		
		System.out.println("Menu verification patient dans la file d attente");
		System.out.println("1 - ID Patient ");
		System.out.println("2 - Retour au menu principale de secretaire  ");
		

		int choix = saisieInt("Choisir une option");

		
		switch(choix) 
		{
		case 1 : listeAttente.add(daoP.findById(id));
		System.out.println("Ajout du patient "+listeAttente.peekLast());
		menuSecretaire();
		break;
		case 2 : menuSecretaire();break;
		}

		addPatientFil(id);
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
		case 4 :addPatientFil(actuelPatient.getId());break;
		}

		AjoutPatientBDD();
		
	}

	
	//-----------------------------------
	
	
	
	public static void choixSalle() {
		
		System.out.println("Menu Salle");
		System.out.println("1 - Choisir salle 1");
		System.out.println("2 - Choisir Salle 2 ");
		System.out.println("3 - Se deconnecter");
		
		int choix = saisieInt("Choisir un menu");

		
		((Medecin) connected).setSalle(choix);
		switch(choix) 
		{
		case 1 : menuMedecin();break;
		case 2 : menuMedecin();break;
		case 3 : connected=null;menuPrincipal();break;
		}
		
		choixSalle();
	}
	
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
		case 5 : sauvegardeListeVisites();connected=null;menuPrincipal();break;
		}

		menuMedecin();
	}
	
	
	public static Visite creationVisiteMedecin (Patient patient) {
		
		actuelleVisite = new Visite((Medecin) connected,patient);
		return actuelleVisite;
	}
	
	public static void rendreSalleDisponible() {
		
		
		Medecin m = (Medecin) connected;
		System.out.println("Salle "+m.getSalle()+" maintenant disponible");
		
		
		if(m.getVisites().size()==10) {
			for (Visite v : m.getVisites()) {daoV.insert(v);};
			m.getVisites().clear();
			
			actuelPatient=listeAttente.poll();
			System.out.println("Information du nouveau patient");
			System.out.println(actuelPatient);
			m.getVisites().add(creationVisiteMedecin(actuelPatient));
			
		} else {
			actuelPatient=listeAttente.poll();
			System.out.println("Information du nouveau patient");
			System.out.println(actuelPatient);
			m.getVisites().add(creationVisiteMedecin(actuelPatient));
		}
		
		System.out.println("1 - Afficher la liste d'attente");
		System.out.println("2 - Afficher le prochain patient");
		System.out.println("3 - Sauvegarder les visites");
		System.out.println("4 - Retour vers le menu Medecin");
		
		int choix = saisieInt("Choisir votre action");


		switch(choix) 
		{
		case 1 : afficherListePatients(); break;
		case 2 : afficherProchainPatient();break;
		case 3 : sauvegardeListeVisites();break;
		case 4 : menuMedecin();break;
		}
	
	
		rendreSalleDisponible();
	}
	
	public static void afficherListePatients() {
		
		System.out.println("Affichage de la liste d'attente");
		System.out.println(listeAttente);
		System.out.println("1 - Rendre la salle disponible");
		System.out.println("2 - Afficher le prochain patient");
		System.out.println("3 - Sauvegarder les visites");
		System.out.println("4 - Retour vers le menu Medecin");
		
		int choix = saisieInt("Choisir votre action");


		switch(choix) 
		{
		case 1 : rendreSalleDisponible();break;
		case 2 : afficherProchainPatient();break;
		case 3 : sauvegardeListeVisites();break;
		case 4 : menuMedecin();break;
		}
		
		afficherListePatients();
	}
	
	public static void afficherProchainPatient() {
		
		System.out.println("Affichage du prochain patient");
		actuelPatient=listeAttente.peek();
		System.out.println(actuelPatient);
		System.out.println("1 - Rendre la salle disponible");
		System.out.println("2 - Afficher la liste d'attente");
		System.out.println("3 - Sauvegarder les visites");
		System.out.println("4 - Retour vers le menu Medecin");
		
		int choix = saisieInt("Choisir votre action");


		switch(choix) 
		{
		case 1 : rendreSalleDisponible();break;
		case 2 : afficherListePatients();break;
		case 3 : sauvegardeListeVisites();break;
		case 4 : menuMedecin();break;
		}
		afficherProchainPatient();
		
	}
	
	public static void sauvegardeListeVisites() {
		
		System.out.println("Sauvegarde de la liste des visites");
		
		for (Visite v : ((Medecin) connected).getVisites()) {daoV.insert(v);};
		((Medecin) connected).getVisites().clear();
		
		System.out.println("1 - Rendre la salle disponible");
		System.out.println("2 - Afficher la liste d'attente");
		System.out.println("3 - Afficher le prochain patient");
		System.out.println("4 - Retour vers le menu Medecin");
		
		int choix = saisieInt("Choisir votre action");


		switch(choix) 
		{
		case 1 : rendreSalleDisponible();break;
		case 2 : afficherListePatients();break;
		case 3 : afficherProchainPatient(); break;
		case 4 : menuMedecin();break;
		}


		sauvegardeListeVisites();
		
	}
	
	
	

	
	//-----------------------------------
		
		
}
