package p2.revature.revwork.services;

import java.util.List;
import org.springframework.stereotype.Service;

import p2.revature.revwork.data.EmployerRepository;
import p2.revature.revwork.data.OpenJobRepository;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Profile;
import p2.revature.revworkboot.models.Availablejob;

@Service
public class OpenJobsService {

	private OpenJobRepository oj;
	private EmployerRepository er;

	public OpenJobsService(OpenJobRepository oj, EmployerRepository er) {
		this.oj = oj;
		this.er = er;
	}

	// this works
	public List<OpenJobs> getAllJobs() {
		return oj.findAll();
	}

	public OpenJobs findById(int integer) {
		// get id
		return oj.findById(integer);
	}

	public OpenJobs addJob(OpenJobs open) {
		open.setId(0);
		OpenJobs open2 = oj.save(open);
		if (open2.getId() != 0 ) {
			return open2;
		}
		return null;
	}

	public OpenJobs deleteJob(OpenJobs open) {
		if (open == null) {
			return null;
		} else {
			oj.deleteById(open.getId());
			return open;

		}
	}
	
	public OpenJobs editJob(OpenJobs open) {
		if (oj.existsById(open.getId())) {
			oj.save(open);
			return open;
		} else {
			return null;
		}
	}

}
