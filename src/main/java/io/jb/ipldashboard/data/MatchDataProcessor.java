package io.jb.ipldashboard.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import io.jb.ipldashboard.model.Match;



	public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
		  Match match = new Match();
		  
		  match.setId(Long.parseLong(matchInput.getId()));
		  match.setCity(matchInput.getCity());
		  match.setDate(LocalDate.parse(matchInput.getDate(), DateTimeFormatter.ISO_DATE));
		  match.setPlayerOfMatch(matchInput.getPlayerOfMatch());
		  match.setVenue(matchInput.getVenue());
		  
		  String firstInningsTeam = matchInput.getTeam1();
		  String secondInningsTeam = matchInput.getTeam2();
		  
		  match.setTeam1(firstInningsTeam);
		  match.setTeam2(secondInningsTeam);
		  
		  match.setTossWinner(matchInput.getTossWinner());
		  match.setTossDecision(matchInput.getTossDecision());
		  match.setWinningTeam(matchInput.getWinningTeam());
		  match.setResultMargin(matchInput.getMargin());
		  match.setMethod(matchInput.getMethod());
		  match.setUmpire1(matchInput.getUmpire1());
		  match.setUmpire2(matchInput.getUmpire2());
		  match.setWonBy(matchInput.getWonBy());
		  
		  
		  
		  return match;
	  }

	}

