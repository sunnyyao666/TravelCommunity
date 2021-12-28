package pm.travelcommunity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.travel.TravelStar;

@Repository
public interface TravelStarRepository extends CrudRepository<TravelStar, Integer> {
    TravelStar findByUser_IdAndTravel_Id(int userID, int travelID);

    void deleteByUser_IdAndTravel_Id(int userID, int travelID);
}
