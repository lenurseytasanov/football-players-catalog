import React, { useState } from "react";
import PlayerListPage from "./PlayerListPage";
import EditBookPage from "./EditBookPage";

function MainPage() {
    const [isEdit, setIsEdit] = useState(false);

    function switchPage() {
        setIsEdit(!isEdit);
    }

    return isEdit ? (
            <EditBookPage switchPage={switchPage} />
        ) : (
            <PlayerListPage switchPage={switchPage} />
        );
}

export default MainPage;