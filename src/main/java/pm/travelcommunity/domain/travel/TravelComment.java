package pm.travelcommunity.domain.travel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pm.travelcommunity.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author YHT
 **/

@Entity
public class TravelComment extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    private Travel travel;

    private String content;

    public TravelComment() {
    }

    public TravelComment(Travel travel, String content) {
        this.travel = travel;
        this.content = content;
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

