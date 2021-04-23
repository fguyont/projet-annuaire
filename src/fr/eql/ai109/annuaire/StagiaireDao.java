package fr.eql.ai109.annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class StagiaireDao {

	private static final String CHEMIN_FICHIER_STRUCTURE = "C:\\Users\\formation\\Desktop\\Fichiers-Projet1\\annuaireStructure.txt";
	private static final String CHEMIN_FICHIER_SOURCE = "C:\\Users\\formation\\Desktop\\Fichiers-Projet1\\stagiaires.txt";
	private static int tailleNom = 0;
	private static int taillePrenom = 0;
	private static int tailleDepartement = 0;
	private static int taillePromo = 0;
	private static int tailleAnnee = 0;
	private static int tailleStagiaire = 0;
	private static int tailleIndex = 0;
	private static int nbStagiaires = 0;
	private static ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
	private static ArrayList<Stagiaire> listeTriee = new ArrayList<Stagiaire>();
	
	
	
	public static ArrayList<Stagiaire> getListeTriee() {
		return listeTriee;
	}
	public static void setListeTriee(ArrayList<Stagiaire> listeTriee) {
		StagiaireDao.listeTriee = listeTriee;
	}
	public ArrayList<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}
	public void setListeStagiaires(ArrayList<Stagiaire> listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}
	public void lireFichierStagiaire () {

		BufferedReader br = null;
		FileReader in = null;

		try {

			in = new FileReader(CHEMIN_FICHIER_SOURCE);
			br = new BufferedReader(in);
			String chaine = "";

			ArrayList <String> donnees = new ArrayList<String>();
			Stagiaire stagiaire;

			while(br.ready()) {
				chaine = br.readLine();
				if (!(chaine.equals("*"))) {
					donnees.add(chaine);
				}
			}

			for (int a=0; a<donnees.size(); a=a+5) {			
				stagiaire = new Stagiaire (donnees.get(a).trim(), donnees.get(a+1).trim(), donnees.get(a+2).trim(), donnees.get(a+3).trim(), Integer.parseInt(donnees.get(a+4).trim()));
				listeStagiaires.add(stagiaire);
				nbStagiaires=listeStagiaires.size();
			}

			in.close();
			br.close();	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				in.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void determinerTaillesMax () {
		BufferedReader br = null;
		FileReader in = null;

		try {
			in = new FileReader(CHEMIN_FICHIER_SOURCE);
			br = new BufferedReader(in);
			String chaine = "";
			ArrayList <String> donnees = new ArrayList<String>();

			while(br.ready()) {
				chaine = br.readLine();

				if (!(chaine.equals("*"))) {
					donnees.add(chaine);
				}
			}

			for (int a=0; a<donnees.size(); a=a+5) {

				tailleNom = trouverMax (tailleNom, donnees.get(a).trim());
				taillePrenom = trouverMax (taillePrenom, donnees.get(a+1).trim());
				tailleDepartement = trouverMax(tailleDepartement, donnees.get(a+2).trim());
				taillePromo = trouverMax(taillePromo, donnees.get(a+3).trim());
				tailleAnnee = trouverMax(tailleAnnee, donnees.get(a+4).trim());
				tailleIndex = trouverMaxIndex(nbStagiaires);
				tailleStagiaire = tailleNom+taillePrenom+tailleDepartement+taillePromo+tailleAnnee+(tailleIndex*3);
			}
			in.close();
			br.close();	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				in.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int trouverMax(int tailleChamp, String champ) {
		if (tailleChamp < champ.length()) {
			return champ.length();
		}
		return tailleChamp;
	}

	public int trouverMaxIndex (int taille) {
		return String.valueOf(taille).length();
	}

	public void creerFichierStructure () {
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");
			
			// Insérer le premier stagiaire
			ecrireStagiaire(listeStagiaires.get(0), 0, raf);
			
			// Boucle qui parcourt la liste des stagiaires et qui prend chaque stagiaire pour les inscrire dans le nouveau fichier
			for (int i = 1; i<listeStagiaires.size(); i++) {
				// Appel de méthode pour inscrire le stagiaire
				insererStagiaire(listeStagiaires.get(i), i, 0, raf);
			}
			raf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Méthode qui insert un stagiaire. ex : denis 0 2 1 => numeroLigneAInserer : 1er chiffre (pos du stagiaire)
	//														destinationPourSauter : si le nom est plus petit, 2ème chiffre
	//																				sinon, 3ème chiffre
	//                                                      (premier appel : destination à 0)
	
	public void insererStagiaire (Stagiaire stagiaire, int numeroLigneAInserer, int destinationPourSauter, RandomAccessFile raf) throws IOException {

		// Récupère le nom pour le comparer avec le nom du stagiaire à insérer
		String nomStagiaireFichier = lireNom (destinationPourSauter, raf);

		// Si le nom du stagiaire à insérer est plus petit que le nom du stagiaire du fichier
		if (stagiaire.getNom().compareToIgnoreCase(nomStagiaireFichier) < 0) {
			
			// Si le fils gauche du stagiaire est null
			if ((filsGaucheEstVide (destinationPourSauter, raf)) == true) {
				
				// Fils gauche du stagiaire du fichier prend une nouvelle valeur : celle de la position du stagiaire à insérer
				ecrireFilsGauche (numeroLigneAInserer, destinationPourSauter, tailleIndex, raf);
				
				// Insertion du nouveau stagiaire en toute fin de fichier
				ecrireStagiaire (stagiaire, numeroLigneAInserer, raf);	
			}
			
			// Si le fils gauche existe
			else {
				
				// Récupère la valeur du fils gauche
				int indexFilsGauche = Integer.parseInt(lireFilsGauche (destinationPourSauter, raf).trim());
				
				// Répète la méthode mais en changeant la valeur de destination
				insererStagiaire (stagiaire, numeroLigneAInserer, indexFilsGauche, raf);
			}
		}
		else {
			if ((filsDroitEstVide (destinationPourSauter, raf)) == true) {
				ecrireFilsDroit (numeroLigneAInserer, destinationPourSauter, tailleIndex, raf);
				ecrireStagiaire (stagiaire, numeroLigneAInserer, raf);
			}
			else {
				int indexFilsDroit = Integer.parseInt(lireFilsDroit (destinationPourSauter, raf).trim());
				insererStagiaire (stagiaire, numeroLigneAInserer, indexFilsDroit, raf);
			}
		}

	}

	public String lireNom (int index, RandomAccessFile raf) throws IOException{
		String resultat = null;
		raf.seek(tailleStagiaire * index);
		byte[] b = new byte[tailleNom];
		raf.read(b);
		resultat = new String(b);
		return resultat;
	}

	public String lireFilsGauche (int index, RandomAccessFile raf) throws IOException{
		String resultat = null;
		raf.seek((tailleStagiaire * index) + tailleNom + taillePrenom + tailleDepartement +taillePromo+tailleAnnee+tailleIndex);
		byte[] b = new byte[tailleIndex];
		raf.read(b);
		resultat = new String(b);
		return resultat;
	}

	public String lireFilsDroit (int index, RandomAccessFile raf) throws IOException{
		String resultat = null;
		raf.seek((tailleStagiaire * index) + tailleNom + taillePrenom + tailleDepartement +taillePromo+tailleAnnee+tailleIndex+tailleIndex);
		byte[] b = new byte[tailleIndex];
		raf.read(b);
		resultat = new String(b);
		return resultat;
	}

	public boolean filsGaucheEstVide (int index, RandomAccessFile raf) throws IOException {
		String resultat = null;
		raf.seek((tailleStagiaire * index) + tailleNom + taillePrenom + tailleDepartement +taillePromo+tailleAnnee+tailleIndex);
		byte[] b = new byte[tailleIndex];
		raf.read(b);
		resultat = new String(b);
		if (resultat.equals("null")) {	
			return true;
		}
		return false;
	}

	public boolean filsDroitEstVide (int index, RandomAccessFile raf) throws IOException {
		String resultat = null;
		raf.seek((tailleStagiaire * index) + tailleNom + taillePrenom + tailleDepartement +taillePromo+tailleAnnee+(tailleIndex*2));
		byte[] b = new byte[tailleIndex];
		raf.read(b);
		resultat = new String(b);
		if (resultat.equals("null")) {
			return true;
		}
		return false;
	}

	// Méthode pour écrire le stagiaire. numero : position du stagiaire dans le fichier
	public void ecrireStagiaire (Stagiaire stagiaire, int numero, RandomAccessFile raf) throws IOException{
		raf.seek(numero*tailleStagiaire);
		
		// Ecriture de chaque attribut du stagiaire dans le fichier
		ecrireChamp(stagiaire.getNom(), tailleNom, raf);
		ecrireChamp(stagiaire.getPrenom(), taillePrenom, raf);
		ecrireChamp(stagiaire.getDepartement(), tailleDepartement, raf);
		ecrireChamp(stagiaire.getPromo(), taillePromo, raf);
		ecrireChamp(String.valueOf(stagiaire.getAnnee()), tailleAnnee, raf);
		ecrireChamp(String.valueOf(numero), tailleIndex, raf);
		ecrireChamp("null", tailleIndex, raf);
		ecrireChamp("null", tailleIndex, raf);
	}

	// Méthode pour écrire un champ. 
	public void ecrireChamp (String valeurChamp, int tailleChamp, RandomAccessFile raf) throws IOException {
		int nbEspaces = tailleChamp - valeurChamp.length();

		StringBuilder champEntierBuilder = new StringBuilder(valeurChamp);
		for (int i=0; i<nbEspaces; i++) {
			champEntierBuilder.append(" ");
		}

		String champEntier = champEntierBuilder.toString();
		byte[] b = champEntier.getBytes();
		raf.write(b);
	}

	// Méthode pour changer la valeur du fils gauche d'un stagiaire
	public void ecrireFilsGauche (int numeroAInserer, int index, int tailleChamp, RandomAccessFile raf) throws IOException {

		raf.seek((index*tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement+ taillePromo +tailleAnnee+tailleIndex);
		int nbEspaces = tailleChamp - String.valueOf(numeroAInserer).length();

		StringBuilder champEntierBuilder = new StringBuilder(String.valueOf(numeroAInserer));
		for (int i=0; i<nbEspaces; i++) {
			champEntierBuilder.append(" ");
		}

		String champEntier = champEntierBuilder.toString();
		byte[] b = champEntier.getBytes();
		raf.write(b);
	}

	public void ecrireFilsDroit (int numeroAInserer, int index, int tailleChamp, RandomAccessFile raf) throws IOException {

		raf.seek((index*tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement+ taillePromo +tailleAnnee+(tailleIndex*2));
		int nbEspaces = tailleChamp - String.valueOf(numeroAInserer).length();

		StringBuilder champEntierBuilder = new StringBuilder(String.valueOf(numeroAInserer));
		for (int i=0; i<nbEspaces; i++) {
			champEntierBuilder.append(" ");
		}

		String champEntier = champEntierBuilder.toString();
		byte[] b = champEntier.getBytes();
		raf.write(b);
	}
	
	public void afficherSurConsole () {
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");

			for (int i=0; i<nbStagiaires; i++) {
				raf.seek(tailleStagiaire * i);
				byte[] b = new byte[tailleStagiaire];
				raf.read(b);
				String afficheurEnregistrement = new String(b);
				System.out.println(afficheurEnregistrement);
			}
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ajouterListeTriee (int position, int destination, RandomAccessFile raf) throws IOException{
		
		raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");
		raf.seek (position * tailleStagiaire);
		if (filsGaucheEstVide(position, raf) == true) {
			Stagiaire stagiaire = convertirTexteEnStagiaire(position, raf);
			listeTriee.add(stagiaire);
		}
		else {
			ajouterListeTriee(Integer.parseInt(lireFilsGauche(position, raf)), destination, raf);
		}
		
	}
	
	public Stagiaire convertirTexteEnStagiaire (int position, RandomAccessFile raf) throws IOException {
		
		raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");
		raf.seek(position * tailleStagiaire);
		String nom, prenom, departement, promo, annee = null;
		
		byte[] b = new byte[tailleNom];
		raf.read(b);
		nom = new String(b);
		
		raf.seek((position * tailleStagiaire) + tailleNom);
		b = new byte[taillePrenom];
		raf.read(b);
		prenom = new String(b);
		
		raf.seek((position * tailleStagiaire) + tailleNom + taillePrenom);
		b = new byte[tailleDepartement];
		raf.read(b);
		departement = new String(b);
		
		raf.seek((position * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement);
		b = new byte[taillePromo];
		raf.read(b);
		promo = new String(b);
		
		raf.seek((position * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo);
		b = new byte[tailleAnnee];
		raf.read(b);
		annee = new String(b);
		
		raf.close();
		
		return new Stagiaire (nom.trim(), prenom.trim(), departement.trim(), promo.trim(), Integer.parseInt(annee.trim()));
	}
}






















