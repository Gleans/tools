import React, { Component } from 'react';
import { HashRouter,Link } from 'react-router-dom/cjs/react-router-dom.min';

class Main extends Component {
    render() {
        return (
            <HashRouter>
                <div>
                    this is main page.
                    <Link to="/main/a">嵌套</Link>
                    <hr/>
                    {this.props.children}
                </div>
            </HashRouter>

        );
    }
}

export default Main;