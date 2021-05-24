package plateau;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;
import exception.SaisieException;

public class Echequier {
	
	private final static int TailleCote = 8;
	private ArrayList<IPiece> Pion;

	private ArrayList<int[][][]> ToutCoupPlatau;	//Tout les coups possible sur le plateau pour toute les pieces
	
	
	private Case[][] damier;						//Plateau constiuer de case
	
	public Echequier () {
		
		this.damier = new Case[TailleCote][TailleCote];
		for (int lig = 0; lig < TailleCote; ++lig)
			for(int col = 0; col < TailleCote; ++ col)
				damier[lig][col] = new Case();
		
		this.Pion = new ArrayList<IPiece>();
		
		Pion.add(new Roi(Couleur.blanc));
		Pion.add(new Tour(Couleur.blanc, false));
		Pion.add(new Tour(Couleur.blanc, true));
		
		Pion.add(new Roi(Couleur.noir));
		Pion.add(new Tour(Couleur.noir, false));
		Pion.add(new Tour(Couleur.noir, true));
			
		for (IPiece piece: Pion) {
			int x = 0;
			int y = 0;
			x = piece.getPositionX();
			y = piece.getPositionY();
			damier[x][y].setOcuppePar(piece.getNom());	//Les pions sont poser sur la plateau d'apres leur coordonnes
		}
		
		this.ToutCoupPlatau = new ArrayList<>();
		actualiser();									//ON met tout de suite a jour la liste des coups
		
	}
	/**
	 * 2eme constructor servant a faire une copie de l'echequier
	 * @param e, echequier a copier
	 */
	public Echequier (Echequier e) {
		this.damier = new Case[TailleCote][TailleCote];
		for (int lig = 0; lig < TailleCote; ++lig)
			for(int col = 0; col < TailleCote; ++ col)
				damier[lig][col] = new Case(e.damier[lig][col]);
				
		this.Pion = new ArrayList<IPiece>();
		for (int i = 0; i <= e.getPion().size() - 1; i++) {
			if (e.getPion().get(i) instanceof Tour)
				Pion.add(new Tour ((Tour) e.getPion().get(i)));
			else if (e.getPion().get(i) instanceof Roi)
				Pion.add(new Roi ((Roi) e.getPion().get(i)));
		}
		
		this.ToutCoupPlatau = new ArrayList<>();
		actualiser();
		
	}
	
	public String toString() {
		String coordLettres = "    a   b   c   d   e   f   g   h";
		int coordChiffres = 8;
		StringBuilder s = new StringBuilder();
		s.append(coordLettres + "\n");
		s.append("   --- --- --- --- --- --- --- ---\n");
		for (int lignes = 0 ; lignes < TailleCote ; lignes++) {
			s.append(coordChiffres);
			coordChiffres--;
			for(int colonnes = 0 ; colonnes < TailleCote ; colonnes++) {
				s.append(" | " + damier[colonnes][lignes].getOccupe());
			}
			
			s.append(" | " + (coordChiffres + 1) + "\n");
			s.append("   --- --- --- --- --- --- --- ---\n");
		}
		
		s.append(coordLettres + "\n");
		return s.toString();	
	        
	}
	
