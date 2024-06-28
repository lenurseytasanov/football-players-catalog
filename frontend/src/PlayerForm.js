import React, {useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import TeamSelectBox from "./TeamSelectBox";

function PlayerForm({initialPlayerState, onSubmit}) {
    const [player, setPlayer] = useState({
        firstname: initialPlayerState ? initialPlayerState.firstname : "",
        lastname: initialPlayerState ? initialPlayerState.lastname : "",
        sex: initialPlayerState ? initialPlayerState.sex : "",
        birthdate: initialPlayerState ? initialPlayerState.birthdate : "",
        team: initialPlayerState ? initialPlayerState.team : "",
        country: initialPlayerState ? initialPlayerState.country : ""
    });

    function handleChange(event) {
        const {name, value} = event.target;
        setPlayer({
            ...player, 
            [name]: value
        });
    }

    return (
        <form className="container" onSubmit={(event) => onSubmit(event, player)}>
            <div className="form-group">
                <label>Имя</label>
                <input className="form-control" type="text" name="firstname" value={player.firstname} onChange={handleChange}></input>
            </div>
            <div className="form-group">
                <label>Фамилия</label>
                <input className="form-control" type="text" name="lastname" value={player.lastname} onChange={handleChange}></input>
            </div>
            <div className="form-group">
                <label>Пол</label>
                <div className="form-check form-check-inline">
                    <label className="form-check-label">Мужской</label>
                    <input className="form-check-input" type="radio" name="sex" value="male" onChange={handleChange}></input>
                </div>      
                <div className="form-check form-check-inline">
                    <label className="form-check-label">Женский</label>
                    <input className="form-check-input" type="radio" name="sex" value="female" onChange={handleChange}></input>
                </div>                       
            </div>
            <div className="form-group">
                <label>Дата рождения</label>
                <input className="form-control" type="date" name="birthdate" value={player.birthdate} onChange={handleChange}></input>
            </div>
            <TeamSelectBox value={player.team} onChange={handleChange} />
            <div className="form-group">
                <label>Страна</label>
                <select className="form-control" name="country" value={player.country} onChange={handleChange}>
                    <option value="russia">Россия</option>
                    <option value="usa">США</option>
                    <option value="italy">Италия</option>
                </select>
            </div>
            <button type="submit" className="btn btn-primary btn-block">Сохранить</button>
        </form>
    );
}

export default PlayerForm;