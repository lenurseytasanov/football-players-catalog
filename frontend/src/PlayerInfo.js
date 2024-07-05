import React from "react";

function PlayerInfo({value, switchPage, setPlayer}) {
    return (
        <div key={value.id} className="card">
            <div className="card-body">
                <p className="card-text">{value.firstname} {value.lastname}</p>
                <p className="card-text">{value.sex}</p>
                <p className="card-text">{value.birthdate}</p>
                <p className="card-text">{value.team}, {value.country}</p>
                <button onClick={() => {
                    setPlayer(value);
                    switchPage();
                }} className="btn btn-primary">Изменить</button>
            </div>
        </div>
    );
}

export default PlayerInfo;