package fr.eql.ai109.annuaire;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanneauFormulaire extends GridPane{

	private Label lblNom;
	private Label lblPrenom;
	private Label lblDepartement;
	private Label lblPromo;
	private Label lblAnnee;
	
	private TextField txtNom;
	private TextField txtPrenom;
	private TextField txtDepartement;
	private TextField txtPromo;
	private TextField txtAnnee;


	private Button btnEnregistrer;
	private Button btnEffacer;
	private Button btnMAJ;
	private Button btnRechercher;
	
	private VBox btnBox;

	public PanneauFormulaire() {

		lblNom= new Label("Nom : ");
		txtNom = new TextField();		
		addRow(10, lblNom, txtNom);

		lblPrenom= new Label("Pr�nom : ");
		txtPrenom = new TextField();		
		addRow(11, lblPrenom, txtPrenom);

		lblDepartement= new Label("D�partement : ");
		txtDepartement = new TextField();		
		addRow(12, lblDepartement, txtDepartement);


		lblPromo= new Label("Promotion : ");
		txtPromo = new TextField();		
		addRow(13, lblPromo, txtPromo);


		lblAnnee= new Label("Ann�e : ");
		txtAnnee = new TextField();		
		
		
		btnRechercher = new Button("Rechercher");
		btnRechercher.setPrefSize(150, 80);

		
		
		btnEnregistrer = new Button("Enregistrer");
		btnEnregistrer.setPrefSize(150, 80);
		
		btnEffacer = new Button("Effacer");
		btnEffacer.setPrefSize(150, 80);
		
		btnMAJ = new Button("Mettre � jour");
		btnMAJ.setPrefSize(150, 80);
		
		// Boutons � rendre invisible
		btnEffacer.setVisible(false);
		btnMAJ.setVisible(false);
		
		
		
		btnBox = new VBox(60); // Espace entre les boutons
		btnBox.getChildren().addAll(btnRechercher, btnEnregistrer, btnEffacer, btnMAJ);
		btnBox.setAlignment(Pos.BASELINE_RIGHT);
		//add(btnBox, 0, 10, 2, 1);
		addRow(14, lblAnnee, txtAnnee);
		addRow(15, btnRechercher, btnEnregistrer);
		addRow(16,btnEffacer, btnMAJ);
		//addColumn(2, btnRechercher, btnEnregistrer, btnEffacer, btnMAJ);
		
		
		setVgap(10); // Taille entre les lignes des champs texte
		setHgap(20); // Largeur de la GridPane
		
		
		btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				StagiaireDao stagiaireDao = new StagiaireDao();
				PanneauGrille panneauGrille = new PanneauGrille ();
				
		
				FenetrePrincipale fenetrePrinc = (FenetrePrincipale) getScene().getRoot();

// ligne 101 : fenetrePrinc.getPanneauGrille().getObservableStagiaires().add(stagiaire);
                
				
			/*	stagiaireDao.rechercherStagiaires(txtNom.getText().trim(), txtPrenom.getText().trim(), txtDepartement.getText().trim(), txtPromo.getText().trim(), txtAnnee.getText().trim());
		*/
				fenetrePrinc.getPanneauRecherche().getObservableStagiaires().clear();
				fenetrePrinc.getPanneauRecherche().getObservableStagiaires().addAll(stagiaireDao.rechercherStagiaires(txtNom.getText().trim(), txtPrenom.getText().trim(), txtDepartement.getText().trim(), txtPromo.getText().trim(), txtAnnee.getText().trim()));
				
				//fenetrePrinc.getPanneauGrille().getStagiaireDao().ajouterBouton(stagiaire);
	
			  //  fenetrePrinc.getPanneauRecherche().getStagiaireDao().rechercherStagiaires(txtNom.getText().trim(), txtPrenom.getText().trim(), txtDepartement.getText().trim(), txtPromo.getText().trim(), txtAnnee.getText().trim());
				
				
			}
			
		});
		
		btnEnregistrer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String departement = txtDepartement.getText();
                String promo = txtPromo.getText();
                String annee = txtAnnee.getText();

                Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promo, Integer.parseInt(annee));
                
                StagiaireDao stagiaireDao = new StagiaireDao();
                
                stagiaireDao.gererDemandeAjout (stagiaire);

                FenetrePrincipale fenetrePrinc = (FenetrePrincipale) getScene().getRoot();

                fenetrePrinc.getPanneauGrille().getObservableStagiaires().clear();
				fenetrePrinc.getPanneauGrille().getObservableStagiaires().addAll(stagiaireDao.getListeTriee());
                

           //     fenetrePrinc.getPanneauGrille().getObservableStagiaires().add(stagiaire);

                //StagiaireDao stagiaireDao = new StagiaireDao();
/*
                try {
                    //RandomAccessFile raf = new RandomAccessFile("C:\Users\formation\Desktop\EQL\Projet-1\annuaireStructure.txt", "rw");
                    //stagiaireDao.insererStagiaire(stagiaire, stagiaireDao.getNbStagiaires(), 0, raf);

                    fenetrePrinc.getPanneauGrille().getStagiaireDao().ajouterBouton(stagiaire);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


            }
        });
		
	}

	public TextField getTxtNom() {
		return txtNom;
	}

	public void setTxtNom(TextField txtNom) {
		this.txtNom = txtNom;
	}

	public TextField getTxtPrenom() {
		return txtPrenom;
	}

	public void setTxtPrenom(TextField txtPrenom) {
		this.txtPrenom = txtPrenom;
	}

	public TextField getTxtDepartement() {
		return txtDepartement;
	}

	public void setTxtDepartement(TextField txtDepartement) {
		this.txtDepartement = txtDepartement;
	}

	public TextField getTxtPromo() {
		return txtPromo;
	}

	public void setTxtPromo(TextField txtPromo) {
		this.txtPromo = txtPromo;
	}

	public TextField getTxtAnnee() {
		return txtAnnee;
	}

	public void setTxtAnnee(TextField txtAnnee) {
		this.txtAnnee = txtAnnee;
	}

	public Button getBtnEffacer() {
		return btnEffacer;
	}

	public void setBtnEffacer(Button btnEffacer) {
		this.btnEffacer = btnEffacer;
	}

	public Button getBtnMAJ() {
		return btnMAJ;
	}

	public void setBtnMAJ(Button btnMAJ) {
		this.btnMAJ = btnMAJ;
	}

	

}