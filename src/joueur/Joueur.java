package joueur;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;

public class Joueur implements IJoueur {
	
	private ArrayList<IPiece> PionEnJeu;
	private String nom;
	private Couleur couleur;
	
	public Joueur(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		this.PionEnJeu = new ArrayList<IPiece>();
		
			PionEnJeu.add(new Roi(couleur));
			PionEnJeu.add(new Tour(couleur, false));
			PionEnJeu.add(new Tour(couleur, true));
		
	}
	
	
	
	@Override
	public void jouerCoup() {
		// TODO Auto-generated method stub
		
	}

}
