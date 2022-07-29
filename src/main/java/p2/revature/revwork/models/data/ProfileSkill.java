package p2.revature.revwork.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ProfileSkill {
	
	@Id // specifies that this field is the primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // specifies that the db generates this value
	private int id;
	@ManyToMany
	@JoinTable(name="profileskill",
		joinColumns = @JoinColumn(name="id"),
		inverseJoinColumns = @JoinColumn(name="id"))
	private Profile profile;
	@ManyToMany
	@JoinTable(name="profileskill",
		joinColumns = @JoinColumn(name="id"),
		inverseJoinColumns = @JoinColumn(name="id"))
	private Skill skill;


}
