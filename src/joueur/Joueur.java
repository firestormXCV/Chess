package joueur;

import java.util.ArrayList;
import java.util.Scanner;

import Piece.Couleur;
import Piece.IPiece;
import Piece.Roi;
import Piece.Tour;
import plateau.Echequier;

public class Joueur implements IJoueur {




	private String nom;
	private Couleur couleur;
	
	public Joueur(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		
	}
	
	
	
	@Override
	public boolean jouerCoup(Echequier e) {	
		int[] mouvement;
		mouvement = new int[4];
		//try
		mouvement = saisie();
		e.deplacement(mouvement, couleur);
		//catch on recommence
		return false;	
	}
	
	public String getNom() {
		return nom;
	}
	
	public int[] saisie() {
				
		int[] coordDeb;
		int[] coordFin;
		int[] coord;
		coordDeb = new int[2];
		coordFin = new int[2];
		coord = new int[4];
		
		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine().trim().toLowerCase();
		
		String s1 = saisie.substring(0,2);
		String s2 = saisie.substring(2);
		
		coordDeb = convert(s1);
		coordFin = convert(s2);
		coord[0] = coordDeb[0];
		coord[1] = coordDeb[1];
		coord[2] = coordFin[0];
		coord[3] = coordDeb[1];
		return coord;
		
		
	}
	
	public int[] convert(String saisie) { // throw exception si la saisie n'est pas comprise, default
		
		int[] coord;
		coord = new int[2];
		
		switch (saisie.charAt(0)) {
		case 'a':
			coord[0] = 0;
			break;
		case 'b':
			coord[0] = 1;
			break;
		case 'c':
			coord[0] = 2;
			break;
		case 'd':
			coord[0] = 3;
			break;
		case 'e':
			coord[0] = 4;
			break;
		case 'f':
			coord[0] = 5;
			break;
		case 'g':
			coord[0] = 6;
			break;
		case 'h':
			coord[0] = 7;
			break;
		default: // throw exception
			
		}
		coord[1] = saisie.charAt(1) - 1;
		
		return coord;
	}
	
	


}
