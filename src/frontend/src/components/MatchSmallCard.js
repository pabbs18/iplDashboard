import { Link } from "react-router-dom";
import './MatchSmallCard.scss'

export const MatchSmallCard = ({matchProps, teamName}) => {
    const team1 = matchProps.team1;
  const team2 = matchProps.team2;
  const otherTeam = teamName === team1 ? team2 : team1;
  const otherTeamRoute = `/teams/${otherTeam}`
  const isMatchWon = teamName ===matchProps.winningTeam
    
    return (
        <div className={isMatchWon ? 'MatchSmallCard won-card' :'MatchSmallCard lost-card'}  >
            <span className='vs'>vs</span>
            <h3>   
               <Link to={otherTeamRoute}>{otherTeam} </Link> </h3>
            <p>on {matchProps.date}</p>
            <p className="match-result">{matchProps.winningTeam} won by {matchProps.resultMargin} {matchProps.wonBy}</p>
        </div>
    );
}

