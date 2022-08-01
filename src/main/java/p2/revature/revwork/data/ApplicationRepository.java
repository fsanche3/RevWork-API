package p2.revature.revwork.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import p2.revature.revwork.models.data.Application;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	public List<Application> findByName(String name);
	
	public Application findById(int id);
}
