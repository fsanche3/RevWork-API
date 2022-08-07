package p2.revature.revwork.controllers.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import p2.revature.revwork.controllers.LoginController;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.services.EmployerService;
import p2.revature.revwork.services.FreelancerService;
import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.models.Usernameandpassword;

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {
	
	@MockBean 
	private JwtUtil jwt;
	
	@MockBean
	private Usernameandpassword loginAuth;
	
	@MockBean 
	private FreelancerService fs;
	
	@MockBean 
	private EmployerService es;
	
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper om = new ObjectMapper();
	
	@Test
	public void authorizeLoginFreelancer() throws JsonProcessingException, Exception {
		FreelancerData freelancer = new FreelancerData(1, "", "", "", "", "username", "password");
		Usernameandpassword auth = new Usernameandpassword();
		auth.setUsername("username");
		auth.setPassword("password");
		auth.setRole("freelancer");
		

		Mockito.when(fs.verifyLogin(auth)).thenReturn(freelancer);
		Mockito.when(jwt.getJwtBuilder()).thenReturn(JWT.create());
		
		
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(auth)))
		.andExpect(status().isOk());
	
	}
	
	@Test
	public void authorizeLoginEmployer() throws JsonProcessingException, Exception {
		EmployerData employer = new EmployerData(1, "", " ","username", "password");
		Usernameandpassword auth = new Usernameandpassword();
		auth.setUsername("username");
		auth.setPassword("password");
		auth.setRole("employer");
		

		Mockito.when(es.verifyLogin(auth)).thenReturn(employer);
		Mockito.when(jwt.getJwtBuilder()).thenReturn(JWT.create());
		
		
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(auth)))
		.andExpect(status().isOk());
	
	}
	
	@Test
	public void doNotAuthorizeLoginFreelancer() throws JsonProcessingException, Exception {
		FreelancerData freelancer = new FreelancerData(1, "", "", "", "", "user", "pass");
		Usernameandpassword auth = new Usernameandpassword();
		auth.setUsername("username");
		auth.setPassword("password");
		auth.setRole("freelancer");
		

		Mockito.when(fs.verifyLogin(auth)).thenReturn(null);
		Mockito.when(jwt.getJwtBuilder()).thenReturn(JWT.create());
		
		
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(auth)))
		.andExpect(status().isBadRequest());
	
	}
	
	@Test
	public void doNotAuthorizeLoginEmployer() throws JsonProcessingException, Exception {
		EmployerData employer = new EmployerData(1, "", " ","user", "pass");
		Usernameandpassword auth = new Usernameandpassword();
		auth.setUsername("username");
		auth.setPassword("password");
		auth.setRole("employer");
		

		Mockito.when(es.verifyLogin(auth)).thenReturn(null);
		Mockito.when(jwt.getJwtBuilder()).thenReturn(JWT.create());
		
		
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(auth)))
		.andExpect(status().isBadRequest());
	
	}
	
	
}
