package fr.eql.ai109.annuaire;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PanneauGrille  extends AnchorPane{

	public StagiaireDao dao = new StagiaireDao();
	public ObservableList<Stagiaire> observableStagiaires;
	public TableView<Stagiaire> tableView;
	private static int posPointee =-1;
	

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
		
		TableColumn<Stagiaire, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("position"));
		colId.setStyle("-fx-alignment: CENTER");
		
		tableView.getColumns().addAll(colNom, colPrenom, colDepartement, colPromo, colAnnee, colId);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		getChildren().add(tableView);
		
		setPrefSize(650, 200);
		
		setTopAnchor(tableView, 10.);
		setLeftAnchor(tableView, 10.);
		setRightAnchor(tableView, 10.);
		setBottomAnchor(tableView, 10.);
		
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
                    posPointee = newValue.getPosition();
                }
            }
        });
	}

	public int getPosPointee() {
		return posPointee;
	}

	public void setPosPointee(int posPointee) {
		PanneauGrille.posPointee = posPointee;
	}
	

}
