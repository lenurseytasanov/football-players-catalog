import React, { useEffect, useState } from "react";
import PlayerInfo from "./PlayerInfo";
import { Client } from '@stomp/stompjs';

function PlayerListPage({switchPage, setPlayer}) {
    const [players, setPlayers] = useState([]);

    useEffect(() => {
        const client = new Client();

        client.configure({
            brokerURL: 'ws://localhost:8080/ws',
            heartbeatIncoming: 0,
            heartbeatOutgoing: 0,
            onConnect: () => {
                console.log('onConnect');

                client.subscribe('/topic', message => {
                    const updatedPlayers = JSON.parse(message.body);
                    setPlayers(updatedPlayers);
                })
            },
            debug: (str) => {
                console.log(new Date(), str);
            },
            onStompError: function (frame) {
                console.log('Broker reported error: ' + frame.headers['message']);
                console.log('Additional details: ' + frame.body);
            }           
        });

        client.activate();

        return () => {
            client.deactivate();
          };
    }, []);

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