package Piece;

public class Roi extends PieceAbs {
	
	private final static int RoinitPosX = 4;
	private final static int RoinitPosY = 1;
	private final static int DiffEntreBlancNoir = 7;
	private final static int RoiMobilite1 = 1;
	private final static int RoiMobilite2 = 1;
	
	
	public Roi(Couleur couleur, String nom, int positionInitX, int positionInitY, int deplacement1, int deplacement2) {
		
		super(couleur, "r", RoinitPosX, RoinitPosY, RoiMobilite1, RoiMobilite2);
		
		if (couleur == Couleur.blanc)
			setPosition(getPositionX(),getPositionY() + DiffEntreBlancNoir);
	}


	
	
	@Override
	public void attaquer(int posX, int posY) {
		// TODO Auto-generated method stub
		
	}

	
	

}
