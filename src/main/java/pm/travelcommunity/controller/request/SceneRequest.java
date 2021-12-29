package pm.travelcommunity.controller.request;

/**
 * @author YHT
 **/
public class SceneRequest {
    private int sceneID;
    private String username;
    private String title;
    private String content;
    private int contributionID;
    private int score;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContributionID() {
        return contributionID;
    }

    public void setContributionID(int contributionID) {
        this.contributionID = contributionID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

