import React, { Component } from 'react';
import { HashRouter, Route, Link ,Switch} from 'react-router-dom';
import Main from './Main'
import About from './About'
import Topic from './Topic'

class Home extends Component {
    render() {
        return (
            <HashRouter>
                <div>
                    <ul style={{ listStyleType: "none", padding: 0 }}>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/about">About</Link>
                        </li>
                        <li>
                            <Link to="/topic">Topic</Link>
                        </li>
                    </ul>
                    <hr />
                    {/* <Route exact={true} path="/" component={Main}></Route> */}
                    <Switch>
                        <Route exact={true} path="/" component={Main}></Route>
                        <Route path="/about" component={About}></Route>
                        <Route path="/topic" component={Topic}></Route>
                    </Switch>
                </div>
            </HashRouter>
        );
    }
}

export default Home;