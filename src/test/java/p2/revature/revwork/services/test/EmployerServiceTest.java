package p2.revature.revwork.services.test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import p2.revature.revwork.RevWorkBootApplication;
import p2.revature.revwork.data.EmployerRepository;
import p2.revature.revwork.data.OpenJobRepository;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.services.EmployerService;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employer;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Usernameandpassword;

@SpringBootTest(classes = RevWorkBootApplication.class)
public class EmployerServiceTest {
	
	@MockBean
	private OpenJobRepository ojr;
	
	@MockBean 
	private EmployerRepository er;
	
	@MockBean
	private Usernameandpassword login;
	
	@MockBean 
	private Employerregister empReg;
	
	@MockBean
	private EmployerData ed;
	
	@Autowired
	private EmployerService empServ;
	
	@Test
	public void getAllEmp() {
		
		List<EmployerData> list = new ArrayList<>();
		list.add(new EmployerData());
		 
		Mockito.when(er.findAll()).thenReturn(list);
		
		List<EmployerData> test = empServ.getAllEmployers();
		Assertions.assertNotNull(test);
	}
	
	@Test 
	public void findById() {
		
		Mockito.when(er.findById(1)).thenReturn(new EmployerData(1));
		
		EmployerData ed = empServ.findById(1);
		
		Assertions.assertNotNull(ed);
		Assertions.assertEquals(ed.getId(), new EmployerData(1).getId());		
	}
	
	@Test
	public void verifyLogin() {
		List<EmployerData> list = new ArrayList<>();
		list.add(new EmployerData(1, "name", "email", "username", "password"));
		EmployerData ed = new EmployerData(1,"name", "email", "username", "password");
		
		Mockito.when(login.getUsername()).thenReturn("username");
		Mockito.when(login.getPassword()).thenReturn("password");
		Mockito.when(er.findByUsername(login.getUsername())).thenReturn(list);
		
		EmployerData test = empServ.verifyLogin(login);
		Assertions.assertNotNull(test);
		Assertions.assertEquals(ed.toString(), test.toString());
	}
	
	@Test 
	public void doNotLogin() {
		List<EmployerData> list = new ArrayList<>();
		list.add(new EmployerData(1, "name", "email", "username", "password"));
		EmployerData ed = new EmployerData(1,"name", "email", "username", "password");
		
		Mockito.when(login.getUsername()).thenReturn("username");
		Mockito.when(login.getPassword()).thenReturn("pd");
		Mockito.when(er.findByUsername(login.getUsername())).thenReturn(list);
		
		EmployerData test = empServ.verifyLogin(login);
		Assertions.assertNull(test);
	}
	
	
	@Test 
	public void noUsernameLogin() {
		List<EmployerData> list = new ArrayList<>();
		
		Mockito.when(login.getUsername()).thenReturn("username");
		Mockito.when(login.getPassword()).thenReturn("pd");
		Mockito.when(er.findByUsername(login.getUsername())).thenReturn(list);
		
		EmployerData test = empServ.verifyLogin(login);
		Assertions.assertNull(test);
	}
	
	
	@Test
	public void verifyRegistration() {
		List<EmployerData> list = new ArrayList<>();

		Mockito.when(empReg.getUsername()).thenReturn("username");
		Mockito.when(er.findByName(empReg.getUsername())).thenReturn(list);

		boolean test = empServ.verifyRigistration(empReg);
		Assertions.assertNotNull(test);
		Assertions.assertTrue(test);

	}
	
	@Test
	public void doNotRegister() {
		List<EmployerData> list = new ArrayList<>();
		list.add(new EmployerData(1, "name", "email", "username", "password"));

		Mockito.when(empReg.getUsername()).thenReturn("username");
		Mockito.when(er.findByName(empReg.getUsername())).thenReturn(list);

		boolean test = empServ.verifyRigistration(empReg);
		Assertions.assertNotNull(test);
		Assertions.assertFalse(test);
	}
	
	@Test 
	public void addJob() {
		Availablejob aj = new Availablejob();
		aj.setId(1);
		Employer e = new Employer();
		e.setId(1);
		aj.setEmployerid(e);
		EmployerData empData = new EmployerData(1);
		OpenJobs job = new OpenJobs(1);
		 
		Mockito.when(ojr.save(any(OpenJobs.class))).thenReturn(job);
		Mockito.when(er.findById(Mockito.anyInt())).thenReturn(empData);
		
		Availablejob test = empServ.addJob(aj);
		Assertions.assertNotNull(test);
	}
	
	@Test 
	public void deleteJob() {
		OpenJobs job = new OpenJobs(1);
		
		OpenJobs test = empServ.deleteJob(job);
		
		Assertions.assertNotNull(test);
		Assertions.assertEquals(test.getId(), job.getId());
	}
	
	@Test 
	public void doNotDeleteJob() {
		OpenJobs job = null;
		
		OpenJobs test = empServ.deleteJob(job);
		
		Assertions.assertNull(test);
	}
	
	@Test
	public void construct() {
		EmployerService es = new EmployerService(er,ojr);
	}

}
