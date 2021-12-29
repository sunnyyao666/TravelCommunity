package pm.travelcommunity.domain.scene;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;
import pm.travelcommunity.domain.travel.TravelStar;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YHT
 **/
@Entity
public class Scene extends BaseEntity {
    private String name;
    private String information;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "scene")
    @JsonIgnore
    private Set<SceneStar> sceneStars = new HashSet<>();

    @Transient
    private boolean isStared;

    public Scene() {
    }

    public Scene(String name, String information) {
        this.name = name;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Set<SceneStar> getSceneStars() {
        return sceneStars;
    }

    public void setSceneStars(Set<SceneStar> sceneStars) {
        this.sceneStars = sceneStars;
    }

    public void removeSceneStar(SceneStar sceneStar) {
        this.sceneStars.remove(sceneStar);
    }

    public boolean isStared() {
        return isStared;
    }

    public void setStared(boolean stared) {
        isStared = stared;
    }
}

