import {
    REGISTER_SUCCESS,
    REGISTER_FAIL,
} from "../actions/authActions";

const user = JSON.parse(localStorage.getItem("user"));

const initialState = user
    ? {isLoggedIn: true, user}
    : {isLoggedIn: false, user: null};

const authReducer = (state = initialState, action) => {
    const {type, payload} = action;
    console.log('action', action);
    switch (type) {
        case REGISTER_SUCCESS:
            return {
                ...state,
                isLoggedIn: false
            };
        case REGISTER_FAIL:
            return {
                ...state,
                isLoggedIn: false
            };
    }
    return state;
};

export default authReducer;