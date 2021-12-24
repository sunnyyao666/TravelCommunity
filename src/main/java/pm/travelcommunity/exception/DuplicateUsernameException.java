package pm.travelcommunity.exception;

/**
 * @author: YHT
 **/
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String username) {
        super(username + " has been registered");
    }
}
