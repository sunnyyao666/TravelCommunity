package pm.travelcommunity.repository.travel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.travel.Travel;

import java.util.Set;

@Repository
public interface TravelRepository extends CrudRepository<Travel, Integer> {
    Travel findById(int id);

    Set<Travel> findAllByOrderByCreateTimeDesc();
}
