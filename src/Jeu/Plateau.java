package Jeu;

public class Plateau {
	
	public Plateau() {
	}
	
	public String toString() {
		StringBuilder Plateau = new StringBuilder();
		Plateau.append("   a   b   c   d   e   f   g   h\n\n");
		for(int i = 8 ; i > 0 ; i--) {
			Plateau.append("  --- --- --- --- --- --- --- ---\n\n");
			Plateau.append( i + " |   |   |   |   |   |   |   |   | " + i + "\n\\n");
		}
		Plateau.append("  --- --- --- --- --- --- --- ---\n\n");
		Plateau.append("   a   b   c   d   e   f   g   h\n");
		
		return Plateau.toString();
		}
}
