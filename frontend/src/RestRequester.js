import axios from "axios";

const serverURL = "http://localhost:9000";
const productURL = `${serverURL}/products`;
const categoryURL = `${serverURL}/categories`;
const creditCardURL = `${serverURL}/creditcards`;
const addressURL = `${serverURL}/address`;
const userURL = `${serverURL}/users`;
const orderURL = `${serverURL}/order`;

export const getProducts = () => {
    return axios.get(productURL, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export const getCategories = () => {
    return axios.get(categoryURL, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export const getUsers = () => {
    return axios.get(userURL, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}

export const getCreditCard = () => {
    return axios.get(creditCardURL, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export const getAddress = () => {
    return axios.get(addressURL, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export const getOrder = () => {
    return axios.get(orderURL, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
