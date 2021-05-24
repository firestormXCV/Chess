 package plateau;
/**
 * Cette classe n'est pas indispensable en l'etat, cependant elle serai necessaire si on voulait implementer plus de regle du jeu, tel que le pat par exemple
 */
public class Case {
	
	private String occupePar; 	//Est ce qu'une piece occupe la case
	
	public Case() {
		this.setOcuppePar(" ");
	}
	
	public Case(Case c) {
		this.setOcuppePar(c.getOccupe());

	}

	public String getOccupe() {
		return occupePar;
	}
	


	public void setOcuppePar(String occupe) {
		this.occupePar = occupe;
	}

	
	
}
