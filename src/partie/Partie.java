package partie;

import Piece.Couleur;
import joueur.IJoueur;
import joueur.Joueur;
import plateau.Echequier;

public class Partie {
	private IJoueur J1;
	private IJoueur J2;
	private Echequier plateau;
	
	public Partie(){
		
		this.plateau = new Echequier();
		this.J1 = new Joueur("J1", Couleur.noir);
		this.J2 = new Joueur("J2", Couleur.blanc);
		System.out.println(plateau.toString());
	}
	
}
