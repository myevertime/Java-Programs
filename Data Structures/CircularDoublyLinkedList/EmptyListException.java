import java.lang.IllegalStateException;

public class EmptyListException extends IllegalStateException {
	public EmptyListException() {
		super("The list is empty.");
	}
}
