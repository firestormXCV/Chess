 package plateau;

public class Case {
	
	private String occupePar; 	//Est ce qu'une piece occupe la case
	private boolean menace; 	//Est ce qu'une piece adverse menace la case

	
	public Case() {
		this.setOcuppePar(" ");
		this.setMenace(false);
	}
	
	public Case(Case c) {
		this.setOcuppePar(c.getOccupe());
		this.setMenace(false);
	}

	public String getOccupe() {
		return occupePar;
	}
	
	public boolean getMenace() {
		return menace;
	}

	public void setOcuppePar(String occupe) {
		this.occupePar = occupe;
	}

	public void setMenace(boolean menace) {
		this.menace = menace;
	}
	
	
}
