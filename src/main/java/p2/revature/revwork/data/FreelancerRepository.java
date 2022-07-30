package p2.revature.revwork.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.Employer;
import p2.revature.revwork.models.data.Freelancer;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {
	public List<Freelancer> findByName(String username);

}
