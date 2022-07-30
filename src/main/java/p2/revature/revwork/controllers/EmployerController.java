package p2.revature.revwork.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2.revature.revwork.services.EmployerService;
import p2.revature.revworkboot.api.RegisterApi;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Freelancerregister;

@RestController
@RequestMapping(path="/register")
public class EmployerController implements RegisterApi {

	private EmployerService empServ;

	public EmployerController(EmployerService empServ) {
		this.empServ = empServ;
	}

	@Override
	@PostMapping(path = "/employer")
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

	@Override
	public ResponseEntity<Void> registerFreelancerPost(@Valid Freelancerregister body) {
		// TODO Auto-generated method stub
		return null;
	}

}
