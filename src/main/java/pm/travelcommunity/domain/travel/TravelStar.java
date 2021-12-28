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
public class TravelStar extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    User user;

    @ManyToOne
    Travel travel;

    public TravelStar() {
    }

    public TravelStar(User user, Travel travel) {
        this.user = user;
        this.travel = travel;
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
}

