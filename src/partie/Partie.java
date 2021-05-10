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
			J1.jouerCoup(plateau, true);
			if (Vainqueur() != null) 				
				break;
			
			System.out.println(plateau.toString());
			J2.jouerCoup(plateau, true);				
		};
		System.out.println(plateau.toString());
		vainqueur =  "Le vainqueur est " + Vainqueur().getNom();
		System.out.println(vainqueur);
	}
	
	private IJoueur Vainqueur() {
		
		plateau.actualiser();
		
		int[] CoordRoiNoir = plateau.getRoiCoord(Couleur.noir);
		if (plateau.echec(CoordRoiNoir)) {
			for (int[][][] coupPiece : plateau.getToutCoupPossible())
				if (coupPiece[0][0][0] == CoordRoiNoir[0] && coupPiece[0][0][1] == CoordRoiNoir[1])
					for (int i = 0; i <= 8 - 1; i++ ) 
						for (int j = 0; j <= 8 - 1; j++ ) 
							if (!plateau.echec(coupPiece[i][j]))
								return null;
		return J1;
		}
		
		int[] CoordRoiBlanc = plateau.getRoiCoord(Couleur.blanc);
		if (plateau.echec(CoordRoiBlanc)) {
			for (int[][][] coupPiece : plateau.getToutCoupPossible())
				if (coupPiece[0][0][0] == CoordRoiBlanc[0] && coupPiece[0][0][1] == CoordRoiBlanc[1])
					for (int i = 0; i <= 8 - 1; i++ ) 
						for (int j = 0; j <= 8 - 1; j++ ) 
							if (!plateau.echec(coupPiece[i][j]))
								return null;
		return J2;
		}
		return null;
	}
		
}
