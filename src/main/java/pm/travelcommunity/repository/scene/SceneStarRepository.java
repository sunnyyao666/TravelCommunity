package pm.travelcommunity.repository.scene;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.scene.SceneStar;

@Repository
public interface SceneStarRepository extends CrudRepository<SceneStar, Integer> {
    SceneStar findByUser_IdAndScene_Id(int userID, int sceneID);
}
