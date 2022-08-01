package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import p2.revature.revworkboot.models.Employer;

@Entity
@Table(name="openjobs")
public class OpenJobs {

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "employerid")
	private EmployerData employer;
	private String name;
	private String description;
	private String skills;
	private String payrate;
	private boolean istaken;

	public OpenJobs() {}
	
	public OpenJobs(EmployerData employer, String name, String description, String skills, String payrate, boolean istaken) {
		super();
		this.employer = employer;
		this.name = name;
		this.description = description;
		this.skills = skills;
		this.payrate = payrate;
		this.istaken = istaken;
	}

	public OpenJobs(int id, EmployerData employer, String name, String description, String skills, String payrate, boolean istaken) {
		super();
		this.id = id;
		this.employer = employer;
		this.name = name;
		this.description = description;
		this.skills = skills;
		this.payrate = payrate;
		this.istaken = istaken;
	}

	
	public boolean isIstaken() {
		return istaken;
	}

	public void setIstaken(boolean istaken) {
		this.istaken = istaken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployerData getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerData employer) {
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
	
	public String getPayrate() {
		return payrate;
	}
	public void setPayrate(String payrate) {
		this.payrate = payrate;
	}

	@Override
	public String toString() {
		return "OpenJobs [id=" + id + ", employer=" + employer + ", name=" + name + ", description=" + description
				+ ", skills=" + skills + ", payrate=" + payrate + ", istaken=" + istaken + "]";
	}

}
