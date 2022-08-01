package p2.revature.revwork.services;

import org.springframework.stereotype.Service;

import java.util.*;
import p2.revature.revwork.data.ApplicationRepository;
import p2.revature.revwork.models.data.Application;


@Service
public class ApplicationService {
	
	private ApplicationRepository ar;
	
	public ApplicationService(ApplicationRepository ar) {
		this.ar = ar;
	}
	
	public Application addApplication(Application app) {
		app.setId(0);
		ar.save(app);
		if (app.getId() != 0) {
			return app;
		}
		return null;
	}
	
	public List<Application> selectApplicants(String name) {
		List<Application> apps = ar.findByName(name);
		if(apps.get(0) == null) {
			return null;
		}
		else {
			return apps;
		}
	}
	
	public Application selectApplicant(int id) {
		boolean pass = ar.existsById(id);
		if(pass != false) {
			return ar.findById(id);
		} else {
		return null;
		}
	}

}

