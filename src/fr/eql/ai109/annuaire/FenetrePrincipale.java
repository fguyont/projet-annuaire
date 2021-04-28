package fr.eql.ai109.annuaire;

import javafx.scene.layout.BorderPane;

// 
public class FenetrePrincipale extends BorderPane{
	
	private PanneauGrille panneauGrille = new PanneauGrille();
	private PanneauDeGauche panneauDeGauche = new PanneauDeGauche();
	private PanneauRecherche panneauRecherche = new PanneauRecherche();
	
	public FenetrePrincipale() {
		super();

		setRight(panneauGrille);
		setLeft(panneauDeGauche);
		//setBottom(panneauRecherche);
		
	}

	public PanneauRecherche getPanneauRecherche() {
		return panneauRecherche;
	}

	public void setPanneauRecherche(PanneauRecherche panneauRecherche) {
		this.panneauRecherche = panneauRecherche;
	}

	public PanneauGrille getPanneauGrille() {
		return panneauGrille;
	}

	public void setPanneauGrille(PanneauGrille panneauGrille) {
		this.panneauGrille = panneauGrille;
	}

	public PanneauDeGauche getPanneauDeGauche() {
		return panneauDeGauche;
	}

	public void setPanneauDeGauche(PanneauDeGauche panneauDeGauche) {
		this.panneauDeGauche = panneauDeGauche;
	}

	

}
