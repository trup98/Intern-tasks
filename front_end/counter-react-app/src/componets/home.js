import {useState, useEffect} from "react";

const Home = () => {
  // Change the name in web after clicking the button;
  const [name, setName] = useState('kam');

  // useEffect demo
  const [state, setState] = useState(0);
  const handleClick = () => {
    setName(25);
  }
  useEffect(() => {
    console.log("useEffect");
  }, [state]);


  return (
    <div className="home">
      <h2>Home Page</h2>
      <p>{name}</p>
      <button onClick={handleClick}>Change Name</button>
      <p>useEffect {state}</p>
      <button onClick={() => setState(state + 1)}>Click</button>
    </div>
  );
}
export default Home;
