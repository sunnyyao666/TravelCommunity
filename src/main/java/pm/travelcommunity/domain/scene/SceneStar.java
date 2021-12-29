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
public class SceneStar extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    private Scene scene;

    public SceneStar() {
    }

    public SceneStar(User user, Scene scene) {
        this.user = user;
        this.scene = scene;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

