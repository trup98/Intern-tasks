import React from 'react';
import {useLocation} from "react-router-dom";

function Dashboard(props) {
  const location = useLocation();
  return (
    <div>
      <h4>Price:{location.state}</h4>
    </div>
  );
}

export default Dashboard;
