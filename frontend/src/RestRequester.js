import axios from "axios";

const serverURL = "http://localhost:9000";
const productURL = `${serverURL}/products`;
const categoryURL = `${serverURL}/categories`;
const creditCardURL = `${serverURL}/creditcards`;
const addressURL = `${serverURL}/address`;
const userURL = `${serverURL}/users`;
const orderURL = `${serverURL}/orders`;

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

export const getCreditCard = (id) => {
    return axios.get(`${creditCardURL}/${id}`, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export const getAddress = (id) => {
    return axios.get(`${addressURL}/${id}`, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
export const getOrder = (id) => {
    return axios.get(`${orderURL}/${id}`, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
