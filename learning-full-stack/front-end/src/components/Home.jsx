import React, {useEffect} from 'react'
import {toast, ToastContainer, Zoom} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {handleToggle, loadUserService} from "../service/user-service";
import {Pagination} from "react-bootstrap";
import EditIcon from '@mui/icons-material/Edit';
import InfoIcon from '@mui/icons-material/Info';
import DeleteIcon from '@mui/icons-material/Delete';
import ViewUser from "./ViewUser";
import EditUser from "./EditUser";
import DeleteUser from "./DeleteUser";


function Home() {
  const [modelOpen, setModelOpen] = React.useState(false);
  const toggleModel = () => setModelOpen(!modelOpen);

  const [selectedUser, setSelectedUser] = React.useState(null)
  const [viewUserOpen, setViewUserOpen] = React.useState(false);
  const [editUserOpen, setEditUserOpen] = React.useState(false);
  const [deleteUserOpen, setDeleteUserOpen] = React.useState(false);

  const handleViewUser = (users) => {
    setSelectedUser(users)
    setViewUserOpen(true);
    setEditUserOpen(false); // Ensure Edit User is closed
    toast.success("User Found successfully!",{
      transition: Zoom
    })
  }

  const handleUpdateUser = (users) => {
    setSelectedUser(users)
    setEditUserOpen(true);
    setViewUserOpen(false);
  }

  const handleDeleteUser = (users) => {
    setSelectedUser(users)
    setDeleteUserOpen(true);
    setEditUserOpen(false);
    setViewUserOpen(false);
  }

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
            <ToastContainer theme="colored"/>
          </div>
        </div>
      </div>

      <table className="table table-striped border ">

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
              <th scope="row">{id+1}</th>
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
              <td>
                <div className="mx-2 ">

                  <div className="button-wrapper mx-2 d-inline-block" onClick={() => handleUpdateUser(user)}>
                    <EditIcon color="action"/></div>
                  <div className="button-wrapper mx-2 d-inline-block" onClick={() => handleViewUser(user)}>
                    <InfoIcon color="info"/>
                  </div>
                  <div className="button-wrapper mx-2 d-inline-block" onClick={() => handleDeleteUser(user)}>
                    <DeleteIcon color="warning"/>
                  </div>
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

      {
        viewUserOpen && (
          <ViewUser isOpen={true} toggle={() => setViewUserOpen(false)} user={selectedUser}/>
        )
      }


      {
        editUserOpen && (
          <EditUser isOpen={true} toggle={() => setEditUserOpen(false)} user={selectedUser}/>
        )
      }
      {
        deleteUserOpen && (
          <DeleteUser isOpen={true} toggle={() => setDeleteUserOpen(false)} user={selectedUser}/>
        )
      }

    </div>
  )
}


export default Home

