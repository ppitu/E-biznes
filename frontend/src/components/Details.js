import React, {Component} from "react";
import {Route} from "react-router-dom";
import CreditCards from "./CreditCards";
import Addresses from "./Addresses";
import Orders from "./Orders";

class Details extends Component {
    constructor() {
        super();
    }

    render() {
        return(
            <div className="details">
                <CreditCards id={this.props.match.params.id} />
                <Addresses id={this.props.match.params.id} />
                <Orders />
                {this.props.match.params.id}
            </div>
        )
    }
}

export default Details;
