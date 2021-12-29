package pm.travelcommunity.repository.travel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.travel.TravelComment;

@Repository
public interface TravelCommentRepository extends CrudRepository<TravelComment, Integer> {

}
