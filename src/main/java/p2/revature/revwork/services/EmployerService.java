package p2.revature.revwork.services;

import java.util.List;

import org.springframework.stereotype.Service;

import p2.revature.revwork.data.EmployerRepository;
import p2.revature.revwork.models.data.Employer;
import p2.revature.revworkboot.models.Employerregister;
import p2.revature.revworkboot.models.Usernameandpassword;

@Service
public class EmployerService {

	private EmployerRepository er;

	public EmployerService(EmployerRepository er) {
		this.er = er;
	}

	public List<Employer> getAllEmployers() {
		return er.findAll();
	}

	boolean verifyLogin(Usernameandpassword login) {
		return true;
	}

	public boolean verifyRigistration(Employerregister register) {
		List<Employer> employers = er.findByName(register.getUsername());

		if (employers.size() == 0) {
			Employer emp = new Employer();
			emp.setName(register.getName());
			emp.setEmail(register.getEmail());
			emp.setUsername(register.getUsername());
			emp.setPassword(register.getPassword());
			er.save(emp);
			return true;
		} else {

			return false;
		}

	}

}
