import React from 'react';
import {Navigate, Outlet} from "react-router-dom";
import {isAuthenticated} from "../auth/AuthLogin";
import {Navbar} from "../pages/Navbar";

function PrivateRoute() {


  if (isAuthenticated()) {
    return <Navigate to="/login"/>;
  } else {
    return <>
      <Navbar/>
      <Outlet/>
    </>
  }
}

export default PrivateRoute;
