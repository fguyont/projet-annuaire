package fr.eql.ai109.annuaire;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PanneauGrille  extends AnchorPane{

	public StagiaireDao dao = new StagiaireDao();
	public ObservableList<Stagiaire> observableStagiaires;
	public TableView<Stagiaire> tableView;
	
	

	public TableView<Stagiaire> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Stagiaire> tableView) {
		this.tableView = tableView;
	}
	
	

	public ObservableList<Stagiaire> getObservableStagiaires() {
		return observableStagiaires;
	}

	public void setObservableStagiaires(ArrayList<Stagiaire> listeAffichee) {
		this.observableStagiaires = FXCollections.observableArrayList(listeAffichee);
	}

	@SuppressWarnings("unchecked")
	public PanneauGrille() {
		super();
		
		observableStagiaires = FXCollections.observableArrayList(dao.getListeTriee());
		tableView = new TableView<Stagiaire>(observableStagiaires);
		
		
		TableColumn<Stagiaire, String> colNom = new TableColumn<>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colNom.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colPrenom = new TableColumn<>("Prénom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colPrenom.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colDepartement = new TableColumn<>("Département");
		colDepartement.setCellValueFactory(new PropertyValueFactory<>("departement"));
		colDepartement.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colPromo = new TableColumn<>("Promotion");
		colPromo.setCellValueFactory(new PropertyValueFactory<>("promo"));
		colPromo.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, Integer> colAnnee = new TableColumn<>("Année");
		colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
		colAnnee.setStyle("-fx-alignment: CENTER");
		
		tableView.getColumns().addAll(colNom, colPrenom, colDepartement, colPromo, colAnnee);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getChildren().add(tableView);
		
		setPrefSize(700, 200);
		AnchorPane.setTopAnchor(tableView, 10.);
		AnchorPane.setLeftAnchor(tableView, 10.);
		AnchorPane.setRightAnchor(tableView, 10.);
		AnchorPane.setBottomAnchor(tableView, 10.);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {

            @Override
            public void changed(ObservableValue<? extends Stagiaire> observable, Stagiaire oldValue, Stagiaire newValue) {
                FenetrePrincipale root = (FenetrePrincipale) PanneauGrille.this.getScene().getRoot();
                PanneauFormulaire panneauFormulaire = root.getPanneauDeGauche().getPanneauFormulaire();
                if(newValue != null){
                    panneauFormulaire.getTxtNom().setText(newValue.getNom());
                    panneauFormulaire.getTxtPrenom().setText(newValue.getPrenom());
                    panneauFormulaire.getTxtDepartement().setText(newValue.getDepartement());
                    panneauFormulaire.getTxtPromo().setText(newValue.getPromo());
                    panneauFormulaire.getTxtAnnee().setText(Integer.toString(newValue.getAnnee()));
                }
            }
        });
	}
	
	public PanneauGrille (ArrayList <Stagiaire> listeAffichee) {
		
		
		
		observableStagiaires = FXCollections.observableArrayList(listeAffichee);
		tableView = new TableView<Stagiaire>(observableStagiaires);
		
		tableView.setEditable(true);
		
		TableColumn<Stagiaire, String> colNom = new TableColumn<>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colNom.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colPrenom = new TableColumn<>("Prénom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colPrenom.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colDepartement = new TableColumn<>("Département");
		colDepartement.setCellValueFactory(new PropertyValueFactory<>("departement"));
		colDepartement.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colPromo = new TableColumn<>("Promotion");
		colPromo.setCellValueFactory(new PropertyValueFactory<>("promo"));
		colPromo.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, Integer> colAnnee = new TableColumn<>("Année");
		colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
		colAnnee.setStyle("-fx-alignment: CENTER");
		
		tableView.getColumns().addAll(colNom, colPrenom, colDepartement, colPromo, colAnnee);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getChildren().add(tableView);
		
		setPrefSize(800, 200);
		AnchorPane.setTopAnchor(tableView, 10.);
		AnchorPane.setLeftAnchor(tableView, 10.);
		AnchorPane.setRightAnchor(tableView, 10.);
		AnchorPane.setBottomAnchor(tableView, 10.);
	}
	
	public TableView recupererTable () {
		
		observableStagiaires = FXCollections.observableArrayList(dao.getListeRecherches());
		tableView = new TableView<Stagiaire>(observableStagiaires);
		
		TableColumn<Stagiaire, String> colNom = new TableColumn<>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colNom.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colPrenom = new TableColumn<>("Prénom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colPrenom.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colDepartement = new TableColumn<>("Département");
		colDepartement.setCellValueFactory(new PropertyValueFactory<>("departement"));
		colDepartement.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, String> colPromo = new TableColumn<>("Promotion");
		colPromo.setCellValueFactory(new PropertyValueFactory<>("promo"));
		colPromo.setStyle("-fx-alignment: CENTER");
		
		TableColumn<Stagiaire, Integer> colAnnee = new TableColumn<>("Année");
		colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
		colAnnee.setStyle("-fx-alignment: CENTER");
		
		tableView.getColumns().addAll(colNom, colPrenom, colDepartement, colPromo, colAnnee);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getChildren().add(tableView);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {

            @Override
            public void changed(ObservableValue<? extends Stagiaire> observable, Stagiaire oldValue, Stagiaire newValue) {
                FenetrePrincipale root = (FenetrePrincipale) PanneauGrille.this.getScene().getRoot();
                PanneauFormulaire panneauFormulaire = root.getPanneauDeGauche().getPanneauFormulaire();
                if(newValue != null){
                    panneauFormulaire.getTxtNom().setText(newValue.getNom());
                    panneauFormulaire.getTxtPrenom().setText(newValue.getPrenom());
                    panneauFormulaire.getTxtDepartement().setText(newValue.getDepartement());
                    panneauFormulaire.getTxtPromo().setText(newValue.getPromo());
                    panneauFormulaire.getTxtAnnee().setText(Integer.toString(newValue.getAnnee()));
                }
            }
        });
		
		return tableView;
	}
	
	public void rafraichirTable () {
		setPrefSize(1000, 1000);
		setTopAnchor(recupererTable(), 10.);
		setLeftAnchor(recupererTable(), 10.);
		setRightAnchor(recupererTable(), 10.);
		setBottomAnchor(recupererTable(), 10.);
	}

}
