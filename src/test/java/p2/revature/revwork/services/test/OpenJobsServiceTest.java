package p2.revature.revwork.services.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import p2.revature.revwork.RevWorkBootApplication;
import p2.revature.revwork.data.OpenJobRepository;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.JobApplication;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.services.OpenJobsService;

@SpringBootTest(classes = RevWorkBootApplication.class)
public class OpenJobsServiceTest {

	@MockBean
	private OpenJobRepository ojr;

	@Autowired
	private OpenJobsService ojs;

	@Test
	public void getAllJobs() {
		List<OpenJobs> list = new ArrayList<>();
		list.add(new OpenJobs());

		Mockito.when(ojr.findAll()).thenReturn(list);

		List<OpenJobs> test = ojs.getAllJobs();
		Assertions.assertNotNull(test);
	}

	@Test
	public void getJobById() {

		Mockito.when(ojr.findById(1)).thenReturn(new OpenJobs(1));

		OpenJobs test = ojs.findById(1);

		Assertions.assertNotNull(test);
		Assertions.assertEquals(test.getId(), new OpenJobs(1).getId());
	}

	@Test
	public void doNotAddJob() {
		OpenJobs ja = new OpenJobs(1);

		Mockito.when(ojr.save(ja)).thenReturn(ja);
		OpenJobs test = ojs.addJob(ja);

		Assertions.assertNull(test);
	}

	@Test
	public void deleteJob() {

		OpenJobs job = new OpenJobs(1);

		OpenJobs test = ojs.deleteJob(job);

		Assertions.assertNotNull(test);
		Assertions.assertEquals(test.getId(), job.getId());
	}

	@Test
	public void doNotDeleteJob() {

		OpenJobs job = null;

		OpenJobs test = ojs.deleteJob(job);

		Assertions.assertNull(test);
	}

	@Test
	public void editJob() {
	OpenJobs job = new OpenJobs(1);

	Mockito.when(ojr.existsById(Mockito.anyInt())).thenReturn(true);
	Mockito.when(ojr.save(job)).thenReturn(job);
	
	OpenJobs test = ojs.editJob(job);

	Assertions.assertNotNull(test);
	Assertions.assertEquals(test.getId(), job.getId());	
	}

	@Test
	public void doNotEditJob() {
		
		OpenJobs job = new OpenJobs(1);

		Mockito.when(ojr.existsById(job.getId())).thenReturn(false);
		
		OpenJobs test = ojs.editJob(job);

		Assertions.assertNull(test);
	}

}
