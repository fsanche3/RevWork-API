package p2.revature.revwork.data;

import org.springframework.data.jpa.repository.JpaRepository;

import p2.revature.revwork.models.data.Application;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
