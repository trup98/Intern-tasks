import React from 'react';
import {Navigate, Outlet} from "react-router-dom";
import {isAuthenticated} from "../auth/AuthLogin";

function PrivateRoute() {


  if (isAuthenticated()) {
    return <Navigate to="/login"/>;
  } else {
    return <Outlet/>
  }
}

export default PrivateRoute;
