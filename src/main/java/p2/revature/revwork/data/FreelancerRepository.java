package p2.revature.revwork.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revwork.models.data.FreelancerData;

@Repository
public interface FreelancerRepository extends JpaRepository<FreelancerData, Integer> {
	public List<FreelancerData> findByName(String username);
	
	public FreelancerData findById(int id);

	
	public List<FreelancerData> findByUsername(String username);

}
