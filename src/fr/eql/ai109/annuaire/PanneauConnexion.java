package fr.eql.ai109.annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class PanneauConnexion  extends GridPane{

	private Label lblIdentifiant;
	private Label lblMdp;
	private Label lblMessageConnexion;

	private TextField txtIdentifiant;
	private PasswordField  txtMdp;

	private Button btnConnexion;
	private Button btnDeconnexion;
	

	PanneauConnexion(){


		lblIdentifiant= new Label("Identifiant : ");
		lblIdentifiant.setFont(Font.font("Aharoni", 14));
		txtIdentifiant = new TextField();		

		lblMessageConnexion = new Label("Utilisateur");
		lblMessageConnexion.setFont(Font.font("Aharoni", 14));
		
		lblMdp= new Label("Mot de passe : ");
		lblMdp.setFont(Font.font("Aharoni", 14));
		txtMdp = new PasswordField();
		
		addRow(1, lblMdp, txtMdp);

		btnConnexion = new Button("Connexion");
		btnConnexion.setPrefSize(100, 70);

		btnDeconnexion = new Button("Déconnexion");
		btnDeconnexion.setPrefSize(100, 20);
		btnDeconnexion.setVisible(false);
		
		addRow(0, lblIdentifiant, txtIdentifiant, lblMessageConnexion, btnDeconnexion);
		addRow(2, btnConnexion);

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
					lblMessageConnexion.setStyle("-fx-text-fill: red;  -fx-font-size: 12pt;");
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
					}
				});
			}

		}
