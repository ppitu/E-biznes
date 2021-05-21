import React, {Component} from "react";
import axios from 'axios';
import {
    BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom';

import {getCategories} from "../RestRequester";

class Categories extends Component {
    constructor() {
        super();
        this.state = {
            categories: [],
        }
    }

    componentDidMount() {
        getCategories()
            .then(res => {
                const category = res.data;
                this.setState({categories: category})
            })
    }

    render() {
        return (
            <select name="category" value={this.state.categories}>
                {this.state.categories.map((category, index) => (
                    <option value={category.id}>{category.name}</option>
                ))}
            </select>
        );
    }
}

export default Categories;
