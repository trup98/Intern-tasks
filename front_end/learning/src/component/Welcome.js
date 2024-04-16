import React, {useState} from 'react';

const Welcome = (props) => {

  const [name, setName] = useState(props.name)
  const changeName = () => {
    setName("Thank You!")
  }
  return (
    <div>
      <h2>Welcome {name}</h2>
      <button onClick={changeName}>Click</button>
    </div>
  );
};

export default Welcome;
