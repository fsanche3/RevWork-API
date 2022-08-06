package p2.revature.revwork.services.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import p2.revature.revwork.RevWorkBootApplication;
import p2.revature.revwork.data.ProfilesRepository;
import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.JobApplication;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Profile;
import p2.revature.revwork.services.ProfileService;

@SpringBootTest(classes = RevWorkBootApplication.class)
public class ProfileServiceTest {

	@MockBean
	ProfilesRepository pr;

	@Autowired
	ProfileService ps;

	@Test
	public void getById() {

		Mockito.when(pr.findById(1)).thenReturn(new Profile(1));

		Profile test = ps.findById(1);

		Assertions.assertNotNull(test);
		Assertions.assertEquals(test.getId(), new Profile(1).getId());
	}

	@Test
	public void doNotAddProfile() {

		Profile prof = new Profile(1);

		Mockito.when(pr.save(prof)).thenReturn(prof);
		
		
		Profile test = ps.addProfile(prof);

		Assertions.assertNull(test);

	}

	@Test
	public void deleteProfile() {

		Profile prof = new Profile(1);

		Profile test = ps.deleteProfile(prof);

		Assertions.assertNotNull(test);
		Assertions.assertEquals(test.getId(), prof.getId());
	}


	@Test
	public void editProfile() {

		Profile prof = new Profile(1);

		Mockito.when(pr.findById(Mockito.anyInt())).thenReturn(prof);
		Mockito.when(pr.save(prof)).thenReturn(prof);
		
		Profile test = ps.editProfile(prof);

		Assertions.assertNotNull(test);
		Assertions.assertEquals(test.getId(), prof.getId());	
		
	}

	@Test
	public void doNotEditProfile() {
		
		Profile prof = new Profile(1);

		Mockito.when(pr.findById(Mockito.anyInt())).thenReturn(null);
		
		Profile test = ps.editProfile(prof);

		Assertions.assertNull(test);

	}
}
