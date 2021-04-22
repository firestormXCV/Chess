package Piece;

public class Roi extends PieceAbs {
	
	private final static int RoinitPosX = 3;
	private final static int RoinitPosY = 0;
	private final static int DiffEntreBlancNoir = 7;
	private final static int RoiMobilite1 = 1;
	private final static int RoiMobilite2 = 1;
	private final static String RoiNom = "r";
	
	public Roi(Couleur couleur) {
		
		super(couleur, RoiNom, RoinitPosX, RoinitPosY, RoiMobilite1, RoiMobilite2);
		
		if (couleur == Couleur.blanc)
			setPosition(getPositionX(),getPositionY() + DiffEntreBlancNoir);
	}


	
	
	@Override
	public void attaquer(int posX, int posY) {
		// TODO Auto-generated method stub
		
	}

	
	

}
