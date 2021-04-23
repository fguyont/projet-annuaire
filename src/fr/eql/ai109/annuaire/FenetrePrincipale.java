package fr.eql.ai109.annuaire;

import javafx.scene.layout.BorderPane;

// 
public class FenetrePrincipale extends BorderPane{
	
	private PanneauGrille panneauGrille = new PanneauGrille();
	
	public FenetrePrincipale() {
		super();

		setRight(panneauGrille);
	}



}
