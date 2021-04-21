package Piece;

public class Tour extends PieceAbs {
	
	private final static int TournitPosX = 1;
	private final static int TourinitPosY = 1;
	private final static int DiffEntreBlancNoir = 7;
	private final static int TourMobilite1 = 7;
	private final static int TourMobilite2 = 0;
	private final static String TourNom = "t";

	public Tour(Couleur couleur, boolean secondeTour) {
		super(couleur, TourNom, TournitPosX, TourinitPosY, TourMobilite1, TourMobilite2);
		
		if (couleur == Couleur.blanc)
			setPosition(getPositionX(),getPositionY() + DiffEntreBlancNoir);
		if (secondeTour)
			setPosition(getPositionX() + DiffEntreBlancNoir, getPositionY());
	}

	@Override
	public void attaquer(int posX, int posY) {
		// TODO Auto-generated method stub
		
	}


}
