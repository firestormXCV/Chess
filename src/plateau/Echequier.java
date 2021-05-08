package plateau;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;

public class Echequier {
	
	private final static int TailleCote = 8;
	private ArrayList<IPiece> PionBlanc;
	private ArrayList<IPiece> PionNoir;
	
	
	private Case[][] damier;
	
	public Echequier () {
		
		this.damier = new Case[TailleCote][TailleCote];
		for (int lig = 0; lig < TailleCote; ++lig)
			for(int col = 0; col < TailleCote; ++ col)
				damier[lig][col] = new Case();
		
		this.PionBlanc = new ArrayList<IPiece>();
		this.PionNoir = new ArrayList<IPiece>();
		
		PionBlanc.add(new Roi(Couleur.blanc));
		PionBlanc.add(new Tour(Couleur.blanc, false));
		PionBlanc.add(new Tour(Couleur.blanc, true));
		
		PionNoir.add(new Roi(Couleur.noir));
		PionNoir.add(new Tour(Couleur.noir, false));
		PionNoir.add(new Tour(Couleur.noir, true));
		
		for (IPiece piece: PionBlanc) {
			int x = 0;
			int y = 0;
			x = piece.getPositionX();
			y = piece.getPositionY();
			damier[x][y].setOccupe(piece.getNom());
		}
		
		for (IPiece piece: PionNoir) {
			int x = 0;
			int y = 0;
			x = piece.getPositionX();
			y = piece.getPositionY();
			damier[x][y].setOccupe(piece.getNom());
			System.out.println(piece.getNom() + "\n");
		}
			
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
		
			if (couleur == Couleur.blanc) {
				for (IPiece piece: PionBlanc) {
					if (piece.getPositionX() == coup[0] && piece.getPositionY() == coup[1]) {
						if (!verifCoup(coup, piece)) //throw exeption
							System.out.println("ok");
						else {
							piece.deplacer(coup[2], coup[3]);
							damier[coup[0]][coup[1]].setOccupe(" ");
							damier[coup[2]][coup[3]].setOccupe(piece.getNom());
						}
					} // else throw exception
						
				}
			} else
				for (IPiece piece: PionNoir) {
					if (piece.getPositionX() == coup[0] && piece.getPositionY() == coup[1]) {
						if (!verifCoup(coup, piece)) //throw exeption
							System.out.println("ok");
						else {
							piece.deplacer(coup[2], coup[3]);
							damier[coup[0]][coup[1]].setOccupe(" ");
							damier[coup[2]][coup[3]].setOccupe(piece.getNom());
						}
					} // else throw exception
				}
	}
	
	private boolean verifCoup(int[] coup, IPiece piece) { //throw exception
		int x = Math.abs(coup[0] - coup[2]);
		int y = Math.abs(coup[1] - coup[3]);
		if (verifLimit(coup)) {
			if ((piece.getDeplacement1() - x >= 0 && piece.getDeplacement2() - y >= 0) ||
					( piece.getDeplacement2() - x >= 0 && piece.getDeplacement1() - y >= 0)) {
						if (verifOccupation(coup, piece))
							return true;
			}		
		}
		return false;
	}
	
	private boolean verifLimit(int[] coup) {
			
		for (int i = 0; i <= 3; i++) {
			if (!(coup[i] >= 0 && coup[i] <= TailleCote - 1)) {
				return false;
			}
				
		}		
		return true;
	}
	
	private boolean verifOccupation(int[] coup, IPiece pion) {
		for (IPiece piece: PionNoir) {
			if (piece.getPositionX() == coup[2] && piece.getPositionY() == coup[3] && pion != piece)
				if (piece.getCouleur() == pion.getCouleur())
					return false;
				else {
					PionNoir.remove(piece);
					return true;
				}
					
		}
		for (IPiece piece: PionBlanc) {
			if (piece.getPositionX() == coup[2] && piece.getPositionY() == coup[3] && pion != piece)
				if (piece.getCouleur() == pion.getCouleur())
					return false;
				else {
					PionNoir.remove(piece);
					return true;
				}		
		}
		
		//for (int x = ;)
		return true;
	}
}
