package Jeu;

public class Plateau {
	
	private final static int nbLignesPlateau = 8;
	private final static int nbColonnesPlateau = 8;
	
	private Case [][] damier;
	
	public Plateau () {
		
		for (int lig =0; lig < nbLignesPlateau; ++lig)
			for(int col = 0; col < nbColonnesPlateau; ++ col)
				damier[lig][col] = new Case();
	}
}
