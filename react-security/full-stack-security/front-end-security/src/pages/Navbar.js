import {Button} from "reactstrap";
import SideBar from "./SideBar";
import {doLogout, refreshToken} from "../service/auth/CookieStore";
import {useNavigate} from "react-router-dom";
import {toast, Zoom} from "react-toastify";
import useUserActivity from "../hooks/useUserActivity";
import {useEffect} from "react";

export const Navbar = () => {
  const navigate = useNavigate();

  const logOut = () => {
    toast.error("Log Out due to inactivity!");
    doLogout();
    navigate("/login");
  };

  const isActive = useUserActivity(logOut);

  useEffect(() => {
    let refreshInterval;
    if (isActive) {
      refreshInterval = setInterval(() => {
        refreshToken();
      }, 900000); // 15 minutes
    }
    return () => clearInterval(refreshInterval);
  }, [isActive]);

  const handleLogOut = () => {
    doLogout();
    navigate("/login");
    toast.success("Log Out successfully", {
      transition: Zoom
    });
  };

  return (
    <>
      <nav className="navbar navbar-light bg-dark">
        <div className="d-flex align-items-center w-100">
          <div className="text-white">
            <SideBar/>
          </div>
          <div className="container-fluid d-flex justify-content-end align-items-center">
            <a className="navbar-brand text-white">CRUD- Security</a>
            <div className="ms-auto">
              <Button color="btn btn-outline-primary mx-2 text-white" onClick={handleLogOut}>Logout</Button>
            </div>
          </div>
        </div>
      </nav>
    </>
  );
};
