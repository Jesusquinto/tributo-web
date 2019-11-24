package co.tributo.cliente;

import co.tributo.cliente.config.AppProperties;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableScheduling
public class Tributo implements CommandLineRunner {
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    
    
    
    
    


	public static void main(String[] args) {
		SpringApplication.run(Tributo.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        System.out.println(passwordEncoder.encode("123456789")); 



    }
}
