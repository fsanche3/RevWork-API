package p2.revature.revwork.controllers;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Application;
import p2.revature.revworkboot.models.Availablejob;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Portfolio;

@RestController
@RequestMapping(path = "/freelancer")
public class FreelancerController implements RegisterApi {

	private FreelancerService fs;
	private ProfileService p;
	private JwtUtil jwt;
	private OpenJobsService ojs;
	private JobApplicationService aps;

	public FreelancerController(FreelancerService fs, ProfileService p, OpenJobsService ojs,
			JobApplicationService aps, JwtUtil jwt) {
		this.fs = fs;
		this.p = p;
		this.ojs = ojs;
		this.aps = aps;
		this.jwt = jwt;
	}

	@GetMapping(path = "/get_jobs")
	public ResponseEntity<List<Availablejob>> jobGet() {
//		List<OpenJobs> open = ojs.getAllJobs();
//		return ResponseEntity.ok(open);
		
		List<OpenJobs> open = ojs.getAllJobs();
		List<Availablejob> aj = new ArrayList<>();
		for (OpenJobs o : open) {
			Availablejob a = new Availablejob();
			a.setId(o.getId());
			a.setEmployerid(EmployerData.toEmployer(o.getEmployer()));
			a.setName(o.getName());
			a.setDescription(o.getDescription());
			a.setSkills(o.getSkills());
			a.setPayrate(o.getPayrate());			
			aj.add(a);
		}
		return ResponseEntity.ok(aj);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<OpenJobs> jobGetById(@PathVariable Integer id) {
		OpenJobs open = ojs.findById(id);
		return ResponseEntity.ok(open);
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

	@GetMapping(path = "/{id}/profiles")
	public ResponseEntity<List<Portfolio>> getProfiles(@PathVariable Integer id, @RequestHeader(value = "Authorization", required = true) String authorization) throws UnsupportedEncodingException {
		String[] arrOfStr = authorization.split(" ", 2);
		
		int tokenId = jwt.getId(arrOfStr[1]);
		
		if ( id == tokenId ) {
			
			List<Profile> plist = fs.getProilesById(id);
			List<Portfolio> newList = new LinkedList<>();
			
	        for (Profile prof : plist) {
	            Portfolio port = new Portfolio();
	            
	            port.setCollege(prof.getCollege());
	            port.setEmail(prof.getEmail());
	            port.setId(prof.getId());
	            port.setName(prof.getName());
	            
	            newList.add(port);
	        }
			
			return ResponseEntity.status(HttpStatus.OK).body(newList);
		}
		else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	}
	
	
	@PostMapping(path = "/create_profile")
	public ResponseEntity<Portfolio> addJob(@RequestBody Portfolio aj,
			@RequestHeader(value = "Authorization", required = true) String authorization) throws UnsupportedEncodingException {

		
		String[] arrOfStr = authorization.split(" ", 2);

		int id = jwt.getId(arrOfStr[1]);
		

		if (id == aj.getFreelancerid().getId()) {

			Profile open = new Profile(-1, FreelancerData.fromFreelancer(aj.getFreelancerid()), aj.getCollege(),
					aj.getName(), aj.getEmail());
			p.addProfile(open);

			return ResponseEntity.status(HttpStatus.CREATED).body(aj);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@DeleteMapping(path = "/delete_profile")
	public ResponseEntity<Void> deleteProfile(@RequestBody Portfolio aj,
			@RequestHeader(value = "Authorization", required = true) String authorization) throws UnsupportedEncodingException {
		
		
		//int id = jwt.getId(authorization);
		String[] arrOfStr = authorization.split(" ", 2);
		int id = jwt.getId(arrOfStr[1]);

		if (id == aj.getFreelancerid().getId()) {

			Profile open = new Profile(aj.getId(), fs.findById(FreelancerData.fromFreelancer(aj.getFreelancerid()).getId()), aj.getCollege(),
					aj.getName(), aj.getEmail());
			boolean pass = p.deleteProfile(open);
			if (pass == true && pass != false) {
				return ResponseEntity.status(HttpStatus.GONE).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@PutMapping(path = "/edit_profile")
	public ResponseEntity<Portfolio> editProfile(@RequestBody Portfolio aj,
			@RequestHeader(value = "Authorization", required = true) String authorization) throws UnsupportedEncodingException {
		//int id = jwt.getId(authorization);
		String[] arrOfStr = authorization.split(" ", 2);
		int id = jwt.getId(arrOfStr[1]);

		if (id == aj.getFreelancerid().getId()) {
			Profile open = new Profile(aj.getId(), FreelancerData.fromFreelancer(aj.getFreelancerid()), aj.getCollege(),
					aj.getName(), aj.getEmail());
			if (p.editProfile(open) != null) {
				return ResponseEntity.status(HttpStatus.OK).body(aj);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aj);
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@PostMapping(path = "/submit_app")
	public ResponseEntity<Void> submitApplication(@RequestBody Application app, 
				@RequestHeader(value = "Authorization", required = true) String authorization) throws UnsupportedEncodingException {
		
		String[] arrOfStr = authorization.split(" ", 2);
		int id = jwt.getId(arrOfStr[1]);
		
		int profId = app.getPortfolioid().getId();
		
		
		Profile prof = p.findById(profId);
		
		
		int jobId = app.getJobid().getId();
		
		OpenJobs job = new OpenJobs();
		job.setId(jobId);
		
		JobApplication a = new JobApplication(-1, job, prof, app.getCoverletter(), app.getName());
		
		if ( prof.getFreelancer().getId() != id ) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		if (aps.addApplication(a) != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}
}