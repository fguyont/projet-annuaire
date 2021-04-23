package fr.eql.ai109.annuaire;

public class ArbreAnnuaire {

	private Noeud racine;

	public Noeud ajoutElement (Stagiaire stagiaire, Noeud noeudCourant) {
		if (noeudCourant == null) {
			return new Noeud (stagiaire, null, null);		
		}
		if ((stagiaire.getNom().compareToIgnoreCase(noeudCourant.getStagiaireNoeudCourant().getNom()) < 0)) {
			noeudCourant.setFilsGauche(ajoutElement(stagiaire, noeudCourant.getFilsGauche()));
		}
		else if ((stagiaire.getNom().compareToIgnoreCase(noeudCourant.getStagiaireNoeudCourant().getNom()) > 0)) {
			noeudCourant.setFilsDroit(ajoutElement(stagiaire, noeudCourant.getFilsDroit()));
		}
		else {
			return noeudCourant;
		}	
		return noeudCourant;
	}

	public void add(Stagiaire stagiaire) {
		racine = ajoutElement(stagiaire, racine);
	}


}
