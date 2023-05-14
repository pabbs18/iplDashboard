import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import './MatchDetailCard.scss'

export const MatchDetailCard = (match, teaName) => {

    const team1 = match.match?.team1;
    const team2 = match.match?.team2;

    const otherTeam = match.teaName === team1 ? team2 : team1;
    const otherTeamRoute = `/teams/${otherTeam}`
    const isMatchWon = match.teaName === match.match?.winningTeam;


    console.log(match.teaName)



    return (
        <div className={isMatchWon ? 'MatchDetailCard won-card': 'MatchDetailCard lost-card'}>
            <div className='main-details'>


                <span className='vs'>vs</span>

                <h1>
                    <Link to={otherTeamRoute}> {otherTeam} </Link>  </h1>
                <h2 className='match- date'>{match.match?.date}</h2>
                <h3 className='match-venue'>at {match.match?.venue} </h3>
                <h3 className='match-result'> {match.match?.winningTeam} won by {match.match?.resultMargin} {match.match?.wonBy}</h3>
            </div>
            <div className='additional-details'>
                
                <h3>Toss Winner</h3>
                <p>{match.match?.tossWinner}</p>
                <h3>Toss Decision</h3>
                <p>{match.match?.tossDecision}</p>
                <h3 className='first-innings'>First Innings</h3>
                <p>{team1}</p>
                <h3 className='second-innings'>Second Innings</h3>
                 <p>{team2}</p>
                <h3>Man of the Match</h3>
                <p>{match.match?.playerOfMatch}</p>

                <h3>Umpires</h3>
                <p>{match.match?.umpire1}, {match.match?.umpire2}</p>
            </div>
        </div>
    );
}



