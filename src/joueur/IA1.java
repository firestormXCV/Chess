package joueur;

import java.util.ArrayList;
import java.util.Random;

import Piece.Couleur;
import Piece.IPiece;
import plateau.Echequier;

public class IA1 implements IJoueur {
	
	private String nom;
	private Couleur couleur;
	private ArrayList<int[][][]> coupDispo;
	
	public IA1(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		this.coupDispo = new ArrayList<>();
	}

	@Override
	public boolean jouerCoup(Echequier e, boolean enEchec) {
		System.out.println("Joueur " + this.couleur + " c'est a vous de jouer !");
		actualiserCoup(e);
		int[] mouvement = new int[4];
		
		
		boolean valide = false;
				
			while (!valide) {
				try {
					mouvement = saisie();
					e.deplacement(mouvement, couleur);
					valide = true;
				}catch (StringIndexOutOfBoundsException exception) {
					valide = false;
					System.out.println(exception);
				}
			}
		
		return false;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] saisie() {
		
		Random rand = new Random();
		int i = rand.nextInt(coupDispo.size());
		int j = rand.nextInt(8);
		int k = rand.nextInt(7);
		k++;

		boolean loop = true;
		
		int[] mouvement = new int[4];
		
		while(loop) {
			
			mouvement = coupDispo.get(i)[j][k];
			
			if ((mouvement[0] != 0 || mouvement[1] != 0) ||
				(mouvement[0] != mouvement[2] || mouvement[1] != mouvement[3])) {
				loop = false;
				
				return mouvement;
			}  else {
				i = rand.nextInt(coupDispo.size());
				j = rand.nextInt(8);
				k = rand.nextInt(8);
			}
		}
		return mouvement;		
	}

	public void actualiserCoup(Echequier e) {
		
		 
		ArrayList<int[][][]> tmpCoupDispo = e.getToutCoupPossible();
		
		
		for (IPiece piece : e.getPion()) {
			if (piece.getCouleur() == couleur)
				coupDispo.add(tmpCoupDispo.get(e.getPion().indexOf(piece)));
		}
		
		
	}
	
}