	/**
	 * Methode permettant de deplacer une piece sur le plateau et de verifier si c'est bien possible
	 * @param coup, le coup qui est entrain d'tre jouer sur le plateau
	 * @param couleur, la couleur du joueur qui veut faire le coup
	 * @throws SaisieException, le coup saisie n'est pas valide sur le plateau
	 */
	public void deplacement(int[] coup, Couleur couleur)   throws SaisieException {
			
		int toutCoupPossible[][][] = new int[8][TailleCote][4];
		actualiser();
		for (IPiece piece: Pion) {
			
			if (piece.getPositionX() == coup[0] && piece.getPositionY() == coup[1] && couleur == piece.getCouleur()) {
				for (int[][][] coupPiece : ToutCoupPlatau)
					if (coupPiece[0][0][0] == coup[0] && coupPiece[0][0][1] == coup[1])	//Correspondance des 2 premiers int, ceux de la position de base de la piece
						toutCoupPossible = coupPiece;									//On met dans le tabl eau tout les coups possible pour la piece qui correspind a celle evoque dans le coup
				
								
				for (int i = 0; i <= TailleCote - 1 ; i++) {
					for (int j = 0; j <= TailleCote - 1; j++) {
						if (toutCoupPossible[i][j][0] == coup[0] &&
							toutCoupPossible[i][j][1] == coup[1] &&
							toutCoupPossible[i][j][2] == coup[2] &&
							toutCoupPossible[i][j][3] == coup[3]) {		//verifie que le coup est bien present dans la liste de tout les coups possbles de la pieces
							
							Echequier plateauTest = new Echequier(this);		//Creer un plateau de test pour faire des simulation
							for (IPiece pionTest : plateauTest.getPion())	
								if (pionTest.getPositionX() == piece.getPositionX() && pionTest.getPositionY() == piece.getPositionY())	//On cherche le pion corerspondant au coup jouer dans le plateau de test
									pionTest.deplacer(coup[2], coup[3]);		//On test le coup sur le plateau
							plateauTest.actualiser();							
							if (!plateauTest.echec(plateauTest.getRoiCoord(couleur))) {	//On verifie que ce coup ne met pas le jouer tout seul en ehec d'une maniere ou d'une autre
								
								for (IPiece ennemis: Pion) {
									if (ennemis.getPositionX() == coup[2] && ennemis.getPositionY() == coup[3] && ennemis != piece) {
										Pion.remove(ennemis);	//Suppression de la piece sur cette case si il en existe une et quel n'est pas allie
										break;
									}
								}						
								piece.deplacer(coup[2], coup[3]);		//Deplacement de la piece
								damier[coup[0]][coup[1]].setOcuppePar(" ");
								damier[coup[2]][coup[3]].setOcuppePar(piece.getNom());	//Mise a jour de l'affichage
								
								return;
							}
						}
					}
				}
			}					
		}
		throw new SaisieException();	// Si on arrive la c'st que les verif on rate et donc il y a un probleme dans le choix du coup, donc on throw ppiur rejouer le coup
	}
	/**
	 * Cette methode permet de calculer tout les coups possible pour une piece sur l'echequier
	 * @param piece: la piece qui est etudie
	 * @return: un tableau de trajectoire, chaque trajectoire est un tableau composé de coordonnées, de paire paire d'int.
	 */
	private int[][][] coupDisponible(IPiece piece) {
		int listTrajectoire[][][] = new int[8][TailleCote][4];
		int toutCoupPossible[][][] = new int[8][TailleCote][4];
		int[] nul = {0, 0, 0, 0};
		
		listTrajectoire = piece.coupPossible(); //Liste temporaire de tout les coups possible pour une piece
		
		for (int i = 0; i <= TailleCote - 1 ; i++) {				//Verification de tout le coups un a un si il sont bien valide d'apres la realite de l'echequier
			boolean possible = true;
			
			for (int j = 0; j <= TailleCote - 1 && possible; j++) {
				
				if (listTrajectoire[i][j] != nul) {
					switch (verifCoup(listTrajectoire[i][j], piece)) {
					case 0:
						possible = false;									//Le coups est valide mais c'est le dernier sur cette trajectoire
					case 1:
							toutCoupPossible[i][j] = listTrajectoire[i][j];	//On ajoute le coup a la liste
						break;
					case 2:
						possible = false;									//Ce coup est impossible
					}
					
				} else 
					possible = false;					
			}
		}
		
		return toutCoupPossible;
	}
	
	
	/**
	 * Cette methode verifie si le coup est bien valide dans l'echequier actuel
	 * @param coup le coup a jouer
	 * @param piece la piece qui doit etre deplacer
	 * @return 0 = coup possible mais dernier direction car ennemis present, 1 = coup valide , 2 =  coup impossible allié present ou autre erreur
	 */
	private int verifCoup(int[] coup, IPiece piece) { //throw exception
		int x = Math.abs(coup[0] - coup[2]);
		int y = Math.abs(coup[1] - coup[3]);
		if (verifLimit(coup) ) {	//Verifie qu'on est pas en dehors du plateau
			if (((piece.getDeplacement1() - x >= 0 && piece.getDeplacement2() - y >= 0) ||
					(piece.getDeplacement2() - x >= 0 && piece.getDeplacement1() - y >= 0))) {	//Verifie que les coups nous emmene pas en negatif
							return verifOccupation(coup, piece);
							
			}		
		}
        return 2;
	}
	/**
	 * Verifie si les valeurs du coup sont bien des valeurs positive et dans la limite du plateau 
	 * (-1 car le tableau commence a 0 alors que l'échéquier commence a 1).
	 * @param coup: Coup a vérifier
	 * @return: True = OK,   False = Erreur
	 */
	private boolean verifLimit(int[] coup) {
			
		for (int i = 0; i <= 3; i++) {
			if (!(coup[i] >= 0 && coup[i] <= TailleCote - 1)) {
				return false;
			}
				
		}		
		return true;
	}
	/**
	 *Verifie si la case vise par la piece n'est pas occupe par un pion allie et si elle est occupe par un pion ennemis, ou bien juste vide
	 * @param coup, coup qui doit etre jouer
	 * @param pion, piece qui doit etre jouer
	 * @return 0 = case occupé ennemis, 1 = case vide, 2 = case occupé allié
	 */
	private int verifOccupation(int[] coup, IPiece pion) {
		for (IPiece piece: Pion) {
			if (piece.getPositionX() == coup[2] && piece.getPositionY() == coup[3] && pion != piece)
				if (piece.getCouleur() == pion.getCouleur())
					return 2;
				else {
					return 0;
				}
					
		}
		return 1;
	}
	
