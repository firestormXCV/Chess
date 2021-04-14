package Piece;

public class Tour extends PieceAbs {
	
	private final static int TournitPosX = 4;
	private final static int TourinitPosY = 1;
	private final static int DiffEntreBlancNoir = 7;
	private final static int TourMobilite = 7;
	private final static int TourMobilite2 = 0;

	public Tour(Couleur couleur, String nom, int positionInitX, int positionInitY, int deplacement1, int deplacement2, boolean secondTour) {
		super(couleur, nom, positionInitX, positionInitY, deplacement1, deplacement2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attaquer(int posX, int posY) {
		// TODO Auto-generated method stub
		
	}


}
