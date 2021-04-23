package fr.eql.ai109.annuaire;

public class Noeud {

	private Stagiaire stagiaireNoeudCourant;
	private int position;
	private Noeud filsGauche;
	private Noeud filsDroit;
	
	
	public Noeud() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Noeud(Stagiaire stagiaireNoeudCourant, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.stagiaireNoeudCourant = stagiaireNoeudCourant;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	public Stagiaire getStagiaireNoeudCourant() {
		return stagiaireNoeudCourant;
	}

	public void setStagiaireNoeudCourant(Stagiaire stagiaireNoeudCourant) {
		this.stagiaireNoeudCourant = stagiaireNoeudCourant;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

/*	public void ajouterElement (Stagiaire stagiaire, Noeud noeud) {
		if (noeud == null) {
			return new Noeud (stagiaire, null, null);
		}
		else {
			if (stagiaire.getNom().compareToIgnoreCase(stagiaireNoeudCourant.getNom()) < 0) {
				filsGauche = ajouterElement(stagiaire, noeud.getFilsGauche());
			}
			else if (stagiaire.getNom().compareToIgnoreCase(stagiaireNoeudCourant.getNom()) > 0) {
				filsDroit = ajouterElement(stagiaire, noeud.getFilsDroit());
			}
			else {
				System.out.println("deja ajoute");
			}
			return noeud;
		}
	}*/
}
