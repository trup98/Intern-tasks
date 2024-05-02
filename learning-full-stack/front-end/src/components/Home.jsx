import React, {useEffect} from 'react'
import {toast, ToastContainer, Zoom} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {handleToggle, loadUserService} from "../service/user-service";

function Home() {

  const [users, setUser] = React.useState([]);
  const [value, setValues] = React.useState("");

  useEffect(() => {
      callAllUsers();
  }, [value]);


  const callAllUsers = () => {
    loadUserService(value)
      .then(resp => {
        console.log(resp);
        setUser(resp.data.data.content);
      });

  }

  const handleToggleFunctionality = (user) => {
    handleToggle(user.id, user.status)
      .then(resp => {
        console.log(resp);
        callAllUsers();
        notifyUserStatus();
      })
      .catch(erro => {
        console.log(erro)
      })
      .finally(resp => {
        console.log("all time print::: ");
      });
  }

  const handleReset = () =>{
    console.log("functional calll")
    callAllUsers()
  }


  const notifyUserFound = () => toast.success("User Found!!", {
    position: "top-right",
    autoClose: 5000,
    transition: Zoom
  });

  const notifyUserStatus = () => toast.warn("User Status Changed", {
    position: "top-right",
    autoClose: 5000,
    transition: Zoom

  })

  return (
    <div>
      <div className="container m-3">
        <div className="container-fluid">
          <div className="d-flex" onSubmit={callAllUsers}>
            <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" value={value}
                   onChange={(e) => {
                     setValues(e.target.value);
                     callAllUsers(e.target.value);
                   }}/>
            <button className="btn btn-outline-success" type="submit" onClick={notifyUserFound}>Search</button>
            <button className="btn btn-outline-danger mx-2" type="submit"
                    onClick={handleReset}>Reset
            </button>
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
        {users.length > 0 ? (
          users.map((user, id) => (
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
    </div>
  )
}


export default Home

