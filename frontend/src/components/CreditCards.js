import React, {Component} from "react";
import axios from "axios";
import {getCreditCard} from "../RestRequester";

class CreditCards extends Component{
    constructor(props) {
        super(props);
        this.state = {
            creditCards: [],
        }
    }

    componentDidMount() {
        getCreditCard()
            .then(res => {
                const creditCards = res.data;
                this.setState({creditCards: creditCards})
            })
    }

    render() {
        return(
            <div className="creditcards">
                <ul>
                    {this.state.creditCards.map((creditCard, index) => (
                        <div key={index}>
                            <h2>{creditCard.id}: {creditCard.holderName}</h2>
                            <p>{creditCard.number}, {creditCard.cvv}, {creditCard.date}</p>
                        </div>
                    ))}
                </ul>
            </div>
        )
    }


}

export default CreditCards;
