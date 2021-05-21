import React, {Component} from "react";
import axios from "axios";

class Details extends Component {
    constructor() {
        super();
        this.state = {
            orderElements: [],
        }
    }

    componentDidMount() {

    }

    render() {
        return(
            <div className="details">
                {this.props.match.params.id}
            </div>
        )
    }
}

export default Details;
