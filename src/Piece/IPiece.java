package Piece;

public interface IPiece {
	public void deplacer(int posX, int posY); //throw				//deplace une piece haut coordonnes indique, utilise possibledeplacement() dabord et si ca return false throw une exception
	public boolean verifDeplacement(int posX, int posY);   //verifie si le deplacement est possible en fonction des capacites de deplacement de la piece en suimpe et en attaque, de la limite du plateau et de l'occupation de la case par une piece allie
	public void attaquer(int posX, int posY);
	public int getPositionX();
	public int getPositionY();
	public int getDeplacement1();
	public int getDeplacement2();
	public String getNom();
	public Couleur getCouleur();
	public int[][][] coupPossible();
}
