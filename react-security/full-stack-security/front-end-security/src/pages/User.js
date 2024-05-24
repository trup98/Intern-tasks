import {useEffect, useState} from "react";
import {callAllUser, changeStatus} from "../service/user-service";
import {Pagination} from "react-bootstrap";
import {toast, Zoom} from "react-toastify";
import {useNavigate} from "react-router-dom";
import {Switch} from "@mui/material";
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import EditIcon from '@mui/icons-material/Edit';
import InfoIcon from '@mui/icons-material/Info';
import DeleteIcon from '@mui/icons-material/Delete';
import {ViewUser} from "./modal/ViewUser";
import {DeleteUser} from "./modal/DeleteUser";
import {isRoleAuthenticated} from "../service/auth/CookieStore";

export const User = () => {

  const navigate = useNavigate();

  const adminRole = isRoleAuthenticated();

  const [users, setUser] = useState({
    data: [],
    totalPages: 0,
    pageNumber: 0,
    pageSize: 10
  });
  const [value, setValues] = useState("");
  const [currentPage, setCurrentPage] = useState(0);

  const [selectedUser, setSelectedUser] = useState(null)
  const [viewUserOpen, setViewUserOpen] = useState(false);
  const [deleteUserOpen, setDeleteUserOpen] = useState(false);


  const handleViewUser = (users) => {
    setSelectedUser(users)
    setViewUserOpen(true);
    toast.success("User Found successfully!", {
      transition: Zoom
    })
  }

  const handleDeleteUser = (users) => {
    setSelectedUser(users)
    setDeleteUserOpen(true);
    setViewUserOpen(false);
  }

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };


  useEffect(() => {
    getAll(currentPage)
  }, [currentPage, value]);


  const getAll = (currentPage) => {
    callAllUser(value, value ? 0 : currentPage, users.pageSize)
      .then(response => {
        setUser({
          data: response.data.content,
          totalPages: response.data.totalPages,
          pageNumber: currentPage,
          pageSize: users.pageSize
        });
      })
      .catch(error => {
        if (error.response && error.response.status === 401) {
          toast.error(error.response.data.message, {
            transition: Zoom
          })
        }
      })
  }

  const handleChangeStatus = (user) => {
    changeStatus(user.id, user.status)
      .then(response => {
        getAll(users.pageNumber)
        toast.success("User changed successfully", {
          transition: Zoom
        })
      })
      .catch(error => {
        toast(error.response.data.message, {
          transition: Zoom
        })
      })

  }

  const handleAddPage = () => {
    navigate("/add");
  }

  const handleEditUser = (user) => {
    navigate("/edit", {state: {user}})

  }


  return (
    <>
      <div className="container">
        <div className="py-4">
          {/*add user*/}
          <div className="container-fluid d-flex justify-content-end align-items-center">
            {
              adminRole && <PersonAddIcon role="button" onClick={() => handleAddPage()}/>
            }

          </div>

          {/*search bar*/}
          <div className="d-flex mt-4" onSubmit={() => getAll(currentPage)}>
            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" value={value}
                   onChange={(e) => {
                     setValues(e.target.value);
                   }}/>
            <button className="btn btn-outline-success" type="submit">Search</button>
          </div>

          {/*user table*/}
          <table className="table border table-striped shadow mt-4">
            <thead>
            <tr>
              <th scope="col">id</th>
              <th scope="col">User Name</th>
              <th scope="col">Email</th>
              <th scope="col">Role</th>
              <th scope="col">Status</th>


            </tr>
            </thead>
            <tbody>
            {
              users.data && users.data.length > 0 ? (
                users.data.map((user, id) =>

                  <tr key={id}>
                    <th scope="row">{id + 1}</th>
                    <td>{user.userName}</td>
                    <td>{user.email}</td>
                    <td>{user.roleNames}</td>
                    <td>

                      {
                        adminRole ? <Switch checked={user.status} onClick={() => handleChangeStatus(user)}/> :
                          <Switch disabled style={{cursor: 'not-allowed'}}/>
                      }

                    </td>
                    <td>
                      <div className="mx-2 ">

                        <div className="button-wrapper mx-2 d-inline-block">
                          {
                            adminRole ? <EditIcon role="button" onClick={() => handleEditUser(user)}/> :
                              <EditIcon style={{cursor: 'not-allowed'}} className="disabled"/>
                          }
                        </div>

                        <div className="button-wrapper mx-2 d-inline-block">
                          {
                            adminRole ? <InfoIcon role="button" color="action" onClick={() => handleViewUser(user)}/> :
                              <InfoIcon className="disabled" style={{cursor: 'not-allowed'}} color="action"/>
                          }

                        </div>
                        <div className="button-wrapper mx-2 d-inline-block">

                          {
                            adminRole ? <DeleteIcon color="error" role="button" onClick={() => handleDeleteUser(user)}/>
                              : <DeleteIcon className="disabled" style={{cursor: 'not-allowed'}} color="error"/>
                          }

                        </div>
                      </div>

                    </td>
                  </tr>
                )
              ) : (
                <tr>
                  <td colSpan="5" className="text-center">No users found</td>
                </tr>
              )
            }

            </tbody>
          </table>

          {/*pagination*/}
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
      </div>


      {/*for modal*/}
      {
        viewUserOpen && (
          <ViewUser isOpen={true} toggle={() => setViewUserOpen(false)} user={selectedUser}/>
        )
      }

      {
        deleteUserOpen && (
          <DeleteUser isOpen={true} toggle={() => setDeleteUserOpen(false)} user={selectedUser}/>
        )
      }
    </>
  )
}
