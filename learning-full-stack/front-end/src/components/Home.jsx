import axios from 'axios';
import {useEffect} from 'react'
import React from 'react'
import {useParams} from "react-router-dom";
import {Button, Container, Pagination, PaginationItem, PaginationLink} from "reactstrap";
import Navbar from "./Navbar";

function Home() {
  const {id, status} = useParams();
  const [users, setUser] = React.useState([]);
  const [currentPage, setCurrentPage] = React.useState(0);
  const [userPerPage, setUserPerPage] = React.useState(10);
  const lastPageIndex = currentPage * userPerPage;
  const firstUserIndex = lastPageIndex - userPerPage;
  const currentUser = users.slice(firstUserIndex, lastPageIndex)
  const [value, setValues] = React.useState("");

  useEffect(() => {
    loadUser();
  }, []);

  const handleToggle = async (id, status) => {
    await axios.patch(`http://localhost:9000/api/user/changeStatus/${id}/${status}`);
    loadUser();
  }

  const loadUser = async () => {
    return await axios.get(`http://localhost:9000/api/user/find?pageNo=${currentPage}&pageSize=${userPerPage}`)
      .then((response) => setUser(response.data.data.content))
      .catch((error) => console.log(error));
  }

  const handleSearch = async (e) => {
    e.preventDefault();
    return await axios.get(`http://localhost:9000/api/user/find?searchKey=${value}`)
      .then((response) => {
        setUser(response.data.data.content);
        setValues("");
      })
      .catch(error => console.log(error));
  }

  const handleReset = ()=>{
    loadUser();
  }
  return (
    <div>
      <div className="container m-3">
        <div className="container-fluid">
          <form className="d-flex" onSubmit={handleSearch}>
            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" value={value}
                   onChange={(e) => setValues(e.target.value)}/>
            <button className="btn btn-outline-success" type="submit">Search</button>
            <button className="btn btn-outline-danger mx-2" type="submit" onSubmit={handleReset}>Reset</button>
          </form>
        </div>
      </div>
      <table className="table table-striped border">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">User Name</th>
          <th scope="col">Email</th>
          <th scope="col">Hobby Name</th>
          <th scope="col" >Status</th>
        </tr>
        </thead>
        <tbody>
        {
          users.map((user, id) => (
            <tr>
              <th scope="row" key={id}>{user.id}</th>
              <td>{user.userName}</td>
              <td>{user.email}</td>
              <td>{user.hobbyNames}</td>
              <td className="align-content-center">
                <div className="form-check form-switch ">
                  <input className="form-check-input" type="checkbox" id="flexSwitchCheckDefault" checked={user.status}
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

