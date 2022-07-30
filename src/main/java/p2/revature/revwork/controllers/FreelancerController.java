package p2.revature.revwork.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revwork.services.FreelancerService;
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;

@RestController
@RequestMapping(path="/register")
public class FreelancerController implements RegisterApi {
	
	private FreelancerService fs;

	public FreelancerController(FreelancerService fs) {
		this.fs = fs;
	}
	
	@Override
	public ResponseEntity<Void> registerEmployerPost(@Valid Employerregister body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping(path = "/freelancer")
	public ResponseEntity<Void> registerFreelancerPost(@Valid Freelancerregister body) {
		boolean success = fs.verifyRigistration(body);

		if (success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	

}
