import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { Autocomplete, TextField } from "@mui/material";

function TeamSelectBox({value, onChange}) {
    const [teams, setTeams] = useState(['team 1', 'team 2']);

    async function getTeams() {
        await fetch("/api/teams")
        .then(response => response.json())
        .then(list => setTeams(list))
        .catch(error => {});
    }

    return (
        <div className="form-group">
            <label>Команда</label>
            <Autocomplete
                inputValue={value}
                onOpen={getTeams}
                onInputChange={(event, newValue) => onChange({target: {name: 'team', value: newValue}})}
                freeSolo
                options={teams}
                sx={{ width: 300 }}
                renderInput={(params) => <TextField {...params} />}
            />                 
        </div>
    );
}

export default TeamSelectBox;