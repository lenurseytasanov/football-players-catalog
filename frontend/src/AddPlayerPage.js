import React from "react";
import PlayerForm from "./PlayerForm";

function AddPlayerPage() {

    async function sendData(player) {
        await fetch('/api/players', {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(player)
        });
    }

    return (
        <div className="container main-content">
            <div className="row justify-content-center">
            <div className="col-md-6">
                <div className="form-container mt-5">
                    <h2 className="text-center mb-4">Добавить игрока</h2>
                    <PlayerForm sendData={sendData} />
                </div>
                </div>
            </div>
        </div>
    )
}

export default AddPlayerPage;