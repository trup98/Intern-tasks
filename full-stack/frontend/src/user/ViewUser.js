import React, {useEffect} from "react";
import {Link, useParams} from "react-router-dom";
import axios from "axios";


function ViewUser() {

  const [user, setUser] = React.useState({
    name: "",
    username: "",
    email: ""
  });

  const {id} = useParams();

  useEffect(() => {
    loadUser()
  }, []);

  const loadUser = async () => {
    const response = await axios.get(`http://localhost:9096/user/findById/${id}`)
    setUser(response.data.data);
  }
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h3 className="text-center m-4">User Details</h3>

          <div className="card">
            <div className="card-header">
              Details of user id : {user.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name : </b>
                  {user.name}
                </li>
                <li className="list-group-item">
                  <b>User Name : </b>
                  {user.userName}
                </li>
                <li className="list-group-item">
                  <b>Email : </b>
                  {user.email}
                </li>
              </ul>
            </div>
          </div>
          <Link to={"/"} className="btn btn-outline-dark my-2">Home</Link>
        </div>
      </div>
    </div>
  );
}

export default ViewUser;
