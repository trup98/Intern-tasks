import React from 'react'
import {Button} from "reactstrap";
import AddUser from "./AddUser";

export default function Navbar() {
  const [modelOpen, setModelOpen] = React.useState(false);
  const toggleModel = () => setModelOpen(!modelOpen);

  return (
    <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand">CRUD</a>
      <div class="d-flex">
        <Button color="btn btn-outline-primary mx-2" onClick={toggleModel} >Add</Button>
        <AddUser isOpen={modelOpen} toggle={toggleModel} />
        {/*<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>*/}
        {/*<button class="btn btn-outline-success" type="submit"  >Search</button>*/}
      </div>
    </div>
  </nav>
  )
}
