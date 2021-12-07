package logic;

public class InvalidDirectionException extends Exception   {
	// you CAN add SerialVersionID if eclipse gives you warning
	private String message;

	public InvalidDirectionException () {
		this.message = "The direction is null. You can't move.";
	}
	
    public InvalidDirectionException (String message) {
        this.message = message;
    }

    public String getMessage() {
    	return this.message;
    }
}
