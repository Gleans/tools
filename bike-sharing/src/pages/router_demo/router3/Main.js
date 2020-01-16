import React, { Component } from 'react';
import { HashRouter, Link } from 'react-router-dom/cjs/react-router-dom.min';

class Main extends Component {
    render() {
        return (
            <HashRouter>
                <div>
                    this is main page.
                    <br/>
                    <Link to="/main/test-id">嵌套1</Link>
                    <br />
                    <Link to="/main/456">嵌套2</Link>
                    <hr />
                    {this.props.children}
                </div>
            </HashRouter>

        );
    }
}

export default Main;