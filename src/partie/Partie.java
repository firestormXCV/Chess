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
		String vainqueur = null;
		
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
		System.out.println(plateau.toString());
		System.out.println(vainqueur);
	}
	
	private String Vainqueur() {
		
		plateau.actualiser();
		
		//if (!mat())
		int[] CoordRoiNoir = plateau.getRoiCoord(Couleur.noir);
		if (mat(CoordRoiNoir))
			return "Blanc est le vainqueur";
		
		int[] CoordRoiBlanc = plateau.getRoiCoord(Couleur.blanc);
		if (mat(CoordRoiBlanc))
			return"Noir est le vainqueur";
		return null;
	}
	
	private boolean mat(int[] coord) {
		
		for (int[][][] coupPossible : plateau.getToutCoupPossible()) {
			for (int i = 0; i <= 8 - 1; i++ ) {
				for (int j = 0; j <= 8 - 1; j++ ) {
					
					if ((coord[0] == coupPossible[i][j][2] && coord[1] == coupPossible[i][j][3])
							&& (coord[0] != coupPossible[i][j][0] || coord[1] != coupPossible[i][j][1])) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
}
