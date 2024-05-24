import React from 'react';
import {Navigate, Outlet, useLocation} from "react-router-dom";
import {isAuthenticated} from "../service/auth/CookieStore";
import {Navbar} from "../pages/Navbar";

function PrivateRoutes() {

  const location = useLocation();

  if (isAuthenticated()) {

    return <>
      <Navbar/>
      <Outlet/>
    </>

  } else {
    console.log("false")
    const redirectPath = location.pathname + location.search
    return <Navigate to={`/login?redirect=${redirectPath}`} replace/>;
  }
}

export default PrivateRoutes;
