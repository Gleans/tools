import React from 'react'

export default class Life extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            count: 0
        };
    }

    UNSAFE_componentWillMount(){
        console.log('will mount')
    }

    UNSAFE_componentDidMount(){
        console.log('did mount')
    }

    UNSAFE_componentWillReceiveProps(newProps){
        console.log('will props'+ newProps.name)
    }

    UNSAFE_shouldComponentUpdate(){
        console.log('show update')
        return
    }

    UNSAFE_componentWillUpdate(){
        console.log('will update')
    }

    UNSAFE_componentDidUpdate(){
        console.log('did update')
    }

    render() {
        return <div>
            <p>this child 组件</p>
            <p>{this.props.name}</p>
        </div>
    }

}