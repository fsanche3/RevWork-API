package p2.revature.revwork.controllers.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import p2.revature.revwork.controllers.FreelancerController;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.models.data.JobApplication;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Profile;
import p2.revature.revwork.services.FreelancerService;
import p2.revature.revwork.services.JobApplicationService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revwork.services.ProfileService;
import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.models.Application;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employer;
import p2.revature.revworkboot.models.Freelancer;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Portfolio;

@WebMvcTest(controllers = FreelancerController.class)
public class FreelancerControllerTest {

	@MockBean
	private FreelancerService freeServ;

	@MockBean
	private Freelancerregister freeReg;

	@MockBean
	private JobApplicationService jas;

	@MockBean
	private OpenJobsService ojs;

	@MockBean
	private ProfileService profileServ;

	@MockBean
	private JwtUtil jwt;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper om = new ObjectMapper();

	@Test
	public void getJobs() throws Exception {
		List<OpenJobs> list = new ArrayList<>();
		OpenJobs j1 = new OpenJobs(1);
		j1.setDescription("");
		EmployerData d = new EmployerData();
		d.setId(1);
		j1.setEmployer(d);
		
		list.add(j1);
		
		List<Availablejob> ajl = new ArrayList<>();
		
		Availablejob aj = new Availablejob();
		aj.setId(1);
		aj.setDescription("");
		ajl.add(aj);
		
		Employer e = new Employer();
		e.setId(1);
		aj.setEmployerid(e);
		String value = om.writeValueAsString(ajl);
		
		Mockito.when(ojs.getAllJobs()).thenReturn(list);

		mockMvc.perform(get("/freelancer/get_jobs")).
		andExpect(status().isOk()).
		andExpect(content().json(value));
	}

	@Test
	public void getJobById() throws Exception {

		OpenJobs job = new OpenJobs(1);
		String value = om.writeValueAsString(job);
		Mockito.when(ojs.findById(job.getId())).thenReturn(job);

		mockMvc.perform(get("/freelancer/1")).andExpect(status().isOk()).andExpect(content().json(value));

	}

