import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Link, useParams} from "react-router-dom";


function Home() {
  const {id} = useParams();
  const [users, setUser] = useState([]);

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const response = await axios.get("http://localhost:9096/user/find")
    setUser(response.data.data);
  }

  const deleteUser = async (id) => {
    await axios.delete(`http://localhost:9096/user/delete/${id}`)
    loadUser()
  }

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border table-striped  shadow">
          <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">Name</th>
            <th scope="col">User Name</th>
            <th scope="col">Email</th>
            <th scope="col">Action</th>
          </tr>
          </thead>
          <tbody>
          {
            users.map((user, id) => (
              <tr>
                <th scope="row" key={id}>{id + 1}</th>
                <td>{user.name}</td>
                <td>{user.userName}</td>
                <td>{user.email}</td>
                <td>
                  <Link className="btn btn-outline-dark mx-2" to={`/viewuser/${user.id}`}>View</Link>
                  <Link className="btn btn-outline-primary mx-2" to={`/edituser/${user.id}`}>Edit</Link>
                  <button className="btn btn-outline-danger mx-2" onClick={() => {
                    deleteUser(user.id)
                  }}>Delete
                  </button>
                </td>
              </tr>
            ))
          }
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Home;
