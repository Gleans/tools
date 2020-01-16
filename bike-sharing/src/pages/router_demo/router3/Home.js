import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Home extends Component {
    render() {
        return (
            // <HashRouter>
                <div>
                    <ul style={{ listStyleType: "none", padding: 0 }}>
                        <li>
                            <Link to="/main">Home1</Link>
                        </li>
                        <li>
                            <Link to="/about">About1</Link>
                        </li>
                        <li>
                            <Link to="/topic">Topic1</Link>
                        </li>
                        <li>
                            <Link to="/makelove">makelove</Link>
                        </li>
                    </ul>
                    <hr />
                    {this.props.children}
                </div>
            // </HashRouter>
        );
    }
}

export default Home;