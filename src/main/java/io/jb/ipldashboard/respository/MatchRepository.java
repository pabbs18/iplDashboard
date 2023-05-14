package io.jb.ipldashboard.respository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.jb.ipldashboard.model.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
	
	@Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :dateStart and :dateEnd order by date desc")
	List<Match> getMatchesByTeamBetweenDates(
		@Param("teamName") String teamName, 
		@Param("dateStart") LocalDate dateStart, 
		@Param("dateEnd")  LocalDate dateEnd);

	List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
}
