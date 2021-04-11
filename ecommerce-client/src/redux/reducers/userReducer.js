import uuid from "uuid/v4";
import cartReducer from "./cartReducer";


const initState = [];

const userReducer = (state = initState, action) => {
    console.log('state', state);

    return state;
};

export default cartReducer;