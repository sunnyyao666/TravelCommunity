package pm.travelcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author: YHT
 **/
@SpringBootApplication
@EnableJpaAuditing
public class TravelCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelCommunityApplication.class, args);
    }

}
