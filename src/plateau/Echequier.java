package plateau;

import java.util.ArrayList;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;

public class Echequier {
	
	private final static int nbLignesPlateau = 8;
	private final static int nbColonnesPlateau = 8;
	private ArrayList<IPiece> PionBlanc;
	private ArrayList<IPiece> PionNoir;
	
	
	private Case[][] damier;
	
	public Echequier () {
		
		this.damier = new Case[nbLignesPlateau][nbColonnesPlateau];
		for (int lig = 0; lig < nbLignesPlateau; ++lig)
			for(int col = 0; col < nbColonnesPlateau; ++ col)
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
			System.out.println(piece.getNom() + "\n");
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
				
				for (int lig = 0; lig < nbLignesPlateau; ++lig) {
					s.append("|");		
					for(int col = 0; col < nbColonnesPlateau; ++ col) {
					s.append(damier[col][lig].getOccupe() + "|");
					}
					s.append("\n");
				}
						
				
				s.append("- - - - - - - - \n");
				
				s.append("\n\n\n" + PionBlanc.get(0).getNom() + PionBlanc.get(0).getPositionX() + "   " + PionBlanc.get(0).getPositionY());
		
		return s.toString();
		
	}
}
