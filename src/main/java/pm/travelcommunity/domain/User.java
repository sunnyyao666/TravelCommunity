package pm.travelcommunity.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.scene.SceneStar;
import pm.travelcommunity.domain.travel.Travel;
import pm.travelcommunity.domain.travel.TravelComment;
import pm.travelcommunity.domain.travel.TravelLike;
import pm.travelcommunity.domain.travel.TravelStar;

import javax.persistence.*;
import java.util.*;

/**
 * @author YHT
 **/
@Entity
public class User extends BaseEntity {
    @Column(unique = true)
    private String username;

    private String password;
    private String email;
    private String phone;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "author")
    private Set<Travel> travels = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<TravelComment> travelComments = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<TravelLike> travelLikes = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<TravelStar> travelStars = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<SceneStar> sceneStars = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Travel> getTravels() {
        return travels;
    }

    public void setTravels(Set<Travel> travels) {
        this.travels = travels;
    }

    public Set<TravelComment> getTravelComments() {
        return travelComments;
    }

    public void setTravelComments(Set<TravelComment> travelComments) {
        this.travelComments = travelComments;
    }

    public Set<TravelLike> getTravelLikes() {
        return travelLikes;
    }

    public void setTravelLikes(Set<TravelLike> travelLikes) {
        this.travelLikes = travelLikes;
    }

    public void removeTravelLike(TravelLike travelLike) {
        this.travelLikes.remove(travelLike);
    }

    public Set<TravelStar> getTravelStars() {
        return travelStars;
    }

    public void setTravelStars(Set<TravelStar> travelStars) {
        this.travelStars = travelStars;
    }

    public void removeTravelStar(TravelStar travelStar) {
        this.travelStars.remove(travelStar);
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
}
