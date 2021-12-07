package logic;

public class InvalidHistoryException extends Exception {

	private String message;
	
	public InvalidHistoryException () {
		this.message = "Invalid history";
	}
	
    public InvalidHistoryException (String message) {
        this.message = message;
    }

    public String getMessage() {
    	return this.message;
    }

}
