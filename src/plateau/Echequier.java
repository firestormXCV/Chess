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
		   StringBuilder s = new StringBuilder();
	        
	        s.append("- - - - - - - - \n");
	                
	                for (int lig = 0; lig < TailleCote; ++lig) {
	                    s.append("|");        
	                    for(int col = 0; col < TailleCote; ++ col) {
	                    s.append(damier[col][lig].getOccupe() + "|");
	                    }
	                    s.append("\n");
	                }
	                        
	                
	                s.append("- - - - - - - - \n");
	                
	                s.append("\n\n\n" + PionBlanc.get(0).getNom() + PionBlanc.get(0).getPositionX() + "   " + PionBlanc.get(0).getPositionY());
	        
	        return s.toString();
	        
	}
	
	public void deplacement(int[] coup, Couleur couleur)  { //throw exception
		
			if (couleur == Couleur.blanc) {
				for (IPiece piece: PionBlanc) {
					if (piece.getPositionX() == coup[0] && piece.getPositionY() == coup[1]) {
						if (!verifCoup(coup, piece)) //throw exeption
							System.out.println("ok");
						else 
							piece.deplacer(coup[2], coup[3]);
					} // else throw exception
						
				}
			} else
				for (IPiece piece: PionNoir) {
					if (piece.getPositionX() == coup[0] && piece.getPositionY() == coup[1]) {
						if (!verifCoup(coup, piece)) //throw exeption
							System.out.println("ok");
						else 
							piece.deplacer(coup[2], coup[3]);
					} // else throw exception
				}
	}
	
	private boolean verifCoup(int[] coup, IPiece piece) { //throw exception
		return true;
	}
}
