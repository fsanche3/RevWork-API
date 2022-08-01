package p2.revature.revwork.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Profile;
import p2.revature.revwork.services.FreelancerService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revwork.services.ProfileService;
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Portfolio;

@RestController
@RequestMapping(path = "/freelancer")
public class FreelancerController implements RegisterApi {

	private FreelancerService fs;
	private ProfileService p;
	private OpenJobsService ojs;

	// did = Create Profile + Delete profile + edit profile + getJobs + getJobsbyID + Register
	// Need to do = Login/Logout + Create Application + add ProfileSkill stuff with API
	// Create profile from the generated skills table

	public FreelancerController(FreelancerService fs ,ProfileService p, OpenJobsService ojs) {
		this.fs = fs;
		this.p = p;
		this.ojs = ojs;
	}
	
	@GetMapping(path="/get_jobs")
	public ResponseEntity<List<Availablejob>> jobGet() {
		List<OpenJobs> open = ojs.getAllJobs();
		List<Availablejob> aj = new ArrayList<>();
		for (OpenJobs o : open) {
			Availablejob a = new Availablejob();
			a.setId(o.getId());
			a.setEmployerid(o.getEmployer());
			a.setName(o.getName());
			a.setDescription(o.getDescription());
			a.setSkills(o.getSkills());
			a.setPayrate(o.getPayrate());
			aj.add(a);
		}
		return ResponseEntity.ok(aj);
	}

	
	@GetMapping(path = "/{id}")
	public ResponseEntity<List<Availablejob>> jobGetById(@PathVariable Integer id) {
		List<OpenJobs> open = ojs.findById(id);
		List<Availablejob> aj = new ArrayList<>();
		for (OpenJobs o : open) {
			Availablejob a = new Availablejob();
			a.setId(o.getId());
			a.setEmployerid(o.getEmployer());
			a.setName(o.getName());
			a.setDescription(o.getDescription());
			a.setSkills(o.getSkills());
			a.setPayrate(o.getPayrate());
			aj.add(a);
		}
		return ResponseEntity.ok(aj);
	}
	
	
	@Override
	public ResponseEntity<Void> registerEmployerPost(@Valid Employerregister body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping(path = "/register")
	public ResponseEntity<Void> registerFreelancerPost(@Valid Freelancerregister body) {
		boolean success = fs.verifyRigistration(body);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@PostMapping(path = "/create_profile")
	public ResponseEntity<Portfolio> addJob(@RequestBody Portfolio aj) {
		Profile open = new Profile(aj.getFreelancerid(), aj.getCollege(), aj.getName(), aj.getEmail());
		p.addProfile(open);
		return ResponseEntity.status(HttpStatus.CREATED).body(aj);
	}

	@DeleteMapping(path = "/delete_profile")
	public ResponseEntity<Portfolio> deleteProfile(@RequestBody Portfolio aj) {
		Profile open = new Profile(aj.getId(),aj.getFreelancerid(), aj.getCollege(), aj.getName(), aj.getEmail());
		if(p.deleteProfile(open) != null) {
		return ResponseEntity.status(HttpStatus.GONE).body(aj);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);

		}
	}
	
	@PutMapping(path = "/edit_profile")
	public ResponseEntity<Portfolio> editProfile(@RequestBody Portfolio aj){
		Profile open = new Profile(aj.getId(),aj.getFreelancerid(), aj.getCollege(), aj.getName(), aj.getEmail());
		if(p.editProfile(open) != null) {
		return ResponseEntity.status(HttpStatus.OK).body(aj);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);
		}
	}
}
