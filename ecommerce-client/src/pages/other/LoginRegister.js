import PropTypes from "prop-types";
import React, {Fragment, useState} from "react";

import {useDispatch, useSelector} from 'react-redux';
import { Link, useLocation } from 'react-router-dom';
import MetaTags from "react-meta-tags";
import {BreadcrumbsItem} from "react-breadcrumbs-dynamic";
import Tab from "react-bootstrap/Tab";
import Nav from "react-bootstrap/Nav";
import LayoutOne from "../../layouts/LayoutOne";
import Breadcrumb from "../../wrappers/breadcrumb/Breadcrumb";
import {
    signUp
} from "../../redux/actions/userActions";
import {connect} from "react-redux";
import {useToasts} from "react-toast-notifications";

const LoginRegister = () => {
    const {pathname} = useLocation();
    const dispatch = useDispatch();
    const { addToast } = useToasts();
    const [inputs, setInputs] = useState({
        email: '',
        password: '',
        username: '',
    });

    const [submitted, setSubmitted] = useState(false);
    const {email, password, username} = inputs;

    const handleChange = e => {
        const {name, value} = e.target;
        setInputs(inputs => ({...inputs, [name]: value}))
        console.log('inputs', inputs);
    };

    const handleSignUpSubmit = e => {
        e.preventDefault();
        setSubmitted(true);
        if(email && password && username)
            dispatch(signUp(inputs, addToast));
    }

    return (
        <Fragment>
            <MetaTags>
                <title>ecommerce-webapp | Login</title>
                <meta
                    name="description"
                    content="Compare page of ecommerce-webapp react minimalist eCommerce template."
                />
            </MetaTags>
            <BreadcrumbsItem to={process.env.PUBLIC_URL + "/"}>Home</BreadcrumbsItem>
            <BreadcrumbsItem to={process.env.PUBLIC_URL + pathname}>
                Login Register
            </BreadcrumbsItem>
            <LayoutOne headerTop="visible">
                {/* breadcrumb */}
                <Breadcrumb/>
                <div className="login-register-area pt-100 pb-100">
                    <div className="container">
                        <div className="row">
                            <div className="col-lg-7 col-md-12 ml-auto mr-auto">
                                <div className="login-register-wrapper">
                                    <Tab.Container defaultActiveKey="login">
                                        <Nav variant="pills" className="login-register-tab-list">
                                            <Nav.Item>
                                                <Nav.Link eventKey="login">
                                                    <h4>Login</h4>
                                                </Nav.Link>
                                            </Nav.Item>
                                            <Nav.Item>
                                                <Nav.Link eventKey="register">
                                                    <h4>Register</h4>
                                                </Nav.Link>
                                            </Nav.Item>
                                        </Nav>
                                        <Tab.Content>
                                            <Tab.Pane eventKey="login">
                                                <div className="login-form-container">
                                                    <div className="login-register-form">
                                                        <form>
                                                            <input
                                                                type="text"
                                                                name="user-name"
                                                                placeholder="Username"
                                                            />
                                                            <input
                                                                type="password"
                                                                name="user-password"
                                                                placeholder="Password"
                                                            />
                                                            <div className="button-box">
                                                                <div className="login-toggle-btn">
                                                                    <input type="checkbox"/>
                                                                    <label className="ml-10">Remember me</label>
                                                                    <Link to={process.env.PUBLIC_URL + "/"}>
                                                                        Forgot Password?
                                                                    </Link>
                                                                </div>
                                                                <button type="submit">
                                                                    <span>Login</span>
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </Tab.Pane>
                                            <Tab.Pane eventKey="register">
                                                <div className="login-form-container">
                                                    <div className="login-register-form">
                                                        <form>
                                                            <input
                                                                type="text"
                                                                name="username"
                                                                placeholder="Username"
                                                                value={username}
                                                                onChange={handleChange}
                                                            />
                                                            <input
                                                                type="password"
                                                                name="password"
                                                                placeholder="Password"
                                                                value={password}
                                                                onChange={handleChange}
                                                            />
                                                            <input
                                                                name="email"
                                                                placeholder="Email"
                                                                type="email"
                                                                value={email}
                                                                onChange={handleChange}
                                                            />
                                                            <div className="button-box">
                                                                <button onClick={handleSignUpSubmit}>
                                                                    <span>Register</span>
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </Tab.Pane>
                                        </Tab.Content>
                                    </Tab.Container>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </LayoutOne>
        </Fragment>
    );
};

LoginRegister.propTypes = {
    location: PropTypes.object,
    signUp: PropTypes.func,
    signIn: PropTypes.func,
};

export default LoginRegister
