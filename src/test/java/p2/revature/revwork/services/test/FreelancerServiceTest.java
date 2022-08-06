package p2.revature.revwork.services.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import p2.revature.revwork.RevWorkBootApplication;
import p2.revature.revwork.data.FreelancerRepository;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.services.FreelancerService;
import p2.revature.revworkboot.models.Freelancer;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Usernameandpassword;

@SpringBootTest(classes = RevWorkBootApplication.class)
public class FreelancerServiceTest {

	@MockBean
	private FreelancerRepository fr;
	
	@MockBean
	private Usernameandpassword login;
	
	@MockBean 
	private Freelancerregister freeReg;
	
	@Autowired
	FreelancerService fs;
	
	@Test
	public void getAllFree() {
		
		List<FreelancerData> list = new ArrayList<>();
		list.add(new FreelancerData());
		 
		Mockito.when(fr.findAll()).thenReturn(list);
		
		List<FreelancerData> test = fs.getAllFreelancers();
		Assertions.assertNotNull(test);
	}
	
	
	@Test
	public void verifyLogin() {
		List<FreelancerData> list = new ArrayList<>();
		list.add(new FreelancerData(1, "name","about", "experience", "email", "username", "password"));
		FreelancerData fd = new  FreelancerData(1, "name","about", "experience", "email", "username", "password");
		
		Mockito.when(login.getUsername()).thenReturn("username");
		Mockito.when(login.getPassword()).thenReturn("password");
		Mockito.when(fr.findByUsername(login.getUsername())).thenReturn(list);
		
		FreelancerData test = fs.verifyLogin(login);
		Assertions.assertNotNull(test);
		Assertions.assertEquals(fd.toString(), test.toString());
	}
	
	@Test 
	public void doNotLogin() {
		List<FreelancerData> list = new ArrayList<>();
		list.add(new FreelancerData(1, "name","about", "experience", "email", "username", "password"));
		
		Mockito.when(login.getUsername()).thenReturn("username");
		Mockito.when(login.getPassword()).thenReturn("pd");
		Mockito.when(fr.findByUsername(login.getUsername())).thenReturn(list);
		
		FreelancerData test = fs.verifyLogin(login);
		Assertions.assertNull(test);
	}
	
	@Test 
	public void noUsernameLogin() {
		List<FreelancerData> list = new ArrayList<>();
		
		Mockito.when(login.getUsername()).thenReturn("username");
		Mockito.when(login.getPassword()).thenReturn("pd");
		Mockito.when(fr.findByUsername(login.getUsername())).thenReturn(list);
		
		FreelancerData test = fs.verifyLogin(login);
		Assertions.assertNull(test);
	}
	
	@Test
	public void verifyRegistration() {
		List<FreelancerData> list = new ArrayList<>();

		Mockito.when(freeReg.getUsername()).thenReturn("username");
		Mockito.when(fr.findByName(freeReg.getUsername())).thenReturn(list);

		boolean test = fs.verifyRigistration(freeReg);
		Assertions.assertNotNull(test);
		Assertions.assertTrue(test);

	}
	
	@Test
	public void doNotRegister() {
		List<FreelancerData> list = new ArrayList<>();
		list.add(new FreelancerData(1, "name","about", "experience", "email", "username", "password"));

		Mockito.when(freeReg.getUsername()).thenReturn("username");
		Mockito.when(fr.findByName(freeReg.getUsername())).thenReturn(list);

		boolean test = fs.verifyRigistration(freeReg);
		Assertions.assertNotNull(test);
		Assertions.assertFalse(test);
	}
	
}
