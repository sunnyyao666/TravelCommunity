package pm.travelcommunity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.travel.Travel;

@Repository
public interface TravelRepository extends CrudRepository<Travel, Integer> {
    Travel findById(int id);
}
