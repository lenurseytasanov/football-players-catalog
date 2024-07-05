import React, { useState } from "react";
import PlayerListPage from "./PlayerListPage";
import EditPlayerPage from "./EditPlayerPage";

function MainPage() {
    const [isEdit, setIsEdit] = useState(false);
    const [activePlayer, setActivePlayer] = useState({});

    function switchPage() {
        setIsEdit(!isEdit);
    }

    function setPlayer(player) {
        setActivePlayer(player)
    }

    return isEdit ? (
            <EditPlayerPage switchPage={switchPage} activePlayer={activePlayer}/>
        ) : (
            <PlayerListPage switchPage={switchPage} setPlayer={setPlayer} />
        );
}

export default MainPage;