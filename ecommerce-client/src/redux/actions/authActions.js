import axios from "axios";

export const LOGIN_SUCCESS = "LOGIN_SUCCESS";
export const LOGIN_FAIL = "LOGIN_FAIL";

export const REGISTER_SUCCESS = "REGISTER_SUCCESS";
export const REGISTER_FAIL = "REGISTER_FAIL";

export const LOGOUT = "LOGOUT";

const showErrors = (error, addToast) => {
    let messages = error.response.data;
    for (const value of Object.values(messages)) {
        addToast(`${value}`, {appearance: "error", autoDismiss: true});
    }
}

export const register = (inputs, addToast) => {
    const {email, password, username} = inputs;
    return dispatch => {
        axios({
            method: 'post',
            url: 'http://localhost:8080/users',
            data: {
                email,
                password,
                username
            }
        }).then(() => {
            dispatch({
                type: REGISTER_SUCCESS,
            });
        }).catch(error => {
                showErrors(error, addToast);
                dispatch({
                    type: REGISTER_FAIL
                })
            }
        );
    };
};

export const login = (email, password, addToast) => {
    return dispatch => {
        axios({
            method: 'post',
            url: 'http://localhost:8080/users/login',
            data: {
                email,
                password,
            }
        }).then(response => {
            if (response.data.accessToken)
                localStorage.setItem("user", JSON.stringify(response.data));

            dispatch({
                type: LOGIN_SUCCESS,
            });
        }).catch(error => {
                showErrors(error, addToast);
                dispatch({
                    type: LOGIN_FAIL
                });
            }
        )
    }
}

export const logout = () => {
    return dispatch => {
        localStorage.removeItem("user");
        dispatch({
            type: LOGOUT
        });
    }

}