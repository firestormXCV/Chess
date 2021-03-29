 package Jeu;

public class Case {
	
	private boolean occupe; 	//Est ce qu'une piece occupe la case
	private boolean menace; 	//Est ce qu'une piece adverse menace la case
	
	public Case() {
		this.setOccupe(false);
		this.setMenace(false);
	}

	public boolean getOccupe() {
		return occupe;
	}
	
	public boolean getMenace() {
		return menace;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

	public void setMenace(boolean menace) {
		this.menace = menace;
	}
	
	
}
