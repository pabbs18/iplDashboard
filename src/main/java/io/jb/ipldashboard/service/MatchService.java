package io.jb.ipldashboard.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jb.ipldashboard.model.Match;
import io.jb.ipldashboard.respository.MatchRepository;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    public List<Match> getMatchesForTeamBetweenDates(String teamName, int year){
        LocalDate starDate =  LocalDate.of(year, 1, 1);
        LocalDate endDate =  LocalDate.of(year, 12, 31);

         return matchRepository.getMatchesByTeamBetweenDates(teamName, starDate, endDate);

    } 
}
