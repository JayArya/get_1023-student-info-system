package exception;
//public class AgeException extends Exception{	// checked
//public class AgeException extends RuntimeException{	// unchecked
public class AgeException extends Throwable{
	public AgeException(String message) {
		super(message);
	}
}
