package p2.revature.revwork;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RevWorkBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevWorkBootApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfig() {
		
		Properties props = new Properties();
		
		InputStream propsFile = RevWorkBootApplication.class.getClassLoader()
				.getResourceAsStream("cors.properties");
		try {
			props.load(propsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		final String allowedOrigins = props.getProperty("cors.allowedorigins");
		
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
					.allowedOrigins(allowedOrigins)
					.allowedHeaders("*")
					.exposedHeaders("Auth")
					.allowCredentials(false);
			}
		};
	}

	
	
}
