package pm.travelcommunity.controller.request;

/**
 * @author YHT
 **/
public class TravelRequest {
    private String username;
    private int travelID;
    private String content;

    public TravelRequest(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTravelID() {
        return travelID;
    }

    public void setTravelID(int travelID) {
        this.travelID = travelID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

