import './App.css';
import Greet from "./component/Greet";
import Welcome from "./component/Welcome";
import UserGreeting from "./component/UserGreeting";
import NameList from "./component/NameList";
import IncrementCounter from "./component/IncrementCounter";
import HoverCounter from "./component/HoverCounter";
import Counter from "./component/Counter";
import incrementCounter from "./component/IncrementCounter";
import RefDemo from "./component/RefDemo";
import Hoc, {HOCRED} from "./component/Hoc";
import React from "react";
import ReducerHook from "./component/ReducerHook";


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
      {/*<NameList/>*/}
      {/*<IncrementCounter/>*/}
      {/*<HoverCounter/>*/}
      {/*<Counter render={(count, incrementCount) => (*/}
      {/*  <IncrementCounter count={count} incrementCount={incrementCount}/>*/}
      {/*)}*/}
      {/*/>*/}
      {/*<Counter render={(count, incrementCount) => (*/}
      {/*  <HoverCounter count={count} incrementCount={incrementCount}/>*/}
      {/*)}*/}
      {/*/>*/}
      {/*<RefDemo/>*/}
      {/*<h1>HOC</h1>*/}
      {/*<HOCRED cmp={Hoc}/>*/}
      <ReducerHook/>
    </div>
  );
}



export default App;
