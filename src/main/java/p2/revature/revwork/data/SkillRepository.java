package p2.revature.revwork.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
