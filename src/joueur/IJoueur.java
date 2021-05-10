package joueur;

import java.util.Scanner;

import plateau.Echequier;

public interface IJoueur {
	public boolean jouerCoup(Echequier e, boolean enEchec);
	public String getNom();
    public int[] saisie();
    
		
		
}
	 

