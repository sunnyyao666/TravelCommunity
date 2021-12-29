package pm.travelcommunity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import pm.travelcommunity.domain.User;
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
    public CommandLineRunner dataLoader(UserRepository userRepository, TravelRepository travelRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                init(userRepository, travelRepository);
            }
        };
    }

    private void init(UserRepository userRepository, TravelRepository travelRepository) {
        User user = new User("admin","admin123","","");
        userRepository.save(user);
    }
}
