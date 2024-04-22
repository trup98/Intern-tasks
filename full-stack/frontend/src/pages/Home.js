import React, {useEffect, useState} from 'react';
import axios from "axios";


function Home() {

  const [users, setUser] = useState([]);

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const response = await axios.get("http://localhost:9096/user/find")
    setUser(response.data.data);
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
          </tr>
          </thead>
          <tbody>
          {
            users.map((user,id) => (
              <tr>
                <th scope="row" key={id}>{id + 1}</th>
                <td>{user.name}</td>
                <td>{user.userName}</td>
                <td>{user.email}</td>
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
