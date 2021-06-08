import React, {Component, useEffect, useState} from "react";
import axios from "axios";
import {getCategories, getUsers} from "../RestRequester";
import {Link, Route, BrowserRouter as Router,} from "react-router-dom";
import Details from "./Details";

function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        getUsers()
            .then(res => {
                setUsers(
                    res.data
                )

            })}, []);

    return(
        <Router>
            <div className="users">
                <ul>
                    {users.map((user, index) => (
                        <div key={index}>
                            <h2>{user.name}</h2>
                            <p>{user.email}</p>
                            <Link to={`/details/${user.id}`} >Szczególy</Link>
                        </div>
                    ))}
                </ul>
                <Route path="/details/:id" component={Details}/>
            </div>
        </Router>
    )
}

export default Users;
