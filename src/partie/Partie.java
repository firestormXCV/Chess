package partie;

import java.util.Scanner;

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
		this.J1 = new Joueur("J1", Couleur.blanc);
		this.J2 = new Joueur("J2", Couleur.noir);
		
	}
	
	public void Jouer() {
		String vainqueur;
		
		while (Vainqueur() == null) {
			System.out.println(plateau.toString());
			if (J1.jouerCoup(plateau) || Vainqueur() != null) {
				vainqueur =  Vainqueur();
				break;
			}
			System.out.println(plateau.toString());
			if (J2.jouerCoup(plateau) || Vainqueur() != null)
				vainqueur =  Vainqueur();
		};
	}
	
	private String Vainqueur() {
		return null;
	}
	
	
}
