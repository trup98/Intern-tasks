import React from 'react';
import {Navigate, Outlet, useLocation} from "react-router-dom";
import {isAuthenticated} from "../service/auth/AuthTokenProvider";
import {Navbar} from "../pages/Navbar";

function PrivateRoute() {

  const location = useLocation();

  if (isAuthenticated()) {
    localStorage.setItem("redirectAfterLogin",location.pathname);
    console.log(location.pathname)
    return <Navigate to="/login"/>;
  } else {
    return <>
      <Navbar/>
      <Outlet/>
    </>
  }
}

export default PrivateRoute;
