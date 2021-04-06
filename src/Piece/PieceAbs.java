package Piece;

public abstract class PieceAbs implements Piece {	
	
	private Couleur couleur;
	private String nom;
	private int[] position = new int [2];
	private int[] deplacement = new int [2];
	private int[] attaque = new int [2];
	
	public PieceAbs(Couleur couleur, String nom, int positionInit[], int deplacement[], int attaque[]) {
		assert(positionInit.length == 2 && deplacement.length == 2 && attaque.length == 2 && nom.length() == 1); //a virer ou faire un throw exception
		
		this.couleur = couleur;
		this.nom = nom.toLowerCase();
				
		this.position[1] = positionInit[1];
		this.position[2] = positionInit[2];
		
		this.attaque[1] = attaque[1];
		this.attaque[2] = attaque[2];					//voir si il y a pas moyen de faire ca en 1 ligne au lieu de 2
		
		this.deplacement[1] = deplacement[1];
		this.deplacement[2] = deplacement[2];
	}
	
	
	
	public String toString() {
		if (couleur == Couleur.blanc)
			return this.nom.toUpperCase();
		else 
			return this.nom.toLowerCase();
	}
}
