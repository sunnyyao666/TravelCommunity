package pm.travelcommunity.domain.travel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;
import pm.travelcommunity.domain.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author YHT
 **/

@Entity
public class TravelComment extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private User author;

    private String authorName;

    @ManyToOne
    @JsonIgnore
    private Travel travel;

    private String content;

    public TravelComment() {
    }

    public TravelComment(User author, Travel travel, String content) {
        this.author = author;
        this.authorName = author.getUsername();
        this.travel = travel;
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

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

