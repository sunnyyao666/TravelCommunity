package pm.travelcommunity.exception;

/**
 * @author: YHT
 **/
public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Fail to authenticate the user.");
    }
}
