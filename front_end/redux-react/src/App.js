import './App.css';
import {useSelector, useDispatch} from 'react-redux';
import React from 'react';
import {incNumber, decNumber} from "./action";


const App = () => {
  const currentState = useSelector(state => state.changeNumber);
  const dispatch = useDispatch();
  return (
    <div className="container">

      <h1>Increment/Decrement counter</h1>
      <h4>Using React-Redux</h4>

      <div className="quantity">
        <button className="btn-warning" onClick={() => dispatch(decNumber())}> -</button>
        <input className="input-group-btn" type="text" value={currentState} name="input-group-btn"/>
        <button className="btn-success" onClick={() => dispatch(incNumber())}> +</button>
      </div>

    </div>
  );
};

export default App;
