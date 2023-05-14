package io.jb.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jb.ipldashboard.model.Match;
import io.jb.ipldashboard.model.Team;
import io.jb.ipldashboard.service.MatchService;
import io.jb.ipldashboard.service.TeamService;

@RestController
@CrossOrigin
public class TeamController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private MatchService matchService;

	@GetMapping("team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		return teamService.getTeam(teamName);
	}

	@GetMapping("/team/{teamName}/matches")
	public List<Match> GetMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
		return matchService.getMatchesForTeamBetweenDates(teamName, year); 
	}

	@GetMapping("/team")
	public Iterable<Team> getAllTeam() {
		return teamService.getAllTeams(); 
	}

}
