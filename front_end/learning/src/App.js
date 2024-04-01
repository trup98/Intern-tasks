import logo from './logo.svg';
import './App.css';
import {useState} from "react";

function App() {
  const [name, setName] = useState("Hola");

  const changeName =
  () => {
    setName("Hi")
    console.log("State change")
  }

  return (
    <div className="App">
      <h2>{name}</h2>
      <button onClick={()=>changeName()}>Click Here</button>
    </div>
  );
}

export default App;
