import React, {useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import TeamSelectBox from "./TeamSelectBox";

function PlayerForm({initialPlayerState, sendData}) {

    const [player, setPlayer] = useState({
        firstname: initialPlayerState ? initialPlayerState.firstname : "",
        lastname: initialPlayerState ? initialPlayerState.lastname : "",
        sex: initialPlayerState ? initialPlayerState.sex : "",
        birthdate: initialPlayerState ? initialPlayerState.birthdate : "",
        team: initialPlayerState ? initialPlayerState.team : "",
        country: initialPlayerState ? initialPlayerState.country : ""
    });
    const [errors, setErrors] = useState({});

    function handleChange(event) {
        const {name, value} = event.target;
        setPlayer({
            ...player, 
            [name]: value
        });

        if (!value.trim()) {
            setErrors({
                ...errors,
                [name]: '(*)'
            });
        } else {
            setErrors({
                ...errors,
                [name]: ''
            });
        }
    }

    function handleSubmit(event) {
        event.preventDefault();

        let newErrors = {};
        Object.keys(player).filter(key => !player[key]).forEach(key => newErrors[key] = '(*)');
        setErrors({...errors, ...newErrors})
        if (Object.values(player).every(value => value.trim())) {
            sendData(player);
        }
    }

    return (
        <form className="container" onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Имя</label>
                <input className="form-control" type="text" name="firstname" value={player.firstname} onChange={handleChange}></input>
                {errors.firstname && <p className="mandatory-field">{errors.firstname}</p>}
            </div>
            <div className="form-group">
                <label>Фамилия</label>
                <input className="form-control" type="text" name="lastname" value={player.lastname} onChange={handleChange}></input>
                {errors.lastname && <p className="mandatory-field">{errors.lastname}</p>}
            </div>
            <div className="form-group">
                <label>Пол</label>
                <div className="form-check form-check-inline">
                    <label className="form-check-label">Мужской</label>
                    <input className="form-check-input" type="radio" name="sex" value="MALE" onChange={handleChange}></input>
                </div>      
                <div className="form-check form-check-inline">
                    <label className="form-check-label">Женский</label>
                    <input className="form-check-input" type="radio" name="sex" value="FEMALE" onChange={handleChange}></input>
                </div>
                {errors.sex && <p className="mandatory-field">{errors.sex}</p>}
            </div>
            <div className="form-group">
                <label>Дата рождения</label>
                <input className="form-control" type="date" name="birthdate" value={player.birthdate} onChange={handleChange}></input>
                {errors.birthdate && <p className="mandatory-field">{errors.birthdate}</p>}
            </div>
            <div className="form-group">
                <TeamSelectBox value={player.team} onChange={handleChange} />
                {errors.team && <p className="mandatory-field">{errors.team}</p>}
            </div>
            <div className="form-group">
                <label>Страна</label>
                <select className="form-control" name="country" value={player.country} onChange={handleChange}>
                    <option></option>
                    <option value="RUSSIA">Россия</option>
                    <option value="USA">США</option>
                    <option value="ITALY">Италия</option>
                </select>
                {errors.country && <p className="mandatory-field">{errors.country}</p>}
            </div>
            <button type="submit" className="btn btn-primary btn-block">Сохранить</button>
        </form>
    );
}

export default PlayerForm;