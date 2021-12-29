package pm.travelcommunity.repository.scene;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.scene.Contribution;

@Repository
public interface ContributionRepository extends CrudRepository<Contribution, Integer> {
    Contribution findById(int id);
}
