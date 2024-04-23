import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";

function AddUser() {

  let navigate = useNavigate();
  // storing information in object
  const [user, setUser] = React.useState({
    name: "",
    userName: "",
    email: ""
  });

  // deconstruct thw object
  const {name, userName, email} = user;

  const onInputChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value});
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:9096/user/add", user)
    navigate("/");
  }
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h3 className="text-center m-4">Add User</h3>
          <form onSubmit={(e) => handleSubmit(e)}>
            <div className="md-3">
              <label htmlFor="name" className="form-label">Name</label>
              <input className="form-control mt-2" name="name" type="text" placeholder="Enter Your Name"
                     value={name} onInput={(e) => onInputChange(e)} required/>
            </div>
            <div className="md-3">
              <label htmlFor="userName" className="form-label mt-4">User Name</label>
              <input className="form-control mt-2" name="userName" type="text" placeholder="Enter Your User Name"
                     value={userName} onInput={(e) => onInputChange(e)} required/>
            </div>
            <div className="md-3">
              <label htmlFor="email" className="form-label mt-4">Email</label>
              <input className="form-control mt-2" name="email" type="text" placeholder="Enter Your Email"
                     value={email} onInput={(e) => onInputChange(e)} required/>
            </div>
            <button className="btn btn-outline-success mt-4" type="submit">Submit</button>
            <Link to="/" className="btn btn-outline-danger mx-2 mt-4 ">Cancel</Link>
          </form>
        </div>
      </div>
    </div>
  )
    ;
}

export default AddUser;
