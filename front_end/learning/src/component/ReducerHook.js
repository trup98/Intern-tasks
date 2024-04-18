import React, {useReducer} from 'react';

const initialState = 0;
const reducer = (state, action) => {
  console.log(state, action)
  if (action.type === "INCREMENT") {
    return state + 1;
  }
  if (action.type === "DECREMENT") {
    return state - 1;
  }
  return state;

}

const ReducerHook = () => {

  const [state, dispatch] = useReducer(reducer, initialState);
  return (
    <div>
      <h4>{state}</h4>
      <button onClick={() => dispatch({type: "INCREMENT"})}>Increment</button>
      <button onClick={() => dispatch({type: "DECREMENT"})}>Decrement</button>
    </div>
  );
};

export default ReducerHook;
