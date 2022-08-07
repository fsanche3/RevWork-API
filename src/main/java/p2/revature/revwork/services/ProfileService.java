package p2.revature.revwork.services;

import java.util.List;

import org.springframework.stereotype.Service;

import p2.revature.revwork.data.ProfilesRepository;
import p2.revature.revwork.models.data.Profile;

@Service
public class ProfileService {

	private ProfilesRepository pr;

	public ProfileService(ProfilesRepository pr) {
		this.pr = pr;
	}

	public Profile addProfile(Profile profile) {
		profile.setId(0);
		pr.save(profile);
		if (profile.getId() != 0) {
			profile.setId(profile.getId());
			return profile;
		}
		return null;
	}

	public boolean deleteProfile(Profile profile) {
		pr.deleteById(profile.getId());
		return true;
	}

	public Profile editProfile(Profile profile) {
		if (pr.findById(profile.getId()) != null) {
			pr.save(profile);
			return profile;
		} else {
			return null;
		}
	}
	
	public Profile findById(int id) {
		return pr.findById(id);
	}
}
