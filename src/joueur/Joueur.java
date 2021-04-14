package joueur;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;

public class Joueur implements IJoueur {
	
	private ArrayList<IPiece> PionEnJeu;
	private String nom;
	private Couleur couleur;
	
	public Joueur(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		if (couleur == Couleur.blanc) {
			
		}else {
			
		}
	}
	
	
	
	@Override
	public void jouerCoup() {
		// TODO Auto-generated method stub
		
	}

}
