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
}


export default Addresses;
