package pm.travelcommunity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pm.travelcommunity.domain.scene.Scene;

import java.util.Set;

@Repository
public interface SceneRepository extends CrudRepository<Scene, Integer> {
    Set<Scene> findAllByOrderByCreateTimeDesc();

    Scene findById(int id);
}
