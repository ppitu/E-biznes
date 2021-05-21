import React, {Component} from "react";
import axios from "axios";
import {getAddress} from "../RestRequester";

class Addresses extends Component {
    constructor(props) {
        super(props);
        this.state = {
            addresses: [],
        }
    }

    componentDidMount() {
        getAddress()
            .then(res => {
                const addresses = res.data;
                this.setState({addresses: addresses})
            })
    }

    render() {
        return(
            <div className="addresses">
                <ul>
                    {this.state.addresses.map((address, index) => (
                        <div key={index}>
                            <h2>{address.id}: {address.street}, {address.city}, {address.zipcode}</h2>
                        </div>
                    ))}
                </ul>
            </div>
        )
    }
}


export default Addresses;