	public ArrayList<int[][][]> getToutCoupPossible() {
		return ToutCoupPlatau;
	}
	
	public ArrayList<IPiece> getPion() {
		return Pion;
	}
	
	/**
	 * Génére la Liste de tout les coups possible pour toute les pieces sur le plateau.
	 */
	public void actualiser() {
		ToutCoupPlatau.removeAll(ToutCoupPlatau);	// Vide la liste des coups possibles
		for (IPiece piece: Pion) 
			this.ToutCoupPlatau.add(coupDisponible(piece));	//Ajoute pour tout les pieces les coups quel peut jouer sur le plateau
	}
	/**
	 * donne les coordonnes du roi de la couleur indique
	 * @param couleur, couleur du roi situer
	 * @return les coordonnes du roi a situer
	 */
	public int[] getRoiCoord(Couleur couleur) {
		int[] coord = new int[4];
		for (IPiece piece : Pion) {
			
			if (piece.getNom().toLowerCase().equals("r") && piece.getCouleur() == couleur) {
				coord[0] = piece.getPositionX();
				coord[1] = piece.getPositionY();
				coord[2] = piece.getPositionX();
				coord[3] = piece.getPositionY();
				return coord;
			}
		}	
		return coord;
	}
	/**
	 * Definis si une case de certaine coordonne est menace par une autre piece, en echec si on parle d'un roi sur cette case
	 * @param coord: coordonnes de la case depuis laquel l echec est verifie
	 * @return TRUE si la piece sur la case coord est en echec, sinon FALSE
	 */
	public boolean echec(int[] coord) {
			
			for (int[][][] coupPossible : ToutCoupPlatau) {
				for (int i = 0; i <= TailleCote - 1; i++ ) {
					for (int j = 0; j <= TailleCote - 1; j++ ) {
						
						if ((coord[2] == coupPossible[i][j][2] && coord[3] == coupPossible[i][j][3])
								&& (coord[0] != coupPossible[i][j][0] || coord[1] != coupPossible[i][j][1])) {	//Verifie si parmis tout les coups possible du plateau, les coordonnes de cette case apparaissent
							return true;
						}
					}
				}
			}
			
			return false;
		}	
}
