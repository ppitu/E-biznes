import React, {Component} from "react";

class Basket extends Component {
    constructor() {
        super();
        this.state = {
            basket: [],
        }
    }

    render() {
        return(
            <div className="basket">
                <ul>
                    {this.state.basket.map((basket, index) => (
                        <div key={index}>
                            <h2>{basket.id}: {basket.productName}, {basket.amount}</h2>
                        </div>
                    ))}
                </ul>
            </div>
        )
    }

}

export default Basket;
