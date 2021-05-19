package application;

import java.util.Scanner;

import partie.Partie;

public class Appli {

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
