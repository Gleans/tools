import React from 'react'
import { Row, Col } from 'antd';
import Util from '../../utils/utils'
import axios from '../../axios/index'
import './index.less'

export default class Header extends React.Component {

    componentWillMount() {
        this.setState({
            userName: "admin"
        })
        setInterval(() => {
            let sysTime = Util.formateDate(new Date().getTime());
            this.setState({
                sysTime
            })
        }, 1000)
        this.getWeatherAPIData('shanghai');
    }

    getWeatherAPIData(city) {
        axios.jsonp({
            url: 'https://free-api.heweather.net/s6/weather/now?location=' + city + '&key=e3df556af8ab43e28173d050e974c1bd'
        }).then((res) => {

        })
    }

    render() {
        return (
            <div className="header">
                <Row className="header-top">
                    <Col span="24">
                        <span>欢迎，{this.state.userName}</span>
                        <a href="#">退出</a>
                    </Col>
                </Row>
                <Row className="breadcrumb">
                    <Col span="4" className="breadcrumb-title">
                        首页
                    </Col>
                    <Col span="20" className="weather">
                        <span className="date">{this.state.sysTime}</span>
                        <span className="weather-detail">晴转多云</span>
                    </Col>
                </Row>
            </div>
        );
    }
}