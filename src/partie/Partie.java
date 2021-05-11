package partie;

import java.util.Scanner;

import Piece.Couleur;
import Piece.IPiece;
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
			
			Echequier plateauTest = new Echequier(plateau);
			System.out.println("Roi noir en echec");
			for (int[][][] coupPiece : plateau.getToutCoupPossible())
				if (coupPiece[0][0][0] == CoordRoiNoir[0] && coupPiece[0][0][1] == CoordRoiNoir[1])				
						for (IPiece piece : plateauTest.getPion()) {
							if (piece.getCouleur() == Couleur.noir && piece.getNom().toLowerCase().equals("r")) {
								for (int i = 0; i <= 8 - 1; i++ ) 	{
									plateauTest.actualiser();
									piece.deplacer(coupPiece[i][1][2],coupPiece[i][1][3]);
									CoordRoiNoir = plateauTest.getRoiCoord(Couleur.noir);	
									if (!plateauTest.echec(CoordRoiNoir))
											return null;
								}
							}
								
							
						}
		return J1;
		}
		
		int[] CoordRoiBlanc = plateau.getRoiCoord(Couleur.blanc);
		if (plateau.echec(CoordRoiBlanc)) {
			
			Echequier plateauTest = new Echequier(plateau);
			System.out.println("Roi blanc en echec");
			for (int[][][] coupPiece : plateau.getToutCoupPossible())
				if (coupPiece[0][0][0] == CoordRoiBlanc[0] && coupPiece[0][0][1] == CoordRoiBlanc[1])
					for (IPiece piece : plateauTest.getPion()) {
						if (piece.getCouleur() == Couleur.blanc && piece.getNom().toLowerCase().equals("r")) {
							for (int i = 0; i <= 8 - 1; i++ ) 	{
								plateauTest.actualiser();
								piece.deplacer(coupPiece[i][1][2],coupPiece[i][1][3]);
								CoordRoiBlanc = plateauTest.getRoiCoord(Couleur.blanc);	
								if (!plateauTest.echec(CoordRoiBlanc))
										return null;
							}
						}
							
						
					}
			return J2;
		}
		
		
	
	return null;
	}
}
