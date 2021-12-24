package pm.travelcommunity.controller.request;

/**
 * @author: Yao Hongtao
 **/
public class UserAuthRequest {
    private String username;
    private String password;

    public UserAuthRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

