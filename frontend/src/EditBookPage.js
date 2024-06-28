import React from "react";
import PlayerForm from "./PlayerForm";

function EditBookPage({player, switchPage}) {
    async function handleSubmit(event, player) {
        event.preventDefault();

        console.log(player);
        await fetch('/api/players/' + player.id, {
            method: "put",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(player)
        });
        switchPage();
    }

    return (
        <div className="container main-content">
            <div className="row justify-content-center">
            <div className="col-md-6">
                <div className="form-container mt-5">
                    <h2 className="text-center mb-4">Изменить данные игрока</h2>
                    <PlayerForm initialPlayerState={player} onSubmit={handleSubmit} />
                </div>
                </div>
            </div>
        </div>
    )
}

export default EditBookPage;