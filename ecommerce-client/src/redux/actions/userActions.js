export const SIGN_IN = "SIGN_IN";
export const SIGN_UP = "SIGN_UP";

export const signUp = (inputs, addToast) => {
    const {email, password, username} = inputs;
    console.log('has been clicked!', {email, password, username});
    return dispatch => {
        if (addToast) {
            addToast("Added To Cart", { appearance: "success", autoDismiss: true });
        }
        dispatch({
            type: signUp,
            payload: {
                email,
                password,
                username
            }
        });
    };
};
