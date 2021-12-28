package pm.travelcommunity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.travel.TravelLike;

@Repository
public interface TravelLikeRepository extends CrudRepository<TravelLike, Integer> {
    TravelLike findByUser_IdAndTravel_Id(int userID, int travelID);
}
