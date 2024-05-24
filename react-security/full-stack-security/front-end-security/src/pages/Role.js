import {useEffect, useState} from "react";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import EditIcon from "@mui/icons-material/Edit";
import InfoIcon from "@mui/icons-material/Info";
import DeleteIcon from "@mui/icons-material/Delete";
import {getRoleAll} from "../service/role-service";
import {toast, Zoom} from "react-toastify";
import {useNavigate} from "react-router-dom";
import {Button} from "reactstrap";
import {DeleteRole} from "./modal/DeleteRole";
import {ViewRole} from "./modal/ViewRole";
import {isRoleAuthenticated} from "../service/auth/CookieStore";

export const Role = () => {

  const navigate = useNavigate();

  const [roles, setRoles] = useState({
    data: []
  });

  const [selectedRole, setSelectedRole] = useState(null)
  const [viewRoleOpen, setViewRoleOpen] = useState(false);
  const [deleteRoleOpen, setDeleteRoleOpen] = useState(false);

  const handleViewRole = (userRole) => {
    setSelectedRole(userRole)
    setViewRoleOpen(true);
    toast.success("Role Found successfully!", {
      transition: Zoom
    })
  }

  const handleDeleteRole = (userRole) => {
    setSelectedRole(userRole)
    setDeleteRoleOpen(true);
    setViewRoleOpen(false);
  }


  useEffect(() => {
    getAllRoles()
  }, []);


  const getAllRoles = () => {
    getRoleAll()
      .then(
        rep => {
          if (rep.status === 200) {
            setRoles({
              data: rep.data.data
            })
          } else {
            toast.error(rep.data.message, {
              transition: Zoom
            })
          }
        }
      ).catch(error => {
      if (error.response && error.response.status === 401) {
        toast.error(error.response.data.message, {
          transition: Zoom
        })
      }
    })
  }

  const handleRole = () => {
    navigate("/role/add")
  }

  const handleCancel = () => {
    navigate("/")
  }

  const handleEditRole = (userRole) => {
    navigate("/editRole", {state: {userRole}})

  }

  const adminRole = isRoleAuthenticated();

  return (

    <>
      <div className="container">
        <div className="py-4">
          <div className="container-fluid d-flex justify-content-end align-items-center">
            {
              adminRole && <PersonAddIcon onClick={() => handleRole()}/>
            }

          </div>
          <table className="table border table-striped shadow mt-4">
            <thead>
            <tr>
              <th scope="col">id</th>
              <th scope="col">Role</th>


            </tr>
            </thead>
            <tbody>
            {
              roles.data && roles.data.length > 0 ? (
                roles.data.map((userRole, id) =>

                  <tr key={id}>

                    <th scope="row">{id + 1}</th>
                    <td>{userRole.role}</td>

                    <td>

                      <div className="mx-2 ">

                        <div className="button-wrapper mx-2 d-inline-block">
                          <EditIcon onClick={() => handleEditRole(userRole)}/>
                        </div>
                        <div className="button-wrapper mx-2 d-inline-block">
                          <InfoIcon color="action" onClick={() => handleViewRole(userRole)}/>
                        </div>
                        <div className="button-wrapper mx-2 d-inline-block">
                          <DeleteIcon color="error" onClick={() => handleDeleteRole(userRole)}/>
                        </div>

                      </div>

                    </td>

                  </tr>
                )
              ) : (
                <tr>
                  <td colSpan="5" className="text-center">No Role found</td>
                </tr>
              )
            }

            </tbody>
          </table>

          <div className="d-flex justify-content-end mt-4">
            <Button color="outline-danger" onClick={handleCancel} className="mx-2">
              Cancel
            </Button>
          </div>

        </div>
      </div>


      {
        viewRoleOpen && (
          <ViewRole isOpen={true} toggle={() => setViewRoleOpen(false)} role={selectedRole}/>
        )
      }

      {
        deleteRoleOpen && (
          <DeleteRole isOpen={true} toggle={() => setDeleteRoleOpen(false)} role={selectedRole}/>
        )
      }

    </>
  )
}
