import React from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import 'react-datepicker/dist/react-datepicker.css';
import Select from "react-select";


function AddUser({isOpen, toggle}) {

  const [user, setUser] = React.useState({
    userName: '',
    firstName: '',
    lastName: '',
    email: '',
    gender: "",
    address: '',
    dob: '',
    hoddyIdList: []
  });

  const {userName, firstName, lastName, email, gender, address, dob, hoddyIdList} = user;

  const hobby = [
    {
      value: "2", label: "Cricket"
    },
    {
      value: "3", label: "Hocky"
    },
    {
      value: "4", label: "Basketball"
    },
    {
      value: "5", label: "Gym"
    },
    {
      value: "1", label: "Football"
    }]



  const [selectedHobby, setSelectedHobby] = React.useState([])

  const handleSelectHobby = (selectHobby) =>{
    setSelectedHobby(selectHobby)
  }

  const onInputChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value});
  }


  const submit = ()=>{
    console.log(selectedHobby)
  }



  return (
    <div>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}> Add User</ModalHeader>
        <ModalBody>
          <div className="container">
            <div className="row align-items-center">
              <div className="col">
                <form>
                  <div className="form-group">
                    <div className="md-3">
                      User Name : <input className="form-control mt-2" name="userName" type="text"
                                         placeholder="Enter User name" value={userName}
                                         onInput={(e) => onInputChange(e)}/>
                    </div>
                    <div className="md-3 mt-2">
                      First Name : <input className="form-control mt-2" name="firstName" type="text"
                                          placeholder="Enter First name" value={firstName}
                                          onInput={(e) => onInputChange(e)}/>
                    </div>
                    <div className="md-3 mt-2">
                      Last Name : <input className="form-control mt-2" name="lastName" type="text"
                                         placeholder="Enter Last name" value={lastName}
                                         onInput={(e) => onInputChange(e)}/>
                    </div>
                    <div className="md-3 mt-2">
                      Email Address : <input className="form-control mt-2" name="email" type="text"
                                             placeholder="Enter your Email Id " value={email}
                                             onInput={(e) => onInputChange(e)}/>
                    </div>
                    <div className="md-3 mt-2">
                      <h6>Gender</h6>
                      <div className="form-check">
                        <input className="form-check-input" type="radio" name="gender" value={gender}
                               onChange={onInputChange}
                               id="male"/>
                        <label className="form-check-label" htmlFor="male">
                          Male
                        </label>
                      </div>
                      <div className="form-check">
                        <input className="form-check-input" type="radio" name="gender" value={gender}
                               onChange={onInputChange}
                               id="female"/>
                        <label className="form-check-label" htmlFor="female">
                          Female
                        </label>
                      </div>
                    </div>
                    <div className="md-3 mt-2">
                      Address : <input className="form-control mt-2" name="address" type="text"
                                       placeholder="Enter your Address " value={address}
                                       onInput={(e) => onInputChange(e)}/>
                    </div>
                    <div className="md-3 mt-2">
                      <h6>Date of Birth</h6>
                      <input type="date" onChange={onInputChange}/>
                    </div>
                    <div className="md-3 mt-2">
                      <h6>Hobby</h6>
                      <Select options={hobby} value={selectedHobby} onChange={handleSelectHobby} isMulti={true}/>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </ModalBody>
        <ModalFooter>
          <Button color="outline-primary" onClick={submit} >
            Submit
          </Button>
          <Button color="outline-secondary" onClick={toggle}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );
}

export default AddUser;