	@Test
	public void Register() throws JsonProcessingException, Exception {

		Freelancerregister input = new Freelancerregister();

		Mockito.when(freeServ.verifyRigistration(input)).thenReturn(true);

		mockMvc.perform(post("/freelancer/register").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(input))).andExpect(status().isCreated());

	}

	@Test
	public void doNotRegister() throws JsonProcessingException, Exception {

		Freelancerregister input = new Freelancerregister();

		Mockito.when(freeServ.verifyRigistration(input)).thenReturn(false);

		mockMvc.perform(post("/freelancer/register").contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(input))).andExpect(status().isNotAcceptable());

	}
	

	@Test
	public void createProfile() throws JsonProcessingException, Exception {
		Profile prof = new Profile(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		Freelancer free = new Freelancer();
		free.setId(1);
		port.setFreelancerid(free);
		FreelancerData freeData = new FreelancerData(1);
		prof.setFreelancer(freeData);
		
		String value = om.writeValueAsString(port);
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(profileServ.addProfile(prof)).thenReturn(prof);
		
		mockMvc.perform(post("/freelancer/create_profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isCreated())
		.andExpect(content().json(value));
		
	}
	
	@Test
	public void doNotCreateProfile() throws JsonProcessingException, Exception {
		Profile prof = new Profile(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		Freelancer free = new Freelancer();
		free.setId(5);
		port.setFreelancerid(free);
		FreelancerData freeData = new FreelancerData(5);
		prof.setFreelancer(freeData);
		
		String value = om.writeValueAsString(port);
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(profileServ.addProfile(prof)).thenReturn(prof);
		
		mockMvc.perform(post("/freelancer/create_profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void cannotDeleteProfile() throws JsonProcessingException, Exception {
		Profile prof = new Profile(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		Freelancer free = new Freelancer();
		free.setId(1);
		port.setFreelancerid(free);
		FreelancerData freeData = new FreelancerData(1);
		prof.setFreelancer(freeData);
		
		String value = om.writeValueAsString(port);
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(profileServ.deleteProfile(prof)).thenReturn(false);
		
		mockMvc.perform(delete("/freelancer/delete_profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void forbiddenDeleteProfile() throws JsonProcessingException, Exception {
		Profile prof = new Profile(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		Freelancer free = new Freelancer();
		free.setId(4);
		port.setFreelancerid(free);
		FreelancerData freeData = new FreelancerData(4);
		prof.setFreelancer(freeData);
		
		String value = om.writeValueAsString(port);
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(freeServ.findById(Mockito.anyInt())).thenReturn(freeData);
		Mockito.when(profileServ.deleteProfile(prof)).thenReturn(true);
		
		mockMvc.perform(delete("/freelancer/delete_profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isForbidden());
		
	}
	
	@Test
	public void cannotEditProfile() throws JsonProcessingException, Exception {
		
		Profile prof = new Profile(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		Freelancer free = new Freelancer();
		free.setId(1);
		port.setFreelancerid(free);
		FreelancerData freeData = new FreelancerData(1);
		prof.setFreelancer(freeData);
		
		String value = om.writeValueAsString(port);
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(profileServ.deleteProfile(prof)).thenReturn(false);
		
		mockMvc.perform(put("/freelancer/edit_profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isNotFound())
		.andExpect(content().json(value));
		
	}
	
	@Test
	public void forbiddenEditProfile() throws JsonProcessingException, Exception {
		
		Profile prof = new Profile(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		Freelancer free = new Freelancer();
		free.setId(4);
		port.setFreelancerid(free);
		FreelancerData freeData = new FreelancerData(4);
		prof.setFreelancer(freeData);
		
		String value = om.writeValueAsString(port);
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(profileServ.deleteProfile(prof)).thenReturn(true);
		
		mockMvc.perform(put("/freelancer/edit_profile").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isForbidden());
		
	}
		
	@Test
	public void getProfileById() throws JsonProcessingException, Exception {
		
		JobApplication app = new JobApplication(1);
		Profile prof = new Profile(1);
		OpenJobs job = new OpenJobs(1);
		Portfolio port = new Portfolio();
		port.setId(1);
		app.setOpenJob(job);
 		Application app1 = new Application();
 		Availablejob aj = new Availablejob();
 		app1.setId(1);
 		aj.setId(1);
 		app1.setJobid(aj);
 		app1.setPortfolioid(port);
 		 List<Profile> list = new ArrayList<>();
 		 List<Portfolio> listP = new ArrayList<>();
 		 listP.add(port);
 		 list.add(prof);
 		 
 		
 		String value = om.writeValueAsString(listP);
 		
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(freeServ.getProilesById(Mockito.anyInt())).thenReturn(list);
		
		mockMvc.perform(get("/freelancer/1/profiles").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isOk())
		.andExpect(content().json(value));

	}
	
	@Test
	public void doNotGetProfileById() throws JsonProcessingException, Exception {
		
		JobApplication app = new JobApplication(1);
		Profile prof = new Profile(2);
		OpenJobs job = new OpenJobs(1);
		Portfolio port = new Portfolio();
		port.setId(2);
		app.setOpenJob(job);
 		Application app1 = new Application();
 		Availablejob aj = new Availablejob();
 		app1.setId(1);
 		aj.setId(1);
 		app1.setJobid(aj);
 		app1.setPortfolioid(port);
 		 List<Profile> list = new ArrayList<>();
 		 List<Portfolio> listP = new ArrayList<>();
 		 listP.add(port);
 		 list.add(prof);
 		 
 		
 		String value = om.writeValueAsString(listP);
 		
		String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJmcmVlbGFuY2VyIl0sImlzcyI6ImF1dGgwIiwiZnVsbG5hbWUiOiJCZW4gTiBKZXJyeSdzIiwiaWQiOjEsInVzZXJuYW1lIjoiR3JhdGVmdWwgRnJlZWxhbmNlciJ9.ujGxNU4t7QNfJLmzyVROFTga-_WTgNVPGp-5g5qeI4w";
				
		Mockito.when(jwt.getId(Mockito.anyString())).thenReturn(1);
		Mockito.when(freeServ.getProilesById(Mockito.anyInt())).thenReturn(list);
		
		mockMvc.perform(get("/freelancer/2/profiles").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",  token)
				.content(om.writeValueAsString(port)))
		.andExpect(status().isForbidden());

	}
}

