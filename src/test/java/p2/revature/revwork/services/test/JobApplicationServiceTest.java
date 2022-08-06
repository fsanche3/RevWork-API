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
import p2.revature.revwork.data.JobApplicationRepository;
import p2.revature.revwork.models.data.JobApplication;
import p2.revature.revwork.services.JobApplicationService;

@SpringBootTest(classes = RevWorkBootApplication.class)
public class JobApplicationServiceTest {
	
	@MockBean
	JobApplicationRepository jar;
	
	@Autowired 
	JobApplicationService appServ;
	
	
	@Test
	public void doNotAddApplication() {
		JobApplication ja = new JobApplication(1);
		
		Mockito.when(jar.save(ja)).thenReturn(ja);
		JobApplication test = appServ.addApplication(ja);
		
		Assertions.assertNull(test);		
	}
	
	@Test
	public void selectApplicants() {
		List<JobApplication> list = new ArrayList<>();
		list.add(new JobApplication(1));
		
		Mockito.when(jar.findByName(Mockito.any())).thenReturn(list);
		
		List<JobApplication> test = appServ.selectApplicants("");
		
		Assertions.assertNotNull(test.get(0));		
	}
	
	@Test
	public void doNotSelectApplicants() {
		List<JobApplication> list = new ArrayList<>();
		list.add(null);
		
		Mockito.when(jar.findByName(Mockito.any())).thenReturn(list);
		
		List<JobApplication> test = appServ.selectApplicants("");
		
		Assertions.assertNull(test);		
	}
	
	@Test 
	public void getApplication() {
		JobApplication ja = new JobApplication(1);
		
		Mockito.when(jar.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.when(jar.findById(Mockito.anyInt())).thenReturn(ja);
		
		JobApplication test = appServ.selectApplicant(ja.getId());
		Assertions.assertNotNull(test);	
	}
	
	@Test 
	public void doNotGetApplication() {
		JobApplication ja = new JobApplication();
		
		Mockito.when(jar.existsById(Mockito.anyInt())).thenReturn(false);
		
		JobApplication test = appServ.addApplication(ja);
		Assertions.assertNull(test);	
	}
	

}
