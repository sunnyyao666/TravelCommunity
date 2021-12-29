package pm.travelcommunity.domain.scene;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;

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

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "scene")
    private Set<Contribution> contributions = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "scene")
    private Set<SceneComment> sceneComments = new HashSet<>();

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

    public Set<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(Set<Contribution> contributions) {
        this.contributions = contributions;
    }

    public Set<SceneComment> getSceneComments() {
        return sceneComments;
    }

    public void setSceneComments(Set<SceneComment> sceneComments) {
        this.sceneComments = sceneComments;
    }

    public void addSceneComment(SceneComment sceneComment) {
        this.sceneComments.add(sceneComment);
    }
}

