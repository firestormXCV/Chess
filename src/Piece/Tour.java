package Piece;

public class Tour extends PieceAbs {
	
	private final static int TournitPosX = 0;
	private final static int TourinitPosY = 0;
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
	
	public Tour(Tour piece) { 	// 2eme constructor corredpondant a la creation d'un plateau de test et donc la copie d'une piece qui est passe en parametre
		
		super(piece.getCouleur(), piece.getNom(), piece.getPositionX(), piece.getPositionY(), piece.getDeplacement1(), piece.getDeplacement2() );
	}
}
