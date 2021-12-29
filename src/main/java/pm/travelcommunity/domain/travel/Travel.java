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
    private User author;

    private String authorName;
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

    public Travel(User author, String content) {
        this.author = author;
        this.authorName = author.getUsername();
        this.content = content;
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

    public void addTravelComment(TravelComment travelComment) {
        this.travelComments.add(travelComment);
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

    public void removeTravelStar(TravelStar travelStar) {
        this.travelStars.remove(travelStar);
    }

    public boolean isStared() {
        return isStared;
    }

    public void setStared(boolean stared) {
        isStared = stared;
    }
}

