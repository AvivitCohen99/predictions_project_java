package world;

public class ParseException extends Exception{
    private String message;
    public ParseException(String message) {
        this.message = message;
    }

    @Override
   public String getMessage() {
        return this.message;
    }
}
