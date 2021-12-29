package pm.travelcommunity.controller.request;

/**
 * @author YHT
 **/
public class SceneRequest {
    private int sceneID;
    private String username;

    public SceneRequest() {
    }

    public int getSceneID() {
        return sceneID;
    }

    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

