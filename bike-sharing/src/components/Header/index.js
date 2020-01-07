import React from 'react'
import { Row, Col } from 'antd'
import Util from '../../utils/utils'
import Axios from '../../axios/index'
import './index.less'

export default class Header extends React.Component {
  UNSAFE_componentWillMount() {
    this.setState({
      userName: 'admin'
    })
    setInterval(() => {
      let sysTime = Util.formateDate(new Date().getTime())
      this.setState({
        sysTime
      })
    }, 1000)
    this.getWeatherAPIData('shanghai')
  }

  getWeatherAPIData(city) {
    Axios.jsonp({
      url:
        'http://api.map.baidu.com/telematics/v3/weather?location=' +
        decodeURIComponent('上海') +
        '&output=json&ak=GpfRm6YmTTPNa3ZnqYIIgGrDbaKDDuxu'
    }).then(res => {
      if (res.status === 'success') {
        let data = res.results[0].weather_data[0]
        this.setState({
          dayPictureUrl: data.dayPictureUrl,
          nightPictureUrl: data.nightPictureUrl
        })
      }
    })
  }

  render() {
    return (
      <div className="header">
        <Row className="header-top">
          <Col span={24}>
            <span>欢迎，{this.state.userName}</span>
            <a href="#">退出</a>
          </Col>
        </Row>
        <Row className="breadcrumb">
          <Col span={4} className="breadcrumb-title">
            首页
          </Col>
          <Col span={20} className="weather">
            <span className="date">{this.state.sysTime}</span>
            <span className="weather-detail">
              <img src={this.state.dayPictureUrl} alt="" />
            </span>
          </Col>
        </Row>
      </div>
    )
  }
}
