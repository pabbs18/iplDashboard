import { useEffect, useState } from "react"
import { MatchDetailCard } from "../components/MatchDetailCard";
import { useParams } from "react-router-dom";
import './MatchPage.scss'
import { YearSelector } from "./YearSelector";
import './YearSelector.scss'

export const MatchPage = () => {

    const [matches, setMatches] = useState([])
    const { teamName, year } = useParams()

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`${process.env.REACT_APP_API_ROOT}/team/${teamName}/matches?year=${year}`)
                const data = await response.json();
                console.log(data)
                setMatches(data);
            };
            fetchMatches();
        }, [teamName, year]
    );
    return (
        <div className="MatchPage">
            <div className="YearSelector">
                <h3>Select year</h3>
                <YearSelector teamName = {teamName}/>
            </div>
            
            
            <div className="Matches">

            <h1>{teamName} Matches in {year}</h1>
                {
                    matches.map(match =>
                        <MatchDetailCard key={match.id} match={match} teaName={teamName} />

                    )
                }

            </div>
        </div>
    )
}
