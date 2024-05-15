import {Button} from "reactstrap";
import SideBar from "./SideBar";
import {doLogout} from "../auth/AuthLogin";
import {useNavigate} from "react-router-dom";

export const Navbar = () => {

  const navigate = useNavigate();

  const handleLogOut = ()=>{
    doLogout();
    navigate("/login");
  }

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
  )
}
