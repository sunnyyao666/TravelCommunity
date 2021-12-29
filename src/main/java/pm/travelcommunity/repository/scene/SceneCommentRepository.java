package pm.travelcommunity.repository.scene;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.scene.SceneComment;

@Repository
public interface SceneCommentRepository extends CrudRepository<SceneComment, Integer> {
}
