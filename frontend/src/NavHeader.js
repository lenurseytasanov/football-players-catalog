import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./App.css";

export default function NavHeader() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="navbar-brand">Каталог футболистов 3.0</div>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item active">
                        <a className="nav-link" href="/new">Добавить игрока</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="/players">Список игроков</a>
                    </li>
                </ul>
            </div>
        </nav>
    );
}