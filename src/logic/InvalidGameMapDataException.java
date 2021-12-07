package logic;

public class InvalidGameMapDataException extends Exception{
	
	private String message;
	
	public InvalidGameMapDataException(String message) {
		this.message = message;
	}
	
	
	public String getMessage() {
		return this.message;
	}
}
