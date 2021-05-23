package exception;

public class SaisieException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SaisieException() {
		super("La saisie n'est pas au format souhaité, ou n'est juste pas valide\nmerci de recommencer");
	}
}
