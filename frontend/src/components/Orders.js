import React, {Component} from "react";
import axios from "axios";
import {getOrder} from "../RestRequester";

class Orders extends Component {
    constructor() {
        super();
        this.state = {
            orders: [],
        }
    }

    componentDidMount() {
        getOrder()
            .then(res => {
                const orders = res.data;
                this.setState({orders: orders})
            })
    }

    render() {
        return(
            <div className="orders">
                <ul>
                    {this.state.orders.map((order, index) => (
                        <div key={index}>
                            <h2>{order.id}: {order.amount}</h2>
                            <p>{order.date}</p>
                        </div>
                    ))}
                </ul>
            </div>
        )
    }
}

export default Orders;
