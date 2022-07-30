package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="profiles")
public class Profile {

	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "freelancerid")
	private Freelancer freelancer;

	public Profile(int id, Freelancer freelancer) {
		super();
		this.id = id;
		this.freelancer = freelancer;
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

	@Override
	public String toString() {
		return "Profile [id=" + id + ", freelancer=" + freelancer + "]";
	}

}
