import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import Life from './pages/demo/Life';

import * as serviceWorker from './serviceWorker';
// import Home from './pages/router_demo/router1/Home';
import Router from './router';

import './style/common.less'

ReactDOM.render(<Router />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
