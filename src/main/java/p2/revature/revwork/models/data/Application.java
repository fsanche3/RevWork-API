package p2.revature.revwork.models.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Application {
	
	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // specifies that the db generates this value
	private int id;
	@ManyToMany
	@JoinTable(name="application",
		joinColumns = @JoinColumn(name="id"),
		inverseJoinColumns = @JoinColumn(name="id"))
	private OpenJobs jobid;
	@ManyToMany
	@JoinTable(name="application",
		joinColumns = @JoinColumn(name="id"),
		inverseJoinColumns = @JoinColumn(name="id"))
	private Profile profile;

}
