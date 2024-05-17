import {useState} from "react";
import {toast, Zoom} from "react-toastify";
import {login} from "../service/user-service";
import {doLogin} from "../service/auth/AuthTokenProvider";
import {useNavigate} from "react-router-dom";

export const LoginPage = () => {

  const [loginDetail, setLoginDetail] = useState({
    email: '',
    password: ''
  });

  const navigate = useNavigate();


  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(loginDetail);

    // validation
    if (loginDetail.email.trim() == "" || loginDetail.password.trim() == "") {
      toast.error("Email and Password is required!!!");
    }

    try {
      //   calling the server and getting the token
      login(loginDetail).then((response) => {
        if (response.status == 200) {
          // save token in cookies
          doLogin(response);
          navigate("/user")
          toast.success("Login successfully!", {
            transition: Zoom
          });
        } else {
          toast.error(response.data.message)
        }
      }).catch(error => {
        toast.error("Invalid email or password")
      })
    } catch (error) {
      if (error.inner && error.inner.length > 0) {
        error.inner.forEach(error => {
          toast.error(`${error.message}`, {
            transition: Zoom
          })
        })
      } else {
        toast.error("Invalid email or password" + error.message, {
          transition: Zoom
        })
      }
    }


  }
  return (
    <>


      <div className="container">
        <div className="row">
          <div className="col-md-6 offset-md-3">
            <h2 className="text-center text-dark mt-5">Login Form</h2>

            <div className="card my-5">

              <form className="card-body cardbody-color p-lg-5" onSubmit={handleSubmit}>

                <div className="text-center">
                  <img src="https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397__340.png"
                       className="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
                       width="200px" alt="profile"/>
                </div>

                <div className="mb-3">
                  <input type="email" className="form-control" id="userEmail"
                         placeholder="Email" value={loginDetail.email}
                         onChange={(e) => setLoginDetail({...loginDetail, email: e.target.value})}/>
                </div>
                <div className="mb-3">
                  <input type="password" className="form-control" id="password" placeholder="password"
                         value={loginDetail.password}
                         onChange={(e) => setLoginDetail({...loginDetail, password: e.target.value})}/>
                </div>
                <div className="text-center">
                  <button type="submit" className="btn btn-outline-primary px-5 mb-5 w-100">Login</button>
                </div>
              </form>
            </div>

          </div>
        </div>
      </div>

    </>
  )
}

