import React, {useState} from 'react';

const Hoc = () => {
  const [count, setCount] = useState(0);
  return (
    <div>
      <h3>{count}</h3>
      <button onClick={() => setCount(count + 1)}>click</button>
    </div>
  );
};

function HOCRED(props) {
  return (
    <div>
      <h2 style={{backgroundColor: "red", width: 100}}>
        <props.cmp/>
      </h2>
    </div>
  );
};
export default Hoc;

