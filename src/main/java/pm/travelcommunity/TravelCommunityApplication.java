package pm.travelcommunity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import pm.travelcommunity.domain.User;
import pm.travelcommunity.domain.scene.Contribution;
import pm.travelcommunity.domain.scene.Scene;
import pm.travelcommunity.domain.travel.Travel;
import pm.travelcommunity.repository.scene.ContributionRepository;
import pm.travelcommunity.repository.scene.SceneRepository;
import pm.travelcommunity.repository.travel.TravelRepository;
import pm.travelcommunity.repository.UserRepository;

/**
 * @author YHT
 **/
@SpringBootApplication
@EnableJpaAuditing
public class TravelCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelCommunityApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, TravelRepository travelRepository, SceneRepository sceneRepository, ContributionRepository contributionRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                init(userRepository, travelRepository, sceneRepository, contributionRepository);
            }
        };
    }

    private void init(UserRepository userRepository, TravelRepository travelRepository, SceneRepository sceneRepository, ContributionRepository contributionRepository) {
        User user = new User("admin", "admin123", "18302010000@fudan.edu.cn", "13812345678");
        userRepository.save(user);
        Travel travel = new Travel(user, "I like Shanghai.");
        travelRepository.save(travel);
        Scene scene = new Scene("Shanghai", "One of the most interesting cities in China.");
        sceneRepository.save(scene);
        Contribution contribution = new Contribution(user, scene, "food", "Shanghainese meat dumplings");
        contributionRepository.save(contribution);
    }
}
