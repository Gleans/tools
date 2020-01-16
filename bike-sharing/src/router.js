import React, { Component } from 'react';
import { HashRouter, Route, Switch } from 'react-router-dom';
import App from './App'
import Login from './pages/login/index'
import Admin from './admin';
import Buttons from './pages/ui/buttons/index';
import NoMatch from './pages/nomatch/index';


class router extends Component {
    render() {
        return (
            <HashRouter>
                <App>
                    <Route path="/login" component={Login}></Route>
                    <Route path="/admin" render={() =>
                        <Admin>
                            <Route path="/admin/ui/buttons" component={Buttons}></Route>
                            <Route component={NoMatch} />
                        </Admin>
                    }></Route>
                    <Route path="/order/detail" component={Login}></Route>
                </App>
            </HashRouter>
        );
    }
}

export default router;