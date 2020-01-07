import React from 'react';
import MenuConfig from './../../config/menuConfig';
import { Menu, Icon } from 'antd';
import testImg from './../../resource/assets/logo-ant.svg'
import './index.less'

const { SubMenu } = Menu;

export default class NavLeft extends React.Component {
    UNSAFE_componentWillMount() {
        const menuTreeNode = this.renderMenu(MenuConfig)

        this.setState({
            menuTreeNode: menuTreeNode
        })
    }


    // 菜单渲染
    renderMenu = (data) => {
        return data.map((ele) => {
            if (ele.children) {
                return (
                    <SubMenu title={ele.title} key={ele.key}>
                        {this.renderMenu(ele.children)}
                    </SubMenu>
                )
            }
            return <Menu.Item key={ele.key} >{ele.title}</Menu.Item>
        })

    }

    render() {
        return (
            <div>
                <div className="logo">
                    <img src={testImg} alt="" />
                    <h1>React Learning</h1>
                </div>
                <Menu theme="dark">
                    {this.state.menuTreeNode}
                </Menu>
            </div>
        );
    }
}