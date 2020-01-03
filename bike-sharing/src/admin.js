import React from 'react'
import { Row, Col } from 'antd';
import Header from './components/Header';
import NavLeft from './components/NavLeft';
import Footer from './components/Footer';

export default class Admin extends React.Component {
    render() {
        return (
            <Row className="container">
                <Col span="4" className="nav-left">
                    <NavLeft/>
                </Col>
                <Col span="20" className="main">
                    <Header/>
                    <Row className="content">
                        {this.props.children}
                    </Row>
                    <Footer className="footer"/>
                </Col>
            </Row>
        );
    }
}