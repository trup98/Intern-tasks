import './App.css';
import Greet from "./component/Greet";
import Welcome from "./component/Welcome";
import UserGreeting from "./component/UserGreeting";
import NameList from "./component/NameList";


function App() {

  return (
    <div className="App">
      {/*<Welcome name="Zoya"/>*/}
      {/*<Greet name="Nick">*/}
      {/*  <p>Example to show the inner html tag we can object. {<br/>} this is children prop without self closing the tag*/}
      {/*    .{<br/>} we can do it though props.children tag in children component*/}
      {/*  </p>*/}
      {/*</Greet>*/}
      {/*<Greet name="Soul"/>*/}
      {/*<Greet name="Kaya"/>*/}
      {/*<UserGreeting/>*/}
      <NameList/>
    </div>
  );
}

export default App;
