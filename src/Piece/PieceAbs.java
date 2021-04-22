package Piece;

public abstract class PieceAbs implements IPiece {	
	
	private Couleur couleur;
	private String nom;
	private int positionX;
	private int positionY;
	private int deplacement1;
	private int deplacement2;

	
	public PieceAbs(Couleur couleur, String nom, int positionInitX, int positionInitY, int deplacement1, int deplacement2) {

		
		this.couleur = couleur;
		this.nom = nom.toLowerCase();
				
		this.deplacement1 = deplacement1;
		this.deplacement2 = deplacement2;
		this.positionX = positionInitX;
		this.positionY = positionInitY;
		
		if (couleur == Couleur.blanc) 
			this.nom = nom.toUpperCase();
	}
	
	
	
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public void deplacer(int posX, int posY)  {//throw
		if (!verifDeplacement(posX, posY)) {
			//throw exception			
		}
		setPosition(posX, posY);
			
			
	}
		
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	public void setPosition(int posX, int posY) {				//DEMANDER POUR CA//
		this.positionX = posX;
		this.positionY = posY;
	}
	
	@Override
	public boolean verifDeplacement(int posX, int posY) {
		
		return true;
	}
}
