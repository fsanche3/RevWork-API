package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import p2.revature.revwork.models.data.Employer;

@Entity
@Table(name="openjobs")
public class OpenJobs {

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "employerid")
	private Employer employer;
	private String name;
	private String description;
	private String skills;
	private String payrate;

	public OpenJobs() {}
	
	public OpenJobs(Employer employer, String name, String description, String skills, String payrate) {
		super();
		this.employer = employer;
		this.name = name;
		this.description = description;
		this.skills = skills;
		this.payrate = payrate;
	}

	public OpenJobs(int id, Employer employer, String name, String description, String skills, String payrate) {
		super();
		this.id = id;
		this.employer = employer;
		this.name = name;
		this.description = description;
		this.skills = skills;
		this.payrate = payrate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "OpenJobs [id=" + id + ", employer=" + employer + ", name=" + name + ", description=" + description
				+ ", skills=" + skills + ", payrate=" + payrate + "]";
	}
	public String getPayrate() {
		return payrate;
	}
	public void setPayrate(String payrate) {
		this.payrate = payrate;
	}

	

}
