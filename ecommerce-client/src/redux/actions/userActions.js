import axios from "axios";

export const SIGN_IN = "SIGN_IN";
export const SIGN_UP = "SIGN_UP";

export const signUp = (inputs, addToast) => {
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
        }).then(response => {
            console.log(response);
        }).catch(error => {
                let messages = error.response.data;

                for (const value of Object.values(messages)) {
                    addToast(`${value}`, {appearance: "error", autoDismiss: true});
                }
            }
        );
    };
};
