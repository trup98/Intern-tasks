import {Button, NavItem, NavLink} from "reactstrap";
import {useState} from "react";
import {LoginPage} from "../pages/LoginPage";

export const Navbar = () => {

  const [modelOpen, setModelOpen] = useState(false);
  const toggleModel = () => setModelOpen(!modelOpen);

  return (
    <>
      <nav className="navbar navbar-light bg-dark">
        <div className="container-fluid">
          <a className="navbar-brand text-white">CRUD- Security</a>

          <div className="d-flex">

            <Button color="btn btn-outline-primary mx-2 text-white" onClick={toggleModel}>Login</Button>
            <LoginPage isOpen={modelOpen} toggle={toggleModel}/>
          </div>
        </div>
      </nav>

    </>
  )
}
