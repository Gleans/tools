import React, { Component } from 'react';
import { HashRouter as Router, Route, Link, Switch } from 'react-router-dom';
import Main from './Main'
import Info from './info'
import Topic from '../router1/Topic'
import About from '../router1/About'
import Home from './Home'
import NoMatch from './NoMatch'

class IRoute extends Component {
    render() {
        return (
            <Router>
                <Home>
                    <Switch>
                        <Route path="/main" render={() =>
                            <Main>
                                <div>
                                    <Route path="/main/:mainId" component={Info}></Route>
                                </div>
                            </Main>
                        }></Route>
                        <Route path="/about" component={About}></Route>
                        <Route path="/topic" component={Topic}></Route>
                        <Route path="*" component={NoMatch}></Route>
                    </Switch>
                </Home>
            </Router>
        );
    }
}

export default IRoute;