package joueur;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;

public class Joueur implements IJoueur {

	private String nom;
	private Couleur couleur;
	
	public Joueur(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		
	}
	
	
	
	@Override
	public void jouerCoup() {
		// TODO Auto-generated method stub
		
	}

}
