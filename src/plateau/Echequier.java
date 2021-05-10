package plateau;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;

public class Echequier {
	
	private final static int TailleCote = 8;
	private ArrayList<IPiece> Pion;
	private ArrayList<int[][][]> ToutCoupPlatau;
	
	
	private Case[][] damier;
	
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
			damier[x][y].setOccupe(piece.getNom());
		}
		
		this.ToutCoupPlatau = new ArrayList<>();
		for (IPiece piece: Pion) 
			this.ToutCoupPlatau.add(coupDisponible(piece));
		
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
	
	public void deplacement(int[] coup, Couleur couleur)  { //throw exception
		//ArrayList<int[][][]> toutCoupPossible = new ArrayList<>();	
		int toutCoupPossible[][][] = new int[8][TailleCote][4];
		actualiser();
		for (IPiece piece: Pion) {
			
			if (piece.getPositionX() == coup[0] && piece.getPositionY() == coup[1] && couleur == piece.getCouleur()) {
				for (int[][][] coupPiece : ToutCoupPlatau)
					if (coupPiece[0][0][0] == coup[0] && coupPiece[0][0][1] == coup[1])
						toutCoupPossible = coupPiece;
				
								
				for (int i = 0; i <= TailleCote - 1 ; i++) {
					for (int j = 0; j <= TailleCote - 1; j++) {
						if (toutCoupPossible[i][j][0] == coup[0] &&
							toutCoupPossible[i][j][1] == coup[1] &&
							toutCoupPossible[i][j][2] == coup[2] &&
							toutCoupPossible[i][j][3] == coup[3]) {
							
							for (IPiece ennemis: Pion) {
								if (ennemis.getPositionX() == coup[2] && ennemis.getPositionY() == coup[3]) {
									Pion.remove(ennemis);
									break;
								}
							}						
							piece.deplacer(coup[2], coup[3]);
							damier[coup[0]][coup[1]].setOccupe(" ");
							damier[coup[2]][coup[3]].setOccupe(piece.getNom());
							
							return;
						} else {
							//throw exception
						}
					}
				}
			}					
		}								
	}
	/**
	 * Cette methode permet de calculer tout les coups possible pour une piece sur l'echequier
	 * @param piece: la piece qui est etudie
	 * @return: un tableau de trajectoire, chaque trajectoire est un tableau compos� de coordonn�es, de paire paire d'int.
	 */
	private int[][][] coupDisponible(IPiece piece) {
		int listTrajectoire[][][] = new int[8][TailleCote][4];
		int toutCoupPossible[][][] = new int[8][TailleCote][4];
		int[] nul = {0, 0, 0, 0};
		
		listTrajectoire = piece.coupPossible();
		
		for (int i = 0; i <= TailleCote - 1 ; i++) {
			boolean possible = true;
			
			for (int j = 0; j <= TailleCote - 1 && possible; j++) {
				
				if (listTrajectoire[i][j] != nul) {
					switch (verifCoup(listTrajectoire[i][j], piece)) {
					case 0:
						possible = false;
						
					case 1:
						toutCoupPossible[i][j] = listTrajectoire[i][j];
						break;
					case 2:
						possible = false;
					}
					
				} else 
					possible = false;
			}
		}
		
		return toutCoupPossible;
	}
	
	
	/**
	 * 
	 * @param coup
	 * @param piece
	 * @return 0 = coup possible mais dernier direction car ennemis present, 1 = coup valide , 2 =  coup impossible alli� present ou autre erreur
	 */
	private int verifCoup(int[] coup, IPiece piece) { //throw exception
		int x = Math.abs(coup[0] - coup[2]);
		int y = Math.abs(coup[1] - coup[3]);
		if (verifLimit(coup)) {
			if ((piece.getDeplacement1() - x >= 0 && piece.getDeplacement2() - y >= 0) ||
					( piece.getDeplacement2() - x >= 0 && piece.getDeplacement1() - y >= 0)) {
						return verifOccupation(coup, piece);
							
			}		
		}
        return 1;
	}
	/**
	 * Verifie si les valeurs du coup sont bien des valeurs positive et dans la limite du plateau 
	 * (-1 car le tableau commence a 0 alors que l'�ch�quier commence a 1).
	 * @param coup: Coup a v�rifier
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
	 * 
	 * @param coup
	 * @param pion
	 * @return 0 = case occup� ennemis, 1 = case vide, 2 = case occup� alli�
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
		//for (int x = ;)
		return 1;
	}
	
	public ArrayList<int[][][]> getToutCoupPossible() {
		return ToutCoupPlatau;
	}
	
	public void actualiser() {
		ToutCoupPlatau.removeAll(ToutCoupPlatau);
		for (IPiece piece: Pion) 
			this.ToutCoupPlatau.add(coupDisponible(piece));
	}
	
	public int[] getRoiCoord(Couleur couleur) {
		int[] coord = new int[2];
		for (IPiece piece : Pion) {
			
			if (piece.getNom().toLowerCase().equals("r") && piece.getCouleur() == couleur) {
				coord[0] = piece.getPositionX();
				coord[1] = piece.getPositionY();
				return coord;
			}
		}	
		return coord;
	}
	
}
