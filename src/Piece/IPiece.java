package Piece;
/**
 * Cette interface est celle qui est implemente par PieceAbs et par consequent dans toute les pieces du jeu
 */
public interface IPiece {
	public void deplacer(int posX, int posY);				//deplace une piece au coordonnes indique
	public int getPositionX();
	public int getPositionY();
	public int getDeplacement1();
	public int getDeplacement2();
	public String getNom();
	public Couleur getCouleur();
	public int[][][] coupPossible();
}
