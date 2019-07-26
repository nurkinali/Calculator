package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private YAMLConfig myConfig;
	
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
    
    public void run(String... args) throws Exception{
    	System.out.println("using environment: " + myConfig.getEnvironment());
    	System.out.println("name: " + myConfig.getName());
    	System.out.println("servers: " + myConfig.getServers());
    }

}