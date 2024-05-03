import React, {useEffect} from 'react'
import {toast, ToastContainer, Zoom} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {handleToggle, loadUserService} from "../service/user-service";
import {Pagination} from "react-bootstrap";

function Home() {

  const [users, setUser] = React.useState({
    data: [],
    totalPages: 0,
    pageNumber: 0,
    pageSize: 10
  });

  const [value, setValues] = React.useState("");
  const [currentPage, setCurrentPage] = React.useState(0);

  useEffect(() => {
    callAllUsers(currentPage);
  }, [currentPage, value]);

  const callAllUsers = (currentPage) => {
    loadUserService(value, value ? 0 : currentPage, users.pageSize)
      .then(resp => {
        console.log(resp.data)
        setUser({
          data: resp.data.data.content,
          totalPages: resp.data.data.totalPages,
          pageNumber: currentPage,
          pageSize: users.pageSize
        });
      });
  }


  const handleToggleFunctionality = (user) => {
    handleToggle(user.id, user.status)
      .then(resp => {
        console.log(resp);
        callAllUsers(users.pageNumber);
        notifyUserStatus();
      })
      .catch(error => {
        console.log(error)
      })
      .finally(resp => {
        console.log("all time print::: ");
      });
  }


  const notifyUserFound = () => toast.success("User Found!!", {
    position: "top-right",
    autoClose: 5000,
    transition: Zoom
  });

  const notifyUserStatus = () => toast.success("User Status Changed", {
    position: "top-right",
    autoClose: 5000,
    transition: Zoom

  })

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };


  return (

    <div>
      <div className="container m-3">
        <div className="container-fluid">
          <div className="d-flex" onSubmit={() => callAllUsers(currentPage)}>
            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" value={value}
                   onChange={(e) => {
                     setValues(e.target.value);
                   }}/>
            <button className="btn btn-outline-success" type="submit" onClick={notifyUserFound}>Search</button>
            <ToastContainer/>
          </div>
        </div>
      </div>

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
        {users.data && users.data.length > 0 ? (
          users.data.map((user, id) => (
            <tr key={id}>
              <th scope="row">{user.id}</th>
              <td>{user.userName}</td>
              <td>{user.email}</td>
              <td>{user.hobbyNames}</td>
              <td>
                <div className="form-check form-switch d-flex justify-content-center">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    checked={user.status}
                    onClick={() => handleToggleFunctionality(user)}
                  />
                </div>
              </td>
            </tr>
          ))
        ) : (
          <tr>
            <td colSpan="5" className="text-center">No users found</td>
          </tr>
        )}
        </tbody>

      </table>

      <div className="d-flex justify-content-center">
        <Pagination>
          {[...Array(users.totalPages)].map((_, index) => (
            <Pagination.Item
              key={index}
              active={index === users.pageNumber}
              onClick={() => handlePageChange(index)}
            >
              {index + 1}
            </Pagination.Item>
          ))}
        </Pagination>
      </div>

    </div>
  )
}


export default Home

