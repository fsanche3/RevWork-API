package p2.revature.revwork.services;

import java.util.List;

import org.springframework.stereotype.Service;

import p2.revature.revwork.data.FreelancerRepository;
import p2.revature.revwork.models.data.Employer;
import p2.revature.revwork.models.data.Freelancer;
import p2.revature.revworkboot.models.Freelancerregister;
import p2.revature.revworkboot.models.Usernameandpassword;

@Service
public class FreelancerService {
	
	
	private FreelancerRepository fr;
	
	public FreelancerService(FreelancerRepository fr) {
		this.fr = fr;
	}
	
	public List<Freelancer> getAllFreelancers(){
		return fr.findAll();
	}
	
	public boolean verifyLogin(Usernameandpassword login) {
		return true;
	}
	
	public boolean verifyRigistration(Freelancerregister register) {
		List<Freelancer> free = fr.findByName(register.getUsername());
		if (free.size() == 0) {
			Freelancer f = new Freelancer();
			f.setName(register.getName());
			f.setAbout(register.getAbout());
			f.setExperiencelevel(register.getExperiencelevel());
			f.setEmail(register.getEmail());
			f.setUsername(register.getUsername());
			f.setPassword(register.getPassword());
			fr.save(f);
			return true;
		} else {

			return false;
		}

	}
	
	

}
