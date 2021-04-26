package fr.eql.ai109.annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProgAnnuaire extends Application {

		@Override
		public void start(Stage stage) throws Exception {		
			
			FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
			
			stage.setTitle("Annuaire");
			
			Scene scene = new Scene(fenetrePrincipale, 1200, 800);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			
			stage.show();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		StagiaireDao stagiaireDao = new StagiaireDao();
		stagiaireDao.lireFichierStagiaire();
		stagiaireDao.determinerTaillesMax();
		stagiaireDao.creerFichierStructure();
		stagiaireDao.afficherSurConsole();
		
		RandomAccessFile raf = new RandomAccessFile("C:\\Users\\formation\\Desktop\\Fichiers-Projet1\\annuaireStructure.txt", "rw");
		
		stagiaireDao.ajouterListeTriee(0, raf);
	
		
		launch(args); 
	}
	
	

}
