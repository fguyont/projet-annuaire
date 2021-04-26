package fr.eql.ai109.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
//aClique.setMaxWidth(120);  Taille du texte
public class PanneauConnexion  extends GridPane{

	private Label lblIdentifiant;
	private Label lblMdp;
	private Label lblMessageConnexion;

	private TextField txtIdentifiant;
	private TextField txtMdp;

	private Button btnConnexion;
	private Button btnDeconnexion;



	PanneauConnexion(){


		lblIdentifiant= new Label("Identifiant : ");
		txtIdentifiant = new TextField();		


		lblMessageConnexion = new Label("Utilisateur");
		addRow(0, lblIdentifiant, txtIdentifiant, lblMessageConnexion);

		lblMdp= new Label("Mot de passe : ");
		txtMdp = new TextField();		
		addRow(1, lblMdp, txtMdp);

		btnConnexion = new Button("Connexion");
		btnConnexion.setPrefSize(100, 70);

		btnDeconnexion = new Button("Déconnexion");
		btnDeconnexion.setPrefSize(100, 70);
		btnDeconnexion.setVisible(false);

		addRow(2, btnConnexion, btnDeconnexion);


		setHgap(20);
		
		btnConnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(txtIdentifiant.getText().equals("Admin") && txtMdp.getText().equals("MDPAdmin")) {

					FenetrePrincipale fenetrePrinc = (FenetrePrincipale) getScene().getRoot();
					fenetrePrinc.getPanneauDeGauche().getPanneauFormulaire().getBtnEffacer().setVisible(true);
					fenetrePrinc.getPanneauDeGauche().getPanneauFormulaire().getBtnMAJ().setVisible(true);
					btnDeconnexion.setVisible(true);
					btnConnexion.setVisible(false);
					lblIdentifiant.setVisible(false);
					txtIdentifiant.setVisible(false);
					lblMdp.setVisible(false);
					txtMdp.setVisible(false);
					
					lblMessageConnexion.setText("Administrateur");

				} else {
					lblMessageConnexion.setText("Erreur de connexion");
				}

			}});
		
				btnDeconnexion.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						FenetrePrincipale fenetrePrinc = (FenetrePrincipale) getScene().getRoot();
						fenetrePrinc.getPanneauDeGauche().getPanneauFormulaire().getBtnEffacer().setVisible(false);
						fenetrePrinc.getPanneauDeGauche().getPanneauFormulaire().getBtnMAJ().setVisible(false);
						btnDeconnexion.setVisible(false);
						btnConnexion.setVisible(true);
						lblIdentifiant.setVisible(true);
						txtIdentifiant.setVisible(true);
						lblMdp.setVisible(true);
						txtMdp.setVisible(true);

						lblMessageConnexion.setText("Utilisateur");



						// ligne 101 : fenetrePrinc.getPanneauGrille().getObservableStagiaires().add(stagiaire);


						/*	stagiaireDao.rechercherStagiaires(txtNom.getText().trim(), txtPrenom.getText().trim(), txtDepartement.getText().trim(), txtPromo.getText().trim(), txtAnnee.getText().trim());
						 */
						//			fenetrePrinc.getPanneauRecherche().getObservableStagiaires().clear();
						//			fenetrePrinc.getPanneauRecherche().getObservableStagiaires().addAll(stagiaireDao.rechercherStagiaires(txtNom.getText().trim(), txtPrenom.getText().trim(), txtDepartement.getText().trim(), txtPromo.getText().trim(), txtAnnee.getText().trim()));

						//fenetrePrinc.getPanneauGrille().getStagiaireDao().ajouterBouton(stagiaire);

						//  fenetrePrinc.getPanneauRecherche().getStagiaireDao().rechercherStagiaires(txtNom.getText().trim(), txtPrenom.getText().trim(), txtDepartement.getText().trim(), txtPromo.getText().trim(), txtAnnee.getText().trim());


					}

				});

			}



		}
