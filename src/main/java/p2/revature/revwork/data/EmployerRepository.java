package p2.revature.revwork.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.EmployerData;
import p2.revature.revworkboot.models.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<EmployerData, Integer> {
	
	public List<EmployerData> findByName(String username);
	


}
