package p2.revature.revwork.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.services.OpenJobsService;
import p2.revature.revworkboot.api.JobApi;
import p2.revature.revworkboot.models.Availablejob;

@RestController
@RequestMapping(path = "/job")
public class JobController implements JobApi {

	private OpenJobsService oj;

	public JobController(OpenJobsService oj) {
		this.oj = oj;
	}

	
	@Override
	@GetMapping
	public ResponseEntity<List<Availablejob>> jobGet() {
		List<OpenJobs> open = oj.getAllJobs();
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
	
	/*
	@GetMapping
	public ResponseEntity<List<Availablejob>> jobGetById(@Valid Availablejob body) {
		List<OpenJobs> open = oj.findById(body);
		List<Availablejob> aj = new ArrayList<>();
		for (OpenJobs o : open) {
			Availablejob a = new Availablejob();
			a.setName(o.getName());
			a.setDescription(o.getDescription());
			a.setSkills(o.getSkills());
			a.setPayrate(o.getPayrate());
			aj.add(a);
		}
		return ResponseEntity.ok(aj);
	}
	*/

	@Override
	@PostMapping
	public ResponseEntity<Void> jobPost(@Valid Availablejob body) {
		Availablejob success = oj.addJob(body);

		if (success != null) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
}

