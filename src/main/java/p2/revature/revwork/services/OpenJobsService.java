package p2.revature.revwork.services;


import java.util.List;
import org.springframework.stereotype.Service;

import p2.revature.revwork.data.OpenJobRepository;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revworkboot.models.Availablejob;

@Service
public class OpenJobsService {

	
	private OpenJobRepository oj;

	public OpenJobsService(OpenJobRepository oj) {
		this.oj = oj;
	}
	
	// This works expect for employerid stays null in the db
	public Availablejob addJob(Availablejob openJob) {
		// the employer ID isnt auto generating (producing null in the db) and
		// I honestly dont know if its a problem
		// Get employerID should actually be getEmployer( ) in the AvaliableJob API i think
		// Ill try this later 
		OpenJobs open = new OpenJobs(openJob.getEmployerid(),openJob.getName(), openJob.getDescription(), openJob.getSkills(), openJob.getPayrate());
		oj.save(open);
		return openJob;
	}
	
	
	// this works
	public List<OpenJobs> getAllJobs() {
		return oj.findAll();
	}
	
	/*
	public List<OpenJobs> findById(Availablejob aj) {
		// get id
		return oj.findById(aj);
	}
*/
}

