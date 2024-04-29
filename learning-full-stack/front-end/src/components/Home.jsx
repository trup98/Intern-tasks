import axios from 'axios';
import {useEffect} from 'react'
import React from 'react'
import {useParams} from "react-router-dom";

function Home() {
  const {id, status} = useParams();
  const [users, setUser] = React.useState([]);



  useEffect(() => {
    loadUser();
  }, []);

  const handleToggle = async (id, status) => {
    await axios.patch(`http://localhost:9000/api/user/changeStatus/${id}/${status}`);
    loadUser();
  }

  const loadUser = async () => {
    const response = await axios.get(`http://localhost:9000/api/user/find`);
    console.log(response.data.data.content.map(obj => obj.status))
    if (response?.data?.data?.content) {
      setUser(response.data.data.content);
    }

  }
  return (
    <div>
      <table className="table table-striped border">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">User Name</th>
          <th scope="col">Email</th>
          <th scope="col">Hobby Name</th>
          <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        {
          users.map((user, id) => (
            <tr>
              <th scope="row" key={id}>{id + 1}</th>
              <td>{user.userName}</td>
              <td>{user.email}</td>
              <td>{user.hobbyNames}</td>
              <td>
                <div class="form-check form-switch">
                  <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" checked={user.status}
                         onClick={() => {
                           handleToggle(user.id, user.status)
                         }}/>
                </div>
              </td>
            </tr>

          ))
        }

        </tbody>
      </table>
    </div>
  )
}


export default Home

