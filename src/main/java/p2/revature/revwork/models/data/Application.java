package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="application")
public class Application {

	
	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // specifies that the db generates this value
	private int id;
	@ManyToOne
	@JoinColumn(name = "jobid")
	private OpenJobs openJob;
	
	
}
