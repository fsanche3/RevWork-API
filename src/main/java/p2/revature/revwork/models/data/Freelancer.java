package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Freelancer {
	
	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // specifies that the db generates this value
	private int id;
	private String name;
	private String about;
	private String experiencelevel;
	private String email;
	
	public Freelancer(int id, String name, String about, String experiencelevel, String email) {
		super();
		this.id = id;
		this.name = name;
		this.about = about;
		this.experiencelevel = experiencelevel;
		this.email = email;
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

	@Override
	public String toString() {
		return "Freelancer [id=" + id + ", name=" + name + ", about=" + about + ", experiencelevel=" + experiencelevel
				+ ", email=" + email + "]";
	}
	
}
