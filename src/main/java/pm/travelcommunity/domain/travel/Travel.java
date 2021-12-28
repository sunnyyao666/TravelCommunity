package pm.travelcommunity.domain.travel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;
import pm.travelcommunity.domain.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YHT
 **/
@Entity
public class Travel extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private User user;

    private String content;
    private int likeNumber;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "travel")
    private Set<TravelComment> travelComments = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "travel")
    @JsonIgnore
    private Set<TravelLike> travelLikes = new HashSet<>();

    @Transient
    private boolean isLiked;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "travel")
    @JsonIgnore
    private Set<TravelStar> travelStars = new HashSet<>();

    @Transient
    private boolean isStared;

    public Travel() {
    }

    public Travel(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
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

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public Set<TravelStar> getTravelStars() {
        return travelStars;
    }

    public void setTravelStars(Set<TravelStar> travelStars) {
        this.travelStars = travelStars;
    }

    public boolean isStared() {
        return isStared;
    }

    public void setStared(boolean stared) {
        isStared = stared;
    }
}

