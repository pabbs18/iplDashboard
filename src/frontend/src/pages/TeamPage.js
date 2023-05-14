import { React, useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import { MatchDetailCard } from "../components/MatchDetailCard";
import { MatchSmallCard } from "../components/MatchSmallCard";
import './TeamPage.scss'
import { PieChart } from 'react-minimal-pie-chart';

export const TeamPage = () => {

  const [team, setTeam] = useState({ matches: [] });
  //get teamName param from all the params presnt in useParams
  const { teamName } = useParams();

  useEffect(
    () => {
      const fecthMatches = async () => {
        const response = await fetch(`${process.env.REACT_APP_API_ROOT}/team/${teamName}`)

        const data = await response.json();
        console.log(data)
        setTeam(data);
        console.log(team.teamName)
        console.log("teamName " + teamName)


      };

      fecthMatches();



    },
    [teamName]
  );


  return (
    <div className="TeamPage">
      <div className='team-name-section'>
        <h1 className='team-name'>{team.teamName}</h1>
        <h3>Latest Matches</h3>
      </div>
      <div className='win-loss-section'>
        Wins /Loses
        <PieChart
          data={[
            { title: 'Loses', value: team.totalMatches-team.totalWins, color: '#a34d5d' },
            { title: 'Wins', value: team.totalWins, color: '#4da375' }
            
           
          ]}
        />
      </div>

      <div className='match-detail-card-section'>
        <MatchDetailCard match={team.matches[0]} teaName={team.teamName} />
      </div>

      {team.matches?.slice(1).map(match => <MatchSmallCard key={match.id} matchProps={match} teamName={teamName} />)}
      <div className='more-link'>
        <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More>></Link>
        
      </div>
    </div>
  );
}

