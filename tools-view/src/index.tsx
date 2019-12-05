import React, {
    createRef,
    useState,
    forwardRef,
    useImperativeHandle
} from 'react';
import ReactDOM from "react-dom";
import { Base64 } from 'js-base64';
import Form, { FormComponentProps } from "antd/lib/form/Form";
import { Button, Input, Divider, Icon, Checkbox } from "antd";

import "antd/dist/antd.css";
// import { any } from "prop-types";

interface UserFormProps extends FormComponentProps {
    username: string;
    password: string;
}

interface TestFormProps extends FormComponentProps {
    onSubmit: () => void;
}
type Ref = FormComponentProps;
const TestForm = forwardRef<Ref, TestFormProps>(
    ({ form, onSubmit }: TestFormProps, ref) => {
        useImperativeHandle(ref, () => ({
            form
        }));

        const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
            e.preventDefault();
            onSubmit();
        };
        const { getFieldDecorator } = form;
        return (
            <Form onSubmit={handleSubmit} className="login-form">
                <Form.Item>
                    {getFieldDecorator('username', {
                        rules: [{ required: true, message: 'Please input your username!' }],
                    })(
                        <Input
                            prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                            placeholder="Username"
                        />,
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('password', {
                        rules: [{ required: true, message: 'Please input your Password!' }],
                    })(
                        <Input
                            prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
                            type="password"
                            placeholder="Password"
                        />,
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('remember', {
                        valuePropName: 'checked',
                        initialValue: true,
                    })(<Checkbox>Remember me</Checkbox>)}
                    <a className="login-form-forgot" href="">
                        Forgot password
              </a>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Log in
              </Button>
                    Or <a href="">register now!</a>
                </Form.Item>
            </Form>
        );
    }
);

const EnhancedForm = Form.create<TestFormProps>()(TestForm);

function App() {
    const formRef = createRef<FormComponentProps>();
    const [inputValue, setInputValue] = useState();

    const handleSubmit = () => {

        if (formRef.current) {
            let form = formRef.current.form;
            var searchParams = new URLSearchParams()

            let map: Map<String, String> = new Map();

            searchParams.set('username', form.getFieldValue("username"));
            searchParams.set('password', form.getFieldValue("password"));
            searchParams.set('grant_type', 'password');

            fetch('/api/auth/oauth/token', {
                method: 'POST',
                body: searchParams,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Authorization': 'Basic ' + Base64.encode("admin:admin")
                }
            }).then(function (response) {
                return response.json();
            }).then(function (myJson) {
                console.log(myJson);
            });


        } else {
            console.log("表单无值");
        }
    };

    return (
        <div>
            <div>I'm the value from form: {inputValue}</div>
            <Divider />
            <EnhancedForm onSubmit={handleSubmit} wrappedComponentRef={formRef} />
        </div>
    );
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
