package io.jb.ipldashboard.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.jb.ipldashboard.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
 
	Team findByTeamName(String teamName);
}
