import React, { Component } from 'react';
import { HashRouter as Router, Route, Link } from 'react-router-dom';
import Main from './Main'
import About from './../router1/About'
import Topic from './../router1/Topic'
import Home from './Home'

class IRoute extends Component {
    render() {
        return (
            <Router>
                <Home>
                    <Route path="/main" render={() =>
                        <Main>
                            <div>
                                <Route path="/main/a" component={About}></Route>
                                {/* this is a sub child element. */}
                            </div>
                        </Main>
                    }></Route>
                    <Route path="/about" component={About}></Route>
                    <Route path="/topic" component={Topic}></Route>
                </Home>
            </Router>
        );
    }
}

export default IRoute;