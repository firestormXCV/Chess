package joueur;
/**
 * Ceci est la class Joueur, elle correspond au code d'un joueur humain
 */
import java.util.ArrayList;
import java.util.Scanner;

import Piece.Couleur;
import Piece.IPiece;

import plateau.Echequier;

public class Joueur implements IJoueur {

	private String nom;
	private Couleur couleur;
	
	public Joueur(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		
	}
	
				
	/**
	 * methode permettant au joueur de jouer son coup, si le coup n'est pas valide alors on recommence jusqu'a obtenir un resultat valide
	 * @param e L'echequier sur lequel le joueur va jouer
	 */
	@Override
	public void jouerCoup(Echequier e) {	
		System.out.println("Joueur " + this.couleur + " c'est a vous de jouer !");
		int[] mouvement = new int[4];			//un tableau d'int qui contient 4 int, celon le format : SourceX SourceY DestinationX DestinationY
		boolean valide = false;
		
		while (!valide) {
			try {
				mouvement = saisie();					//choix du coup
				e.deplacement(mouvement, couleur);		//Deplacement sur l'echequier
				valide = true;
			}catch (Exception exception) {				//Si le coup est rendu impossible par la leve d'une exception custom ou basique
				valide = false;							//On recommence le coup
				System.out.println("Erreur de saisie ou coup impossible merci de rejouer un coup différent");
			}
		}
		return;
	}
	
	public String getNom() {
		return nom;
	}
	/**
	 * methode permettant le choix du coup par le joueur, il tappe ce qu'il soihaite jouer et sa saisie est convertie en int
	 * @return un tableau d'int qui contient 4 int, celon le format : SourceX SourceY DestinationX DestinationY
	 */
	public int[] saisie() {
				
		int[] coordDeb = new int[2];
		int[] coordFin = new int[2];
		int[] coord = new int[4];
		
		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine().trim().toLowerCase();
		
		String s1 = saisie.substring(0,2);
		String s2 = saisie.substring(2);
		
		coordDeb = convert(s1);
		coordFin = convert(s2);
		coord[0] = coordDeb[0];
		coord[1] = coordDeb[1];
		coord[2] = coordFin[0];
		coord[3] = coordFin[1];
		return coord;
		
		
	}
	/**
	 * Cette methode convertie une string, soit une coordonne dans le tableau en coordonne comprehensible par le code, en int
	 * @param saisie  La string a convertire
	 * @return	Le tableau d'int une fois convertie
	 */
	public int[] convert(String saisie) { // throw exception si la saisie n'est pas comprise, default
		
		int[] coord;
		coord = new int[2];
		
		coord[0] = saisie.charAt(0) - 97;			//Convertie la lettre en entier correspond au coordonnees reel du plateau grace au code ASCII
		coord[1] = 7 - (saisie.charAt(1) - 49);		//Convertie cette fois-ci le nombre
		//System.out.println(saisie);
		return coord;
	}
	
	


}
