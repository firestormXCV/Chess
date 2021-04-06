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
	public void deplacer(int[] position)  {//throw
		if (!verifDeplacement(position)) {
			//throw exception			
		}
		setPosition(position);
			
			
	}
		
	public int[] getPosition() {
		return position;
	}

	private void setPosition(int[] position) {
		this.position = position;
	}
	
	@Override
	public boolean verifDeplacement(int[] position) {
		
		return true;
	}
}
