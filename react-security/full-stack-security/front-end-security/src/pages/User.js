import {useEffect, useState} from "react";
import {callAllUser, changeStatus} from "../service/user-service";
import {Pagination} from "react-bootstrap";
import {toast, Zoom} from "react-toastify";
import {Link, useNavigate} from "react-router-dom";
import {Switch} from "@mui/material";
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import EditIcon from '@mui/icons-material/Edit';
import InfoIcon from '@mui/icons-material/Info';
import DeleteIcon from '@mui/icons-material/Delete';
import {ViewUser} from "./modal/ViewUser";
import {DeleteUser} from "./modal/DeleteUser";

export const User = () => {

  const navigate = useNavigate();

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
    // callAllUser();
  }, [currentPage, value]);


  const getAll = (currentPage) => {
    callAllUser(value, value ? 0 : currentPage, users.pageSize)
      .then(response => {
        console.log(response.data.content)
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
    console.log()
    changeStatus(user.id, user.status)
      .then(response => {
        getAll(users.pageNumber)
        toast.success("User changed successfully", {
          transition: Zoom
        })
      })
      .catch(error => {
        console.log(error)
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
          <div className="container-fluid d-flex justify-content-end align-items-center">
            <PersonAddIcon onClick={() => handleAddPage()}/>
          </div>
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
                    <th scope="row">{user.id}</th>
                    <td>{user.userName}</td>
                    <td>{user.email}</td>
                    <td>{user.roleNames}</td>
                    <td>
                      <Switch checked={user.status} onClick={() => handleChangeStatus(user)}/>
                    </td>
                    <td>
                      <div className="mx-2 ">

                        <div className="button-wrapper mx-2 d-inline-block">
                          <EditIcon onClick={() => handleEditUser(user)}/>
                        </div>
                        <div className="button-wrapper mx-2 d-inline-block">
                          <InfoIcon color="action" onClick={() => handleViewUser(user)}/>
                        </div>
                        <div className="button-wrapper mx-2 d-inline-block">
                          <DeleteIcon color="error" onClick={() => handleDeleteUser(user)}/>
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
