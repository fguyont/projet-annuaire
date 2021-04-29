package fr.eql.ai109.annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
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
	private static int derniereLigne = 0;
	private static ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
	private static ArrayList<Stagiaire> listeTriee = new ArrayList<Stagiaire>();
	private static ArrayList<Stagiaire> listeRecherches = new ArrayList<Stagiaire>();
	private static int posDepartFichier = 0;



	public static int getPosDepartFichier() {
		return posDepartFichier;
	}

	public static void setPosDepartFichier(int posDepartFichier) {
		StagiaireDao.posDepartFichier = posDepartFichier;
	}

	public static int getNbStagiaires() {
		return nbStagiaires;
	}

	public static void setNbStagiaires(int nbStagiaires) {
		StagiaireDao.nbStagiaires = nbStagiaires;
	}

	public static int getDerniereLigne() {
		return derniereLigne;
	}

	public static void setDerniereLigne(int derniereLigne) {
		StagiaireDao.derniereLigne = derniereLigne;
	}

	public ArrayList<Stagiaire> getListeRecherches() {
		return listeRecherches;
	}

	public ArrayList<Stagiaire> getListeTriee() {
		return listeTriee;
	}

	public ArrayList<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
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

			int i = 0;

			for (int a=0; a<donnees.size(); a=a+5) {	
				stagiaire = new Stagiaire (donnees.get(a).trim(), donnees.get(a+1).trim(), donnees.get(a+2).trim(), 
						donnees.get(a+3).trim(), Integer.parseInt(donnees.get(a+4).trim()), i);
				listeStagiaires.add(stagiaire);
				nbStagiaires=listeStagiaires.size();
				i++;
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
			derniereLigne++;

			// Boucle qui parcourt la liste des stagiaires et qui prend chaque stagiaire pour les inscrire dans le nouveau fichier
			for (int i = 1; i<listeStagiaires.size(); i++) {
				// Appel de méthode pour inscrire le stagiaire
				insererStagiaire(listeStagiaires.get(i), i, 0, raf);
				derniereLigne++;
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

	public String lirePosition (int index, RandomAccessFile raf) throws IOException{
		String resultat = null;
		raf.seek((tailleStagiaire * index) + tailleNom + taillePrenom + tailleDepartement +taillePromo+tailleAnnee);
		byte[] b = new byte[tailleIndex];
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

	public void ajouterListeTriee (int positionCourante, RandomAccessFile raf) {

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");
			raf.seek (positionCourante * tailleStagiaire);

			if (filsGaucheEstVide(positionCourante, raf) == false) {
				int indexFilsGauche = Integer.parseInt(lireFilsGauche(positionCourante, raf).trim());
				ajouterListeTriee(indexFilsGauche, raf);
			}

			Stagiaire stagiaire = convertirTexteEnStagiaire(positionCourante, raf);
			listeTriee.add(stagiaire);	

			if (filsDroitEstVide(positionCourante, raf) == false) {
				int indexFilsDroit = Integer.parseInt(lireFilsDroit(positionCourante, raf).trim());
				ajouterListeTriee(indexFilsDroit, raf);
			}	
			raf.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	public Stagiaire convertirTexteEnStagiaire (int position, RandomAccessFile raf) throws IOException {

		raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");
		raf.seek(position * tailleStagiaire);
		String nom, prenom, departement, promo, annee, index = null;

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

		raf.seek((position * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo + tailleAnnee);
		b = new byte[tailleIndex];
		raf.read(b);
		index = new String(b);

		raf.close();

		return new Stagiaire (nom.trim(), prenom.trim(), departement.trim(), promo.trim(), Integer.parseInt(annee.trim()), Integer.parseInt(index.trim()));
	}

	public ArrayList <Stagiaire> rechercherStagiaires (String nomRecherche, String prenomRecherche, String depRecherche, String promoRecherche, String anneeRecherche) {

		listeRecherches = new ArrayList<Stagiaire>();
		if (nomRecherche.equals("") && prenomRecherche.equals("") && depRecherche.equals("") && promoRecherche.equals("") && anneeRecherche.equals("")) {
			System.out.println("rien");
			return listeRecherches;
		}

		for (Stagiaire stagiaire : listeTriee) {

			if ((stagiaire.getNom().equals(nomRecherche) || nomRecherche.equals("")) && (stagiaire.getPrenom().equals(prenomRecherche) || prenomRecherche.equals("")) &&
					(stagiaire.getDepartement().equals(depRecherche) || depRecherche.equals("")) && (stagiaire.getPromo().equals(promoRecherche) || promoRecherche.equals("")) &&
					(String.valueOf(stagiaire.getAnnee()).equals(anneeRecherche) || anneeRecherche.equals(""))) {
				listeRecherches.add(stagiaire);
			}
		}

		return listeRecherches;

	}

	public void gererDemandeAjout (Stagiaire stagiaire) {

		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");

			insererStagiaire(stagiaire, donnerDernierePosition(), 0, raf);

			derniereLigne++;

			listeTriee.clear();

			ajouterListeTriee(0, raf);

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

	public int compterNbStagiaires () {
		return listeTriee.size();
	}

	public int donnerDernierePosition () {
		return derniereLigne;
	}

	public void gererDemandeSuppression (String nomASupprimer, String prenomASupprimer,String depASupprimer,String promoASupprimer,String anneeASupprimer) {
		RandomAccessFile raf = null;
		int positionASupprimer = -1;

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");

			positionASupprimer = chercherPosition (nomASupprimer,prenomASupprimer,depASupprimer,promoASupprimer,anneeASupprimer, 0);

			System.out.println(positionASupprimer);

			if (positionASupprimer == -1) {
				System.out.println("pas trouvé la position, suppression annulée");
				raf.close();
			}

			else {
				supprimerStagiaire (positionASupprimer, raf);

				listeTriee.clear();

				ajouterListeTriee(0, raf);

				raf.close();
			}
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

	public int chercherPosition (String nomASupprimer, String prenomASupprimer,String depASupprimer,String promoASupprimer,String anneeASupprimer, int posAFouiller) {
		RandomAccessFile raf = null;
		String nom, prenom, departement, promo, annee, index, indexFilsGauche, indexFilsDroit = null;
		int posAtrouver = -1;

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");

			raf.seek(posAFouiller * tailleStagiaire);

			byte[] b = new byte[tailleNom];
			raf.read(b);
			nom = new String(b);

			raf.seek((posAFouiller * tailleStagiaire) + tailleNom);
			b = new byte[taillePrenom];
			raf.read(b);
			prenom = new String(b);

			raf.seek((posAFouiller * tailleStagiaire) + tailleNom + taillePrenom);
			b = new byte[tailleDepartement];
			raf.read(b);
			departement = new String(b);

			raf.seek((posAFouiller * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement);
			b = new byte[taillePromo];
			raf.read(b);
			promo = new String(b);

			raf.seek((posAFouiller * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo);
			b = new byte[tailleAnnee];
			raf.read(b);
			annee = new String(b);

			if (nom.trim().equals(nomASupprimer) && prenom.trim().equals(prenomASupprimer) && departement.trim().equals(depASupprimer) &&
					(promo.trim().equals(promoASupprimer) && annee.trim().equals(anneeASupprimer))) {

				raf.seek((posAFouiller * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee);
				b = new byte[tailleIndex];
				raf.read(b);
				index = new String(b);
				posAtrouver = Integer.valueOf(index.trim());

				raf.close();
				return posAtrouver;
			}

			if (nomASupprimer.compareToIgnoreCase(nom.trim()) < 0) {

				raf.seek((posAFouiller * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);
				b = new byte[tailleIndex];
				raf.read(b);
				indexFilsGauche = new String(b);

				if (!indexFilsGauche.trim().equals("null")) {
					return chercherPosition(nomASupprimer, prenomASupprimer, depASupprimer, promoASupprimer, anneeASupprimer, Integer.valueOf(indexFilsGauche.trim()));
				}
			}

			if (nomASupprimer.compareToIgnoreCase(nom.trim()) > 0) {

				raf.seek((posAFouiller * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);
				b = new byte[tailleIndex];
				raf.read(b);
				indexFilsDroit = new String(b);

				if (!indexFilsDroit.trim().equals("null")) {
					return chercherPosition(nomASupprimer, prenomASupprimer, depASupprimer, promoASupprimer, anneeASupprimer, Integer.valueOf(indexFilsDroit.trim()));
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return posAtrouver;
	}

	public void supprimerStagiaire (int positionASupprimer, RandomAccessFile raf) throws IOException {

		raf.seek(positionASupprimer);
		int positionPere = -1;
		int posPereDuPlusProche = -1;
		String filsGauche;
		String filsDroit;
		int plusProche = -1;
		String filsGaucheDuPlusProche;

		// si le stagiaire n'a pas de fils
		if (filsGaucheEstVide(positionASupprimer, raf) == true && filsDroitEstVide(positionASupprimer, raf) == true) {
			positionPere=chercherPere(positionASupprimer, raf);
			ecrireFilsGaucheNull(positionASupprimer, raf);
			ecrireFilsDroitNull(positionASupprimer, raf);
			if (positionPere != -1) {
				supprimerValeurFils(positionPere, positionASupprimer, raf);
			}
		}

		// si stagiaire a un seul fils
		else if (filsGaucheEstVide(positionASupprimer, raf) == false && filsDroitEstVide(positionASupprimer, raf) == true) {
			filsGauche = lireFilsGauche(positionASupprimer, raf).trim();
			positionPere=chercherPere(positionASupprimer, raf);
			if (positionPere != -1) {
				changerValeurFils(positionPere, String.valueOf(positionASupprimer), filsGauche, raf);
			}
			ecrireFilsGaucheNull(positionASupprimer, raf);
			ecrireFilsDroitNull(positionASupprimer, raf);

		}
		else if (filsGaucheEstVide(positionASupprimer, raf) == true && filsDroitEstVide(positionASupprimer, raf) == false) {
			filsDroit = lireFilsDroit(positionASupprimer, raf).trim();
			positionPere=chercherPere(positionASupprimer, raf);
			if (positionPere != -1) {
				changerValeurFils(positionPere, String.valueOf(positionASupprimer), filsDroit, raf);
			}
			ecrireFilsGaucheNull(positionASupprimer, raf);
			ecrireFilsDroitNull(positionASupprimer, raf);

		}

		// si stagiaire a deux fils
		else if (filsGaucheEstVide(positionASupprimer, raf) == false && filsDroitEstVide(positionASupprimer, raf) == false) {

			filsGauche = lireFilsGauche(positionASupprimer, raf).trim();
			filsDroit = lireFilsDroit(positionASupprimer, raf).trim();
			positionPere=chercherPere(positionASupprimer, raf);
			plusProche = trouverPlusProche1(positionASupprimer, raf);
			filsGaucheDuPlusProche = lireFilsGauche(plusProche, raf).trim();
			posPereDuPlusProche=chercherPere(plusProche, raf);

			if (posPereDuPlusProche == positionASupprimer) {
				changerDeuxFils (plusProche, filsGaucheDuPlusProche, filsDroit, raf);
			}
			else {
				changerDeuxFils (plusProche, filsGauche, filsDroit, raf);
			}
			if (!filsGaucheDuPlusProche.trim().equals("null")) {
				ecrireFilsDroit(Integer.parseInt(filsGaucheDuPlusProche.trim()), posPereDuPlusProche, tailleIndex, raf);
			}
			else {
				supprimerValeurFils(posPereDuPlusProche, plusProche, raf);
			}
			if (positionPere != -1) {
				changerValeurFils(positionPere, String.valueOf(positionASupprimer), String.valueOf(plusProche), raf);
			}

			ecrireFilsGaucheNull(positionASupprimer, raf);
			ecrireFilsDroitNull(positionASupprimer, raf);



			System.out.println("position " +positionASupprimer+ " fg " +filsGauche+ " fd " +filsDroit+ " pos pere "+positionPere+ " plus proche " +plusProche+ " pere du plus proche " +posPereDuPlusProche);


		}

		//	positionPere=chercherPere(positionASupprimer, raf);
		//	if (positionPere == -1) {
		//posDepartFichier = plusProche;
		/*System.out.println("on change de départ");
			changerPointDepart(raf);*/
		//	}

	}

	public void changerPointDepart (RandomAccessFile raf) throws IOException {

		int i;
		for (i=0; i<=derniereLigne; i++) {
			if (chercherPere(i, raf) != -1) {
				continue;
			}
		}
		posDepartFichier = i;
	}

	public int chercherPere (int position, RandomAccessFile raf) throws IOException {
		String indexFilsGauche = null;
		String indexFilsDroit = null;
		String index = null;
		int positionPere = -1;

		for (int i=0; i<nbStagiaires; i++) {
			raf.seek((i * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);
			byte[] b = new byte[tailleIndex];
			raf.read(b);
			indexFilsGauche = new String(b);
			raf.seek((i * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);
			b = new byte[tailleIndex];
			raf.read(b);
			indexFilsDroit = new String(b);

			if (String.valueOf(position).equals(indexFilsGauche.trim()) || String.valueOf(position).equals(indexFilsDroit.trim())) {
				raf.seek((i * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee);
				b = new byte[tailleIndex];
				raf.read(b);
				index = new String(b);

				positionPere = Integer.parseInt(index.trim());

			}
		}

		return positionPere;
	}

	public void supprimerValeurFils (int index, int indexFils, RandomAccessFile raf) throws IOException {
		String indexFilsGauche = null;
		String indexFilsDroit = null;
		raf.seek((index * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);
		byte[] b = new byte[tailleIndex];
		raf.read(b);
		indexFilsGauche = new String(b);
		raf.seek((index * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);
		b = new byte[tailleIndex];
		raf.read(b);
		indexFilsDroit = new String(b);

		if (String.valueOf(indexFils).equals(indexFilsGauche.trim())) {
			raf.seek((index * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);
			ecrireChamp("null", tailleIndex, raf);
		}

		else if (String.valueOf(indexFils).equals(indexFilsDroit.trim())) {
			raf.seek((index * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);
			ecrireChamp("null", tailleIndex, raf);
		}
	}

	public void changerValeurFils (int indexPere, String chaineIndexSupprime, String chaineNouveauFils, RandomAccessFile raf) throws IOException {
		String indexFilsGauche = null;
		String indexFilsDroit = null;
		raf.seek((indexPere * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);
		byte[] b = new byte[tailleIndex];
		raf.read(b);
		indexFilsGauche = new String(b);
		raf.seek((indexPere * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);
		b = new byte[tailleIndex];
		raf.read(b);
		indexFilsDroit = new String(b);

		if (chaineIndexSupprime.equals(indexFilsGauche.trim())) {
			raf.seek((indexPere * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);
			ecrireChamp(chaineNouveauFils, tailleIndex, raf);
		}

		else if (chaineIndexSupprime.equals(indexFilsDroit.trim())) {
			raf.seek((indexPere * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);
			ecrireChamp(chaineNouveauFils, tailleIndex, raf);
		}

	}

	public int trouverPlusProche1 (int indexSupprime, RandomAccessFile raf) throws IOException {
		int posFilsGauche = Integer.parseInt(lireFilsGauche(indexSupprime, raf).trim());

		raf.seek((Integer.parseInt(lireFilsGauche(indexSupprime, raf).trim()) * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex);

		if (filsDroitEstVide(posFilsGauche, raf)) {
			return posFilsGauche;
		}

		int position = trouverPlusProche2 (posFilsGauche, raf);
		return position;

	}

	public int trouverPlusProche2 (int index, RandomAccessFile raf) throws IOException {

		if (lireFilsDroit(index, raf).trim().equals("null")) {
			return Integer.parseInt(lirePosition(index, raf).trim());
		}

		int posFilsDroit = Integer.parseInt(lireFilsDroit(index, raf).trim());
		raf.seek((Integer.parseInt(lireFilsDroit(index, raf).trim()) * tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement + taillePromo +tailleAnnee+tailleIndex+tailleIndex);

		return trouverPlusProche2(posFilsDroit, raf);
	}

	public void changerDeuxFils (int index, String indexFilsGauche, String indexFilsDroit, RandomAccessFile raf) throws IOException {
		if (indexFilsGauche.equals("null")) {
			ecrireFilsGaucheNull(index, raf);
		}
		else {
			ecrireFilsGauche(Integer.parseInt(indexFilsGauche.trim()), index, tailleIndex, raf);
		}
		if (indexFilsDroit.equals("null")) {
			ecrireFilsDroitNull(index, raf);
		}
		else {
			ecrireFilsDroit(Integer.parseInt(indexFilsDroit.trim()), index, tailleIndex, raf);
		}
	}

	public void ecrireFilsGaucheNull (int index, RandomAccessFile raf) throws IOException {
		raf.seek((index*tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement+ taillePromo +tailleAnnee+tailleIndex);

		String champEntier = "null";
		byte[] b = champEntier.getBytes();
		raf.write(b);
	}

	public void ecrireFilsDroitNull (int index, RandomAccessFile raf) throws IOException {
		raf.seek((index*tailleStagiaire) + tailleNom + taillePrenom + tailleDepartement+ taillePromo +tailleAnnee+tailleIndex+tailleIndex);

		String champEntier = "null";
		byte[] b = champEntier.getBytes();
		raf.write(b);
	}

	public void gererDemandeSuppression2 (int posASupprimer) {
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(CHEMIN_FICHIER_STRUCTURE, "rw");

			supprimerStagiaire (posASupprimer, raf);

			System.out.println("suppression réussie");

			listeTriee.clear();

			ajouterListeTriee(posDepartFichier, raf);

			System.out.println("nombre stagiaires :" +listeTriee.size());

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
}



































