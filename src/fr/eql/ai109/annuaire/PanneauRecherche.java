package fr.eql.ai109.annuaire;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class PanneauRecherche extends HBox{

	public StagiaireDao stagiaireDao = new StagiaireDao();
	public ObservableList<Stagiaire> observableStagiaires;
	public TableView<Stagiaire> tableView;
	
	public PanneauRecherche () {
		
		observableStagiaires = FXCollections.observableArrayList(stagiaireDao.getListeRecherches());
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
		
		setPrefSize(200, 200);
		
	}
	
	
	




	public StagiaireDao getStagiaireDao() {
		return stagiaireDao;
	}







	public void setStagiaireDao(StagiaireDao stagiaireDao) {
		this.stagiaireDao = stagiaireDao;
	}







	public ObservableList<Stagiaire> getObservableStagiaires() {
		return observableStagiaires;
	}







	public void setObservableStagiaires(ObservableList<Stagiaire> observableStagiaires) {
		this.observableStagiaires = observableStagiaires;
	}







	public void setObservableStagiaires(ArrayList<Stagiaire> listeAffichee) {
		this.observableStagiaires = FXCollections.observableArrayList(listeAffichee);
	}
}
