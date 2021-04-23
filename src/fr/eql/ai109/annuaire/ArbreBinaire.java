package fr.eql.ai109.annuaire;

public class ArbreBinaire {

	private String nom;
	private Stagiaire stagiaire;
	private ArbreBinaire filsGauche;
	private ArbreBinaire filsDroit;


	public ArbreBinaire() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ArbreBinaire(Stagiaire stagiaire, ArbreBinaire filsGauche, ArbreBinaire filsDroit) {
		super();
		this.setStagiaire(stagiaire);
		this.setFilsGauche(filsGauche);
		this.setFilsDroit(filsDroit);
	}


	public ArbreBinaire(String nom, Stagiaire stagiaire, ArbreBinaire filsGauche, ArbreBinaire filsDroit) {
		super();
		this.nom = nom;
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	// Affichage de l'arbre
	public void afficherArbre(ArbreBinaire ab) {
		if (ab != null) {
			afficherArbre(ab.getFilsGauche());
			System.out.print(" " + ab.getStagiaire().getNom());
			afficherArbre(ab.getFilsDroit());
		}
	}


	public ArbreBinaire getFilsGauche() {
		return filsGauche;
	}


	public void setFilsGauche(ArbreBinaire filsGauche) {
		this.filsGauche = filsGauche;
	}


	public Stagiaire getStagiaire() {
		return stagiaire;
	}


	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}


	public ArbreBinaire getFilsDroit() {
		return filsDroit;
	}


	public void setFilsDroit(ArbreBinaire filsDroit) {
		this.filsDroit = filsDroit;
	}


	// Recherche d'un élément
	public void recherche(String cle, ArbreBinaire ab, boolean trouve) {
		if(ab==null) {
			trouve = false;	
			//System.out.println(cle + " ne fait pas partie de l'annuaire");
		} else {
			if(cle.compareToIgnoreCase(ab.stagiaire.getNom())<0) {
				recherche(cle, ab.getFilsGauche(), trouve);
			}else {
				if(cle.compareToIgnoreCase(ab.stagiaire.getNom())>0) {
					recherche(cle, ab.getFilsDroit(), trouve); 
				} else {
					trouve = true;		
					System.out.println(ab.stagiaire);
				}
			}
		}
	}


	// Ajout d'un élément
	public ArbreBinaire ajout(Stagiaire s, ArbreBinaire ab, boolean existeDeja) {
	//	recherche(s.getNom(), ab, existeDeja);
		if(ab==null) {
			existeDeja=false;
			ab = new ArbreBinaire(s, null, null);

		} else {
			if(existeDeja==false && s.getNom().compareToIgnoreCase(ab.getStagiaire().getNom())<0) {
				ab.setFilsGauche(ajout(s, ab.getFilsGauche(), existeDeja));
			} else {
				if(existeDeja==false && s.getNom().compareToIgnoreCase(ab.getStagiaire().getNom())>0){
					ab.setFilsDroit(ajout(s, ab.getFilsDroit(), existeDeja));
				} else {
					existeDeja=true;
				}
			}
		}
		return ab;
	}

	// Suppression d'un élément




}
