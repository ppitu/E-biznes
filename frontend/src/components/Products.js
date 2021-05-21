import React, {Component} from "react";
import axios from 'axios';
import {getProducts} from "../RestRequester";
import Categories from "./Categories";

class Products extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
        }
    }

    componentDidMount() {
        getProducts()
            .then(res => {
                const products = res.data;
                this.setState({products: products})
            })
    }

    render() {
        return (
            <div className="products">
                <ul>
                    {this.state.products.map((product, index) => (
                        <div key={index}>
                            <h3>{product.id}:{product.name}</h3>
                            <p>{product.description}</p>
                            <button>Dodaj to koszyka</button>
                            <Categories />
                        </div>
                    ))}
                </ul>
            </div>
        );
    }
}

export default Products;
