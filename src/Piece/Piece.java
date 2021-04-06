package Piece;

public interface Piece {
	public void deplacer(int[] position); 				//deplace une piece haut coordonnes indique, utilise possibledeplacement() dabord et si ca return false throw une exception
	public boolean possibleDeplacement(int[] position);   //verifie si le deplacement est possible en fonction des capacites de deplacement de la piece en suimpe et en attaque, de la limite du plateau et de l'occupation de la case par une piece allie
	public void attaquer(int[] position);
}
