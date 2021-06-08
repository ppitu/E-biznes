import React, {useEffect, useState} from "react";
import {getCreditCard, getProducts} from "../RestRequester";

function CreditCards() {
    const [creditCards, setCreditCards] = useState([]);

    useEffect(() => {
        getCreditCard()
            .then(res => {
                setCreditCards(res.data)
            })}, []
    );

    return(
        <div className="creditcards">
            <h2>{creditCards.id}: {this.state.creditCards.holderName}</h2>
            <p>{creditCards.number}, {this.state.creditCards.cvv}, {this.state.creditCards.date}</p>
        </div>
    )
}

export default CreditCards;
