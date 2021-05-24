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
	
	public Roi(Roi piece) {	// 2eme constructor corredpondant a la creation d'un plateau de test et donc la copie d'une piece qui est passe en parametre
	
	super(piece.getCouleur(), piece.getNom(), piece.getPositionX(), piece.getPositionY(), piece.getDeplacement1(), piece.getDeplacement2() );
	}

}
