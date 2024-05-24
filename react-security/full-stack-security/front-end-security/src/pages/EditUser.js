import {useLocation, useNavigate} from "react-router-dom";
import {useRef, useState} from "react";
import {Col, Container, Form, FormControl, Row} from "react-bootstrap";
import DatePicker from "react-datepicker";
import Select from "react-select";
import {Button} from "reactstrap";
import {userSchema} from "../utils/UserValidation";
import {addUser, updateUser} from "../service/user-service";
import {toast, Zoom} from "react-toastify";

export const EditUser = () => {

  const location = useLocation();
  const user = location.state.user;
  const updateUserId = user.id;

  const navigate = useNavigate();

  const [userName, setUserName] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [dob, setDob] = useState(null);
  const [gender, setGender] = useState('male');
  const [address, setAddress] = useState('');
  const [role] = useState([
    {value: '1', label: 'ROLE_ADMIN'},
    {value: '2', label: 'ROLE_USER'},
    {value: '3', label: 'ROLE_MANAGER'},
  ]);

  const [selectedRole, setSelectedRole] = useState([]);

  const rolesId = selectedRole.value

  const handleDateChange = (date) => {
    setDob(date)
  }

  const handleCancel = () => {
    navigate("/")
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = {

      userName,
      firstName,
      lastName,
      dob,
      address,
      gender,
      rolesId

    }

    try {

      // await userSchema.validate(userData, {abortEarly: false}

      updateUser(updateUserId, userData)
        .then(response => {
          if (response.status == 200) {
            toast.success(response.data.message, {
              transition: Zoom
            });
            navigate("/")
          }
        })
        .catch(error => {
          toast.error(error.response.data.message, {
            transition: Zoom
          })
        })
    } catch (error) {
      if (error.inner && error.inner.length > 0) {
        error.inner.forEach(error => {
          toast.error(`${error.message}`, {
            transition: Zoom
          });
        });
      } else {
        toast.error('Error adding User!' + error.message, {
          transition: Zoom
        });
      }
    }


  }


  return (
    <>
      <h3 className="text-center">Update User Id : {user.id}</h3>
      <Container>
        <Row>
          <Col>
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="firstName">
                <Form.Label>First Name</Form.Label>
                <FormControl type="text" value={firstName} onChange={e => setFirstName(e.target.value)}
                             placeholder={user.firstName} required/>
              </Form.Group>

              <Form.Group controlId="lastName">
                <Form.Label>Last Name</Form.Label>
                <FormControl type="text" value={lastName} onChange={e => setLastName(e.target.value)}
                             placeholder={user.lastName} required/>
              </Form.Group>

              <Form.Group controlId="userName">
                <Form.Label>User Name</Form.Label>
                <FormControl type="text" value={userName} onChange={e => setUserName(e.target.value)}
                             placeholder={user.userName} required/>
              </Form.Group>

              <Form.Group controlId="gender">
                <Form.Label>Gender</Form.Label>
                <div>
                  <Form.Check inline type="radio" name="gender" label="Male" value="male"
                              onChange={() => setGender("male")}/>
                  <Form.Check inline type="radio" name="gender" label="Female" value="female"
                              onChange={() => setGender("female")}/>
                </div>
              </Form.Group>

              <Form.Group controlId="address">
                <Form.Label>Address</Form.Label>
                <Form.Control type="text" value={address} onChange={(e) => setAddress(e.target.value)}
                              placeholder={user.address}/>
              </Form.Group>

              <Form.Group controlId="dob">
                <Form.Label>Date of Birth : </Form.Label>
                <DatePicker selected={dob} onChange={handleDateChange} placeholderText={user.dob}
                            showYearDropdown dateFormat="yyyy-MM-dd" yearDropdownItemNumber={10}/>
              </Form.Group>

              <Form.Group controlId="hobbies">
                <Form.Label>Role</Form.Label>
                <Select options={role} value={selectedRole} onChange={setSelectedRole} placeholder={user.rolesId}/>
              </Form.Group>

            </Form>
            <div className="d-flex justify-content-end mt-4">
              <Button color="outline-primary" onClick={handleSubmit} className="mx-2">
                Submit
              </Button>

              <Button color="outline-danger" onClick={handleCancel} className="mx-2">
                Cancel
              </Button>
            </div>
          </Col>
        </Row>
      </Container>

    </>
  )
}
