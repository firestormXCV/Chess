package joueur;
/**
 * Class IA1
 * Correspond au code d'une IA de niveau 1, elle est capable de jouer des coups toujours valide mais de maniere aleatoire
 * Si elle est en echeque elle ce debrouille pour ne plus letre au tour suivant, sauf si il y a echec et mat
 */
import java.util.ArrayList;
import java.util.Random;

import Piece.Couleur;
import Piece.IPiece;
import plateau.Echequier;

public class IA1 implements IJoueur {
	
	private String nom;
	private Couleur couleur;
	private ArrayList<int[][][]> coupDispo;		//Correspond a la liste de tout les coups possible pour tout les piece
												//Tout les coups possible d'une piece sont contenues d'un un tableau de type int[][][]
	
	public IA1(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
		this.coupDispo = new ArrayList<>();
	}
	/**
	 * La methode jouer coup sert a l'IA a jouer son coup et a recommencer en cas d'erreur 
	 * @param e L'echequier sur lequel l'IA va jouer
	 */
	@Override
	public void jouerCoup(Echequier e) {
		System.out.println("Joueur " + this.couleur + " c'est a vous de jouer !");
		e.actualiser();
		actualiserCoup(e);
		int[] mouvement = new int[4];
		
		
		boolean valide = false;
				
			while (!valide) {
				try {
					mouvement = saisie();				//Choix du coup
					e.deplacement(mouvement, couleur);	//Deplacement de la piece sur l'echequier
					valide = true;
				}catch (Exception exception) {
					valide = false;						//Recommence si une exception est levee
				}
			}
		
		return;
	}

	@Override
	public String getNom() {
		
		return nom;
	}
	/**
	 * Permet a l'IA de chosir un coup aleatoirement parmis tout ceux possible
	 * @return un tableau d'int qui contient 4 int, celon le format : SourceX SourceY DestinationX DestinationY
	 */
	@Override
	public int[] saisie() {
		
		Random rand = new Random();
		int i = rand.nextInt(coupDispo.size());	//Entre 0 et la taille de l'arraylist
		int j = rand.nextInt(8);				//Entre 0 et 7, c'est le maximum possible quoi qu'il arrive, cf calcul de tout les coups possible dans l'echequier
		int k = rand.nextInt(7);				//Entre 0 et 6
		k++;									//k passe entre 1 et 7, c'est le maximum possible quoi qu'il arrive, cf calcul de tout les coups possible dans l'echequier

		boolean loop = true;
		
		int[] mouvement = new int[4];
		
		while(loop) {
			
			mouvement = coupDispo.get(i)[j][k];	//Choisie un coup random dans la liste
			
			if ((mouvement[0] != 0 || mouvement[1] != 0 || mouvement[2] != 0 || mouvement[3] != 0) &&
				(mouvement[0] != mouvement[2] || mouvement[1] != mouvement[3])) {		//verifie que le coup n'est pas nul est ne correspond pas a un mouvement sur place
				loop = false;
				
				return mouvement;
			}  else {									//Sinon on choisie un autre coup
				i = rand.nextInt(coupDispo.size());
				j = rand.nextInt(8);
				k = rand.nextInt(8);
			}
		}
		return mouvement;		
	}
	/**
	 * Actualise la liste de tout les coups possible pour l'IA
	 * @param e l'echequier sur lequel elle joue
	 */
	public void actualiserCoup(Echequier e) {
		
		 coupDispo.removeAll(coupDispo);	//Vide l'araylist
		ArrayList<int[][][]> tmpCoupDispo = e.getToutCoupPossible(); //Remplis une ArrayList temporaire avec la liste des coups possibles sur le plateau ( Noir et Blanc donc)
		
		
		for (IPiece piece : e.getPion()) {									//Verifie pour chaque piece si elle correspond a la couleur de l'IA qui joue, si oui on ahjoute le coup correspondant dans l'arraylist definitive
			if (piece.getCouleur() == couleur)
				coupDispo.add(tmpCoupDispo.get(e.getPion().indexOf(piece)));
		}
		
		
	}
	
}
