import React, {useEffect, useState} from "react";
import {getCreditCard, getProducts} from "../RestRequester";

function CreditCards(props) {
    const [creditCards, setCreditCards] = useState([]);

    useEffect(() => {
        getCreditCard(props.id)
            .then(res => {
                setCreditCards(res.data)
            })}, []
    );

    return(
        <div className="creditcards">
            <h2>{creditCards.id}: {creditCards.holderName}</h2>
            <p>{creditCards.number}, {creditCards.cvv}, {creditCards.date}</p>
        </div>
    )
}

export default CreditCards;
