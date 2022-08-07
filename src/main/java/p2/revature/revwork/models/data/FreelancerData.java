package p2.revature.revwork.models.data;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import p2.revature.revworkboot.models.Freelancer;

@Entity
@Table(name="freelancer")
public class FreelancerData {
	
	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	private String name;
	private String about;
	private String experiencelevel;
	private String email;
	private String username;
	private String password;
	
	@OneToMany
	@JoinColumn(name="freelancerid")
	private List<Profile> profiles;

	
	public FreelancerData() {}
	public FreelancerData(int id) {super(); this.id = id;}
	public FreelancerData(int id, String name, String about, String experiencelevel, String email, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.about = about;
		this.experiencelevel = experiencelevel;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public static Freelancer toFreelancer(FreelancerData data) {
		Freelancer free = new Freelancer();
		
		free.setAbout(data.getAbout());
		free.setEmail(data.getEmail());
		free.setExperiencelevel(data.getExperiencelevel());
		free.setId(data.getId());
		free.setName(data.getName());
		free.setPassword(data.getPassword());
		free.setUsername(data.getUsername());
		
		return free;
	}
	
	public static FreelancerData fromFreelancer(Freelancer free) {
		FreelancerData data = new FreelancerData();
		
		data.setAbout(free.getAbout());
		data.setEmail(free.getEmail());
		data.setExperiencelevel(free.getExperiencelevel());
		data.setId(free.getId());
		data.setName(free.getName());
		data.setPassword(free.getPassword());
		data.setUsername(free.getUsername());
		
		return data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getExperiencelevel() {
		return experiencelevel;
	}

	public void setExperiencelevel(String experiencelevel) {
		this.experiencelevel = experiencelevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Profile> getProfiles() {
		return profiles;
	}
	
	@Override
	public String toString() {
		return "FreelancerData [id=" + id + ", name=" + name + ", about=" + about + ", experiencelevel="
				+ experiencelevel + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}

	
}
