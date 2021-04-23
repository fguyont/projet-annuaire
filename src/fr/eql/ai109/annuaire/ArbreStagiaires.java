package fr.eql.ai109.annuaire;

public class ArbreStagiaires {

	private Noeud root;
	
	
	
	public Noeud getRoot() {
		return root;
	}

	public void setRoot(Noeud root) {
		this.root = root;
	}

	public Noeud addRecursive (Noeud current, Stagiaire stagiaire) {
		if (current == null) {
			return new Noeud (stagiaire, null, null);
		}
		
		if ((stagiaire.getNom().compareToIgnoreCase(current.getStagiaireNoeudCourant().getNom()) < 0)) {
			current.setFilsGauche(addRecursive(current.getFilsGauche(), stagiaire));
		} else if ((stagiaire.getNom().compareToIgnoreCase(current.getStagiaireNoeudCourant().getNom()) > 0)) {
			current.setFilsDroit(addRecursive(current.getFilsDroit(), stagiaire));
		}
		else {
			return current;
		}
		
		return current;
	}
	
	public void add (Stagiaire stagiaire) {
		root = addRecursive(root, stagiaire);
	}
	
	public void traverseInOrder(Noeud node) {
	    if (node != null) {
	        traverseInOrder(node.getFilsGauche());
	        System.out.print(" " + node.getStagiaireNoeudCourant().getNom());
	        traverseInOrder(node.getFilsDroit());
	    }
	}
	
	
}
