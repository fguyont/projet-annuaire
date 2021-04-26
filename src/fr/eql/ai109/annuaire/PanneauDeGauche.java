package fr.eql.ai109.annuaire;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;

public class PanneauDeGauche extends BorderPane{

	private PanneauFormulaire panneauFormulaire = new PanneauFormulaire();
	private PanneauConnexion panneauConnexion = new PanneauConnexion();
	
	public PanneauDeGauche() {
		setTop(panneauConnexion);
		setCenter(panneauFormulaire);
		
		
		setStyle("-fx-background-color: cyan");
		setPrefSize(500, BASELINE_OFFSET_SAME_AS_HEIGHT);
	}

	public PanneauFormulaire getPanneauFormulaire() {
		return panneauFormulaire;
	}

	public void setPanneauFormulaire(PanneauFormulaire panneauFormulaire) {
		this.panneauFormulaire = panneauFormulaire;
	}
	
}
