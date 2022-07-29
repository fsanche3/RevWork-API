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

import p2.revature.revworkboot.api.JobApi;
import p2.revature.revworkboot.models.Availablejob;

@RestController
@RequestMapping(path="/job")
public class JobController implements JobApi{

	@Override
	@GetMapping()
	public ResponseEntity<List<Availablejob>> jobGet() {
		List<Availablejob> books = new ArrayList<>();
		
		Availablejob book1 = new Availablejob();
		book1.setTitle("Job Title1");
		book1.setSkills("skill 1, skill 2");
		book1.setJobdescription("MT");
		book1.setPayrate("25.12");
		
		books.add(book1);
		
		Availablejob book3 = new Availablejob();
		book3.setTitle("Job Title2");
		book3.setSkills("skill3");
		book3.setJobdescription("RE");
		book3.setPayrate("13.18");
		
		books.add(book3);
		
		return new ResponseEntity<>(books,HttpStatus.OK);
	}

	@Override
	@PostMapping
	public ResponseEntity<Void> jobPost(@Valid Availablejob body) {
		// TODO Auto-generated method stub
		
		System.out.println("Post made on job enppint");
		return null;
	}
	
}