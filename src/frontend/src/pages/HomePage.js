import { useEffect, useState } from "react"
import { TeamTile } from "./TeamTile";
import './HomePage.scss'

export const HomePage = () => {

    const [teams, setTeams] = useState([]);

    useEffect(
        () => {
            const fecthTeams = async () => {
                const respone = await fetch(`${process.env.REACT_APP_API_ROOT}/team`)
                const data = await respone.json();
                setTeams(data);
            };

            fecthTeams();
        }, []
    );

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">IPL Dashboard</h1>
            </div>
            <div className="team-grid">
                {teams.map(team => <TeamTile key = {team.id} teamName={team.teamName} />)}
            </div>
        </div>
    )
}