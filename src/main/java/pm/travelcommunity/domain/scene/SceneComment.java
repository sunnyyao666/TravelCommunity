package pm.travelcommunity.domain.scene;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;
import pm.travelcommunity.domain.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author YHT
 **/
@Entity
public class SceneComment extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private User author;

    private String authorName;

    @ManyToOne
    @JsonIgnore
    private Scene scene;

    private String content;

    private int score;

    public SceneComment() {
    }

    public SceneComment(User author, Scene scene, String content, int score) {
        this.author = author;
        this.authorName = author.getUsername();
        this.scene = scene;
        this.content = content;
        this.score = score;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        this.authorName = author.getUsername();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

