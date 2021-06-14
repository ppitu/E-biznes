import React, {Component, useEffect, useState} from "react";
import axios from "axios";
import {getAddress, getUsers} from "../RestRequester";

function Addresses(props) {
    const [addresses, setAddresses] = useState(0);

    useEffect(() => {
        getAddress(props.id)
            .then(res => {
                setAddresses({
                    users: res.data
                })

            })}, []);

    return(
        <div className="addresses">
            <h2>{addresses.id}: {addresses.street}, {addresses.city}, {addresses.zipcode}</h2>
        </div>
    )
}

/*class Addresses extends Component {
    constructor(props) {
        super(props);
        this.state = {
            addresses: [],
        }
    }

    componentDidMount() {
        getAddress(this.props.id)
            .then(res => {
                const addresses = res.data;
                this.setState({addresses: addresses})
            })
    }

    render() {
        return(
            <div className="addresses">
                <h2>{this.state.addresses.id}: {this.state.addresses.street}, {this.state.addresses.city}, {this.state.addresses.zipcode}</h2>
            </div>
        )
    }
}*/


export default Addresses;
