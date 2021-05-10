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
		if (!verifDeplacement(posX, posY)) { //verif useles ??
			//throw exception			
		}
		setPosition(posX, posY);
			
			
	}
	
	
	
	/**
	 *Renvoie tout les coups possibles a partir de la position actuelle de la pièce
	 *Les coups sont calculé enfonction de la portées de la piece, sans prendre un compte le plateau.
	 *
	 *Return: un tableau en 3D d'int.
	 *[8]------>Tableau de max 8 cases, couvre les 8 trajectoire possible, (certaine sont donc nul pour certaine piece)
	 *[8]---->tableau de max 8 cases il correspond a une trajectoire, c'est une suite de paire de coordonnées, représente tout les cases d'une trajectoire
	 *[4]--> Tableau de 4 cases des coordonnées, les x et y de début et x y de fin
	 */
	public int[][][] coupPossible() {
		
		int listTrajectoire[][][] = new int[8][8][4];
		
		for(int x = positionX, c = 0; x <= positionX + deplacement1;c++, x++) {
			listTrajectoire[0][c][0] = positionX;
			listTrajectoire[0][c][1] = positionY;
			listTrajectoire[0][c][2] = x;
			listTrajectoire[0][c][3] = positionY;
		}
		for(int y = positionY, c = 0; y <= positionY + deplacement1;c++, y++) {
			listTrajectoire[1][c][0] = positionX;
			listTrajectoire[1][c][1] = positionY;
			listTrajectoire[1][c][2] = positionX;
			listTrajectoire[1][c][3] = y;
		}
		
		
		for(int x = positionX, c = 0; x >= positionX - deplacement1;c++, x--){
			listTrajectoire[2][c][0] = positionX;
			listTrajectoire[2][c][1] = positionY;
			listTrajectoire[2][c][2] = x;
			listTrajectoire[2][c][3] = positionY;
		}
		
		for(int y = positionY, c = 0; y >= positionY - deplacement1;c++, y--){
			listTrajectoire[3][c][0] = positionX;
			listTrajectoire[3][c][1] = positionY;
			listTrajectoire[3][c][2] = positionX;
			listTrajectoire[3][c][3] = y;
		}
		
		for(int x = positionX, y = positionY, c = 0; x >= positionX - deplacement1 && y <= positionY + deplacement2;c++, x--, y++){
			listTrajectoire[4][c][0] = positionX;
			listTrajectoire[4][c][1] = positionY;
			listTrajectoire[4][c][2] = x;
			listTrajectoire[4][c][3] = y;
		}
				
		for(int x = positionX, y = positionY, c = 0; x >= positionX - deplacement1 && y >= positionY - deplacement2;c++, x--, y--){
			listTrajectoire[5][c][0] = positionX;
			listTrajectoire[5][c][1] = positionY;
			listTrajectoire[5][c][2] = x;
			listTrajectoire[5][c][3] = y;
		}
		
		for(int x = positionX, y = positionY, c = 0; x <= positionX + deplacement1 && y <= positionY + deplacement2;c++, x++, y++){
			listTrajectoire[6][c][0] = positionX;
			listTrajectoire[6][c][1] = positionY;
			listTrajectoire[6][c][2] = x;
			listTrajectoire[6][c][3] = y;
		}
		
		for(int x = positionX, y = positionY, c = 0; x <= positionX + deplacement1 && y >= positionY - deplacement2;c++, x++, y--){
			listTrajectoire[7][c][0] = positionX;
			listTrajectoire[7][c][1] = positionY;
			listTrajectoire[7][c][2] = x;
			listTrajectoire[7][c][3] = y;
		}
		
		return listTrajectoire;
	}
		
	public int getDeplacement1() {
		return deplacement1;
	}



	public int getDeplacement2() {
		return deplacement2;
	}



	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	public void setPosition(int posX, int posY) {				
		this.positionX = posX;
		this.positionY = posY;
	}
	
	@Override
	public boolean verifDeplacement(int posX, int posY) {
		
		return true;
	}

	public Couleur getCouleur() {
		return couleur;
	}
}
