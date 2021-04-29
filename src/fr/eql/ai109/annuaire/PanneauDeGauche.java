package fr.eql.ai109.annuaire;


import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PanneauDeGauche extends BorderPane{

	private PanneauFormulaire panneauFormulaire = new PanneauFormulaire();
	private PanneauConnexion panneauConnexion = new PanneauConnexion();
	
	
    String imageUrl = "https://moodle.ecoleql.fr/pluginfile.php/1/theme_eguru/logo/1617206969/index.png";
    Image image = new Image(imageUrl,160,100,true,true);
     
    // Create the ImageView
    ImageView imageView = new ImageView(image);
     
    // Create the HBox      
    HBox root = new HBox();

	
	public PanneauDeGauche() {
		setTop(panneauConnexion);
		setCenter(panneauFormulaire);
		setMargin(panneauFormulaire, new Insets(10, 10, 10, 30));
		setBottom(root);
		setMargin(root, new Insets(0, 10, 10, 80));
		
	    // Add Children to the HBox
	    root.getChildren().add(imageView);
	    root.setStyle("-fx-padding: 110;");
	    root.setPrefSize(350, 50);
	    
	    
		setStyle("-fx-background-color: lightskyblue");
		setPrefSize(550, BASELINE_OFFSET_SAME_AS_HEIGHT);
	}

	public PanneauFormulaire getPanneauFormulaire() {
		return panneauFormulaire;
	}

	public void setPanneauFormulaire(PanneauFormulaire panneauFormulaire) {
		this.panneauFormulaire = panneauFormulaire;
	}
	
}
