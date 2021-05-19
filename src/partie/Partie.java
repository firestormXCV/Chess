package partie;

import java.util.Scanner;

import Piece.Couleur;
import Piece.IPiece;
import joueur.IA1;
import joueur.IJoueur;
import joueur.Joueur;
import plateau.Echequier;

public class Partie {
	private IJoueur J1;
	private IJoueur J2;
	private Echequier plateau;
	
	public Partie(int choix1, int choix2){
		
		switch (choix1) {
		case 1:
			this.J1 = new Joueur("J1", Couleur.blanc);
			break;
		case 2:
			this.J1 = new IA1("J1", Couleur.blanc);
			break;
		default:
			this.J1 = new Joueur("J1", Couleur.blanc);			
		}
		
		switch (choix2) {
		case 1:
			this.J2 = new Joueur("J2", Couleur.noir);
			break;
		case 2:
			this.J2 = new IA1("J2", Couleur.noir);
			break;
		default:
			this.J2 = new Joueur("J2", Couleur.noir);
		}
		
		
		this.plateau = new Echequier();
		
		
	}
	
	public void Jouer() {
		String vainqueur = null;
		
		
		System.out.println(J1.getNom());
		System.out.println(J2.getNom());
		while (vainqueur == null) {
			
			System.out.println(plateau.toString());
			J1.jouerCoup(plateau, true);
			vainqueur = Vainqueur();
			if (vainqueur != null) 
				break;
			
				
			
			System.out.println(plateau.toString());
			J2.jouerCoup(plateau, true);	
			vainqueur = Vainqueur();
		}
		
		System.out.println(plateau.toString() + "-----------");
		System.out.println("Le vainqueur est " + vainqueur);
	}
	
	/**
	 * Cette methode permet de savoir si il y a echec et mat, et pour quel joueur.
	 * @return Le Joueur gagnant ou null si aucun n'est vainqueur
	 */
	private String Vainqueur() {
		
		plateau.actualiser();
		
		int[] CoordRoiNoir = plateau.getRoiCoord(Couleur.noir);
		int nbNoir = 0;
		int nbBlanc = 0;
		
		for (IPiece piece : plateau.getPion())
			if (piece.getCouleur() == Couleur.blanc)
				nbBlanc++;
			else 
				nbNoir++;
		
		if (nbNoir <= 1 && nbBlanc <= 1) {
			System.out.println("nullll");
			return "match null";
		}
			
		else if (nbNoir <= 1) {
			System.out.println(J2.getNom());
			return J1.getNom();
		}
			
		else if (nbBlanc <= 1) {
			System.out.println(J1.getNom());
			return J2.getNom();
		}
			
		
		
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
		String J = J1.getNom();
		System.out.println("roi noir en echec et mat vainqueur " + J);
		return J1.getNom();
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
			String J = J2.getNom();
			System.out.println("roi blanc en echec et mat vainqueur " + J);
			return J2.getNom();
		}
		
		
	
	return null;
	}
}
