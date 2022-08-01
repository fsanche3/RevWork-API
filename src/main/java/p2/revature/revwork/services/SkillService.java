package p2.revature.revwork.services;

import org.springframework.stereotype.Service;

import java.util.*;
import p2.revature.revwork.data.SkillRepository;
import p2.revature.revwork.models.data.OpenJobs;
import p2.revature.revwork.models.data.Skill;

@Service
public class SkillService {
	
	private SkillRepository sr;
	
	public SkillService(SkillRepository sr) {
		this.sr = sr;
	}
	
	
	public List<Skill> getAllSkills(){
		return sr.findAll();
	}
	
	public List<Skill> findByName(String name){
		return sr.findByName(name);
	}

	public Skill addSkill(Skill skill) {
		skill.setId(0);
		skill.setDescription("User Added skill");
		sr.save(skill);
		if (skill.getId() != 0) {
			return skill;
		}
		return null;
	}
}
