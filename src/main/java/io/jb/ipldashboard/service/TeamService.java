package io.jb.ipldashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.jb.ipldashboard.model.Match;
import io.jb.ipldashboard.model.Team;
import io.jb.ipldashboard.respository.MatchRepository;
import io.jb.ipldashboard.respository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;

	public Team getTeam(String teamName) {
		
		Team team = teamRepository.findByTeamName(teamName);
		Pageable pageable = PageRequest.of(0, 4);
		
		List<Match> matches = matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
		
		team.setMatches(matches);
		
		return team;
		
		
	}

    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
