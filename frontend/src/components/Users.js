import React, {Component} from "react";
import axios from "axios";
import {getUsers} from "../RestRequester";
import {Link, Route, BrowserRouter as Router,} from "react-router-dom";
import Details from "./Details";

class Users extends Component {
    constructor() {
        super();
        this.state = {
            users: [],
        }
    }

    componentDidMount() {
        getUsers()
            .then(res => {
                const users = res.data;
                this.setState({users: users})
            })
    }

    render() {
        return(
            <Router>
            <div className="users">
                <ul>
                    {this.state.users.map((user, index) => (
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
}

export default Users;
