package fr.eql.ai109.annuaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PanneauGrille  extends AnchorPane{

	public StagiaireDao dao = new StagiaireDao();
	public ObservableList<Stagiaire> observableStagiaires = FXCollections.observableArrayList(dao.getListeTriee());
	public TableView<Stagiaire> tableView = new TableView<Stagiaire>(observableStagiaires);
	
	
	@SuppressWarnings("unchecked")
	public PanneauGrille() {
		super();
		
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

}
