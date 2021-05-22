import React, {Component} from "react";
import {getCreditCard} from "../RestRequester";

class CreditCards extends Component{
    constructor(props) {
        super(props);
        this.state = {
            creditCards: [],
        }
    }

    componentDidMount() {
        getCreditCard(this.props.id)
            .then(res => {
                const creditCards = res.data;
                this.setState({creditCards: creditCards})
            })
    }

    render() {
        return(
            <div className="creditcards">
                            <h2>{this.state.creditCards.id}: {this.state.creditCards.holderName}</h2>
                            <p>{this.state.creditCards.number}, {this.state.creditCards.cvv}, {this.state.creditCards.date}</p>
            </div>
        )
    }


}

export default CreditCards;
