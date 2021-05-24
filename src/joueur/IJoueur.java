package joueur;

/**
 * Ceci est l'interface de tout les joueurs, un joueur quelqu'il soit implemente forcement cette interface
 */

import java.util.Scanner;

import plateau.Echequier;

public interface IJoueur {
	
	/**
	 * Tout les joueurs peut importe s'ils sont humain ou artificiels, ils doivent jouer une coup, a leur maniere
	 * @param e			L'echequier sur lequel les pieces vont jouer
	 */
	public void jouerCoup(Echequier e);
	public String getNom();
	/**
	 * Cette methode sert au choix d'un coup par un utilisateur ou bien par une IA
	 * @return un tableau d'int qui contient 4 int, celon le format : SourceX SourceY DestinationX DestinationY
	 */
    public int[] saisie();
    
		
		
}
	 

