package partie;
/**
 * Class Partie.java, c'est le deroulement d'une partie, avec les verification de victoire.
 */
import java.util.Scanner;

import Piece.Couleur;
import Piece.IPiece;
import exception.SaisieException;
import joueur.IA1;
import joueur.IJoueur;
import joueur.Joueur;
import plateau.Echequier;

public class Partie {
	private IJoueur J1;
	private IJoueur J2;
	private Echequier plateau;
	
	/**
	 * En fonction du choix le joueur choisie de jouer une IA ou de jouer manuellement
	 * @param choix1 Choix de J1
	 * @param choix2 Choix de J2
	 */
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
	
	/**
	 * Methode permettant de jouer une partie complete
	 */
	public void Jouer() {
		String vainqueur = null;
		
		
		System.out.println(J1.getNom());
		System.out.println(J2.getNom());
		while (vainqueur == null) {			//Tant que la methode vainqueur() ne renvoie aucune string la partie ne s'arrete pas
			
			System.out.println(plateau.toString());
			J1.jouerCoup(plateau);
			vainqueur = Vainqueur();
			if (vainqueur != null) 
				break;
			
				
			
			System.out.println(plateau.toString());
			J2.jouerCoup(plateau);	
			vainqueur = Vainqueur();
		}
		
		System.out.println(plateau.toString() + "-----------");
		System.out.println("Le vainqueur est " + vainqueur);
	}
	
	/**
	 * Cette methode permet de savoir si il y a echec et mat, et pour quel joueur.
	 * @return Le Joueur gagnant null, ou une string disant match nul s'il n'y a aucun n'est vainqueur
	 */
	private String Vainqueur() {
		
		plateau.actualiser();	//Actualise la liste de tout les coups jouables sur l'echequier
		
		int[] CoordRoiNoir = plateau.getRoiCoord(Couleur.noir);
		int nbNoir = 0;
		int nbBlanc = 0;
		
		for (IPiece piece : plateau.getPion())	//Compte le nombre de piece noir et blanche restant
			if (piece.getCouleur() == Couleur.blanc)
				nbBlanc++;
			else 
				nbNoir++;
		
		if (nbNoir <= 1 && nbBlanc <= 1) {	 //Si les 2 joueurs n'ont plus assez de pion pour continuer a jouer, que les 2 rois
			return "match null";
		}
			
		else if (nbNoir <= 1) {				//Le joueur noir n'a plus que sont roi et ne peux donc plus jouer
			System.out.println(J2.getNom()+ " n'a plus assez de pion");
			return J1.getNom();
		}
			
		else if (nbBlanc <= 1) {			//Le joueur blanc n'a plus que sont roi et ne peux donc plus jouer
			System.out.println(J1.getNom()+ " n'a plus assez de pion");
			return J2.getNom();
		}
			
		
		
		if (plateau.echec(CoordRoiNoir)) {	//Si le joueur noir est en echec alors on verifie s'il y a mat
			
			Echequier plateauTest = new Echequier(plateau);	//On créer un plateau de test afin de pouvoir faire des simulation de coup
			System.out.println("Roi noir en echec");

			for (int[][][] coupPiece : plateau.getToutCoupPossible())
				if (coupPiece[0][0][0] == CoordRoiNoir[0] && coupPiece[0][0][1] == CoordRoiNoir[1])		//Cherche parmit tout les coups possible ce qui correspondent au roi				
						for (IPiece piece : plateauTest.getPion()) {
							if (piece.getCouleur() == Couleur.noir && piece.getNom().toLowerCase().equals("r")) {	//On cherche quel piece est le roi su le plateau
								for (int i = 0; i <= 8 - 1; i++ ) 	{				//On test pour tout les coups possible autour du roi, les 8 directions
									plateauTest = new Echequier(plateau);			//On refait un plateau a chaque fois car le roi a peut etre bouger
									plateauTest.actualiser();				
									try {
										plateauTest.deplacement(coupPiece[i][1],Couleur.noir);
									} catch (SaisieException e) {
										continue;
									}
									//System.out.println(plateauTest.toString());
									plateauTest.actualiser();
									CoordRoiNoir = plateauTest.getRoiCoord(Couleur.noir);	
									if (!plateauTest.echec(CoordRoiNoir))		//S'il n'y a pas echec d'un 1 cas, alors ca veut dire qu'il n'y a pas mat, le roi n'est pas coincé
											return null;
									
								}
							}
								
							
						}

		System.out.println("Roi noir en echec et mat vainqueur " + J1.getNom());
		return J1.getNom();
		}
		
		int[] CoordRoiBlanc = plateau.getRoiCoord(Couleur.blanc);
		if (plateau.echec(CoordRoiBlanc)) {		//Comme precedement
			
			Echequier plateauTest = new Echequier(plateau);
			System.out.println("Roi blanc en echec");

			for (int[][][] coupPiece : plateau.getToutCoupPossible())
				if (coupPiece[0][0][0] == CoordRoiBlanc[0] && coupPiece[0][0][1] == CoordRoiBlanc[1])
					for (IPiece piece : plateauTest.getPion()) {
						if (piece.getCouleur() == Couleur.blanc && piece.getNom().toLowerCase().equals("r")) {
							for (int i = 0; i <= 8 - 1; i++ ) 	{
								plateauTest = new Echequier(plateau);
								plateauTest.actualiser();
								try {
									plateauTest.deplacement(coupPiece[i][1],Couleur.blanc);
								} catch (SaisieException e) {
									continue;
								}
								//System.out.println(plateauTest.toString());
								plateauTest.actualiser();
								CoordRoiBlanc = plateauTest.getRoiCoord(Couleur.blanc);	
								if (!plateauTest.echec(CoordRoiBlanc))
										return null;
							}
						}
							
						
					}
			
			System.out.println("Roi blanc en echec et mat vainqueur " + J2.getNom());
			return J2.getNom();
		}
		
		
	
	return null;
	}
}
