import React from 'react';
import {Navigate, Outlet} from "react-router-dom";
import {isAuthenticated} from "../auth/AuthLogin";

function PrivateRoute() {

  if (isAuthenticated()) {
    return <Outlet/>
  } else {
    return <Navigate to={"/login"}/>
  }
}

export default PrivateRoute;
