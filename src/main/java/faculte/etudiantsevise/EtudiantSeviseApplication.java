package faculte.etudiantsevise;

import faculte.etudiantsevise.config.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableFeignClients
@EnableConfigurationProperties(RsaKeys.class)

public class EtudiantSeviseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantSeviseApplication.class, args);
    }

}
