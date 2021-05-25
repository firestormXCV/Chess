package TestsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Piece.Couleur;
import exception.SaisieException;
import plateau.Echequier;

class testPlateau {

	@Test
	void testEchiquiertoStringQuiFonctionne() throws SaisieException {
		Echequier EchiquierRéférence = new Echequier();
		Echequier Echiquier2test = new Echequier();
		int coup1[] = {0,7,0,1}; //Coup valide pour la tour blanche à gauche.
		EchiquierRéférence.deplacement(coup1, Couleur.blanc);
		Echiquier2test.deplacement(coup1, Couleur.blanc);	
		assertEquals(Echiquier2test.toString(), EchiquierRéférence.toString());
	}
	
	@Test
	void testEchiquierQuiLeveSaisieException(){ 
		Echequier EchiquierRéférence = new Echequier();
		Echequier Echiquier2test = new Echequier();
		int coup1[] = {0,7,1,4}; //Coup invalide pour une tour.
		try {
			Echiquier2test.deplacement(coup1, Couleur.blanc);
			fail ("Ca marche");
		} catch(Exception e) {
		}
		assertEquals(Echiquier2test.toString(), EchiquierRéférence.toString());
		// Le coup invalide a été refusé, la pièce reste au même endroit jusqu'au nouveau coup demandé à l'utilisateur.
	}
}
