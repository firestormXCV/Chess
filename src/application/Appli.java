/**
 * @resume Bonjour ceci est le code de notre projet de BPO de période D, a savoir un jeu d'échec fonctionnel en java.
 * Vous avez la possibilité de faire jouer 2 joueur entre eux, ou bien joueur contre IA ou encore IA conter IA.
 * En ce qui concerne la saisie, pour jouer il faut saisir les coordonnée de base d'une piece puis celle de destination
 * Ex: d7d8
 * Les pieces blanche sont representees par ds lettre majuscule et donc les noirs en minuscules.
 * 
 * @author Thomas PASQUIER, Axel ROCHE
 * 
 */

package application;

import java.util.Scanner;

import partie.Partie;

public class Appli {
	
	
	/**
	 * Ceci est le main de notre projet, il permet de choisir celon quel paramètre vous allez lancer une partie.
	 */
	public static void main(String[] args) {
		
		int choix1 = 0;
		int choix2 = 0;
		Scanner sc = new Scanner(System.in);
		
		boolean valide = false;
				
		while (!valide) {
			try {
				System.out.println("Bonjour Joueur 1, que voulez-vous jouer ?\n	1. Joueur\n	2. IA de niveau 1\n");

				String saisie = sc.nextLine().trim();
				choix1 = Integer.parseInt(saisie);
				
				System.out.println("Et vous Joueur 2, que voulez-vous jouer ?\n	1. Joueur\n	2. IA de niveau 1\n");
				saisie = sc.nextLine().trim();
				choix2 = Integer.parseInt(saisie);
				
				Partie game = new Partie(choix1, choix2);
				game.Jouer();
			}catch (Exception exception) {
				valide = false;
				System.out.println("Je n'ai pas compris votre choix, merci de recommencer");
			}
		}
		
	}

}
