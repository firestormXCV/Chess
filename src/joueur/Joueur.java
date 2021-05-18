package joueur;

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
	
	
	
	@Override
	public boolean jouerCoup(Echequier e, boolean enEchec) {	
		System.out.println("Joueur " + this.couleur + " c'est a vous de jouer !");
		int[] mouvement = new int[4];
		boolean valide = false;
		
		while (!valide) {
			try {
				mouvement = saisie();
				e.deplacement(mouvement, couleur);
				valide = true;
			}catch (StringIndexOutOfBoundsException exception) {
				valide = false;
				System.out.println(exception);
			}
		}
		return false;
	}
	
	public String getNom() {
		return nom;
	}
	
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
	
	public int[] convert(String saisie) { // throw exception si la saisie n'est pas comprise, default
		
		int[] coord;
		coord = new int[2];
		
		coord[0] = saisie.charAt(0) - 97;
		coord[1] = 7 - (saisie.charAt(1) - 49);
		//System.out.println(saisie);
		System.out.println(coord[0] + coord[1]);
		return coord;
	}
	
	


}
