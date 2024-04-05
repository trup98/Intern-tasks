import React from 'react';


const Greet = (props) => {
  console.log(props);
  return (
    <div>
      <h2>Hello {props.name}</h2>
      {props.children}
    </div>
  );
};

export default Greet;
