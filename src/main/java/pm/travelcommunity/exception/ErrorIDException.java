package pm.travelcommunity.exception;

/**
 * @author YHT
 **/
public class ErrorIDException extends RuntimeException {
    public ErrorIDException(String type, int id) {
        super("Fail to find " + type + " with id: " + id);
    }
}

