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
import p2.revature.revwork.services.EmployerService;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Portfolio;

@RestController
@RequestMapping(path = "/employer")
public class EmployerController implements RegisterApi {

	private EmployerService empServ;
	private OpenJobsService ojs;
	
	//Did = GetJobs + FindJobbyID+ AddJob + Delete Job + edit Job + Register
	// Need to do = Login/Logout + Select Application + ID Validation with JWT for editing and deleting jobs


	public EmployerController(EmployerService empServ ,OpenJobsService ojs) {
		this.ojs = ojs;
		this.empServ = empServ;
	}
	
	@GetMapping(path= "/get_jobs")
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
	@PostMapping(path = "/register")
	public ResponseEntity<Void> registerEmployerPost(@Valid Employerregister body) {
		// TODO Auto-generated method stub

		// System.out.println("hello from employee register");

		boolean success = empServ.verifyRigistration(body);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@PostMapping(path = "/add_job")
	public ResponseEntity<Availablejob> addJob(@RequestBody Availablejob aj) {
	OpenJobs open = new OpenJobs(aj.getEmployerid(),aj.getName(), aj.getDescription(), aj.getSkills(),aj.getPayrate());
		ojs.addJob(open);
		return ResponseEntity.status(HttpStatus.CREATED).body(aj);
	}
	
	@DeleteMapping(path = "/delete_job")
	public ResponseEntity<Availablejob> deleteJob(@RequestBody Availablejob openJob){
		OpenJobs open = new OpenJobs(openJob.getId(),openJob.getEmployerid(),openJob.getName(), openJob.getDescription(), openJob.getSkills(), openJob.getPayrate());
		ojs.deleteJob(open);
		return ResponseEntity.status(HttpStatus.GONE).body(openJob);		
	}
	
	@PutMapping(path = "/edit_job")
	public ResponseEntity<Availablejob> editJob(@RequestBody Availablejob aj){
		OpenJobs open = new OpenJobs(aj.getId(),aj.getEmployerid(), aj.getName(), aj.getDescription(), aj.getSkills(), aj.getPayrate());
		if(ojs.editJob(open) != null) {
		return ResponseEntity.status(HttpStatus.OK).body(aj);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);
		}
	}

	@Override
	public ResponseEntity<Void> registerFreelancerPost(@Valid Freelancerregister body) {
		// TODO Auto-generated method stub
		return null;
	}

}

