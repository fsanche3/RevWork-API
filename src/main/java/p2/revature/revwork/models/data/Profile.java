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

@Entity
@Table(name = "profiles")
public class Profile {

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "freelancerid")
	private Freelancer freelancer;
	private String college;
	private String name;
	private String email;

	public Profile() {
		super();
	}

	public Profile(int id, Freelancer freelancer, String college, String name, String email) {
		super();
		this.id = id;
		this.freelancer = freelancer;
		this.college = college;
		this.name = name;
		this.email = email;
	}

	public Profile(Freelancer freelancer, String college, String name, String email) {
		super();
		this.freelancer = freelancer;
		this.college = college;
		this.name = name;
		this.email = email;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", freelancer=" + freelancer + ", college=" + college + ", name=" + name
				+ ", email=" + email + "]";
	}

}
