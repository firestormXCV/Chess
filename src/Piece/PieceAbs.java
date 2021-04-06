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
				
		this.position = positionInit;
		this.attaque = attaque;
		this.deplacement = deplacement;
	}
	
	
	
	public String toString() {
		if (couleur == Couleur.blanc)
			return this.nom.toUpperCase();
		else 
			return this.nom.toLowerCase();
	}
	
	@Override
	public void deplacer(int[] position) {
		if (possibleDeplacement(position)) {
			setPosition(position);
		}
		//else throw exception
			
			
	}
		
	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public boolean possibleDeplacement(int x, int y) {
		
		return true;
	}
}
