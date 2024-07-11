package analysis.buildingmanager.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("analysis.buildingmanager")
public class BuildingManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingManagerApplication.class, args);
    }

}
