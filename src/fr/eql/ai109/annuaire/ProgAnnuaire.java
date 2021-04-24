package fr.eql.ai109.annuaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProgAnnuaire extends Application {

		@Override
		public void start(Stage stage) throws Exception {		
			
			FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
			
			stage.setTitle("Annuaire");
			
			Scene scene = new Scene(fenetrePrincipale);
			
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		StagiaireDao stagiaireDao = new StagiaireDao();
		stagiaireDao.lireFichierStagiaire();
		stagiaireDao.determinerTaillesMax();
		stagiaireDao.creerFichierStructure();
		//stagiaireDao.afficherSurConsole();
		
		RandomAccessFile raf = new RandomAccessFile("C:\\Users\\formation\\Desktop\\Fichiers-Projet1\\annuaireStructure.txt", "rw");
		
		stagiaireDao.ajouterListeTriee(0, raf);
		System.out.println(stagiaireDao.getListeTriee().size());
		
	/*	for (Stagiaire stagiaire : stagiaireDao.getListeTriee()) {
			System.out.println(stagiaire.getNom());
		}*/
		
		launch(args); 
	}
	
	

}
