import React, {useEffect, useState} from "react";
import {getOrder} from "../RestRequester";

function Orders(props) {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        getOrder(props.id)
            .then(res => {
                setOrders( res.data
                )

            })}, []);

    return(
        <div className="orders">
            <ul>
                {orders.map((order, index) => (
                    <div key={index}>
                        <h2>{order.id}: {order.amount}</h2>
                        <p>{order.date}</p>
                    </div>
                ))}
            </ul>
        </div>
    );
}

export default Orders;
