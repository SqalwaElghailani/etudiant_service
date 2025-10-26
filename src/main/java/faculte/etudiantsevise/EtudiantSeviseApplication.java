package faculte.etudiantsevise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class EtudiantSeviseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantSeviseApplication.class, args);
    }

}
