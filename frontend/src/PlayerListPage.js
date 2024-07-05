import React, { useEffect, useState } from "react";
import PlayerInfo from "./PlayerInfo";

function PlayerListPage({switchPage, setPlayer}) {
    const [players, setPlayers] = useState([]);

    useEffect(() => async () => {
        await fetch("/api/players")
            .then(response => response.json())
            .then(response => setPlayers(response))
            .catch(error => {});
    }, [])

    return (
        <div className="container main-content">
            <div className="row justify-content-center">
                <div className="col-md-60">
                    <div className="form-container mt-5">
                        <h2 className="text-center mb-4">Список игроков</h2>
                        <div className="container">
                            {players.map(player => (
                                <PlayerInfo key={player.id} value={player} switchPage={switchPage} setPlayer={setPlayer} />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PlayerListPage;