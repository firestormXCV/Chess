 package plateau;

public class Case {
	
	private String occupe; 	//Est ce qu'une piece occupe la case
	private boolean menace; 	//Est ce qu'une piece adverse menace la case

	
	public Case() {
		this.setOccupe(" ");
		this.setMenace(false);
	}
	
	public Case(Case c) {
		this.setOccupe(c.getOccupe());
		this.setMenace(false);
	}

	public String getOccupe() {
		return occupe;
	}
	
	public boolean getMenace() {
		return menace;
	}

	public void setOccupe(String occupe) {
		this.occupe = occupe;
	}

	public void setMenace(boolean menace) {
		this.menace = menace;
	}
	
	
}
