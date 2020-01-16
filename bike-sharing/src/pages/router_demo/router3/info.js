import React, { Component } from 'react';

class info extends Component {
    render() {
        return (
            <div>
                this  is 动态 路由.<br/>
                动态路由值：{this.props.match.params.mainId}
            </div>
        );
    }
}

export default info;