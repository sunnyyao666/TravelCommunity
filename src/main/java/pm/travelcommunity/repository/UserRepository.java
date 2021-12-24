package pm.travelcommunity.repository;

import org.springframework.data.repository.CrudRepository;
import pm.travelcommunity.domain.User;

/**
 * @author: YHT
 **/
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
