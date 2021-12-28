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
    private User user;

    @ManyToOne
    @JsonIgnore
    private Travel travel;

    private String content;

    public TravelComment() {
    }

    public TravelComment(User user, Travel travel, String content) {
        this.user = user;
        this.travel = travel;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

