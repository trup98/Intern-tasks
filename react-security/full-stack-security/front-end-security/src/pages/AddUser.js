import {useState} from "react";
import {Col, Container, Form, FormControl, Row} from "react-bootstrap";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import {Button} from "reactstrap";
import Select from "react-select";
import {userSchema} from "../utils/UserValidation";
import {toast, Zoom} from "react-toastify";
import {addUser} from "../service/user-service";
import {useNavigate} from "react-router-dom";

export const AddUser = () => {

  const navigate = useNavigate();

  const [userName, setUserName] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
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
    navigate("/user")
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = {
      email,
      password,
      userName,
      firstName,
      lastName,
      dob,
      address,
      gender,
      rolesId

    }
    try {
      await userSchema.validate(userData, {abortEarly: false});

      const response = await addUser(userData);

      if (response.status === 200) {
        toast.success(response.data.message, {
          transition: Zoom
        });
      } else {
        toast.error(response.data.message, {
          transition: Zoom
        });
      }
    } catch (error) {
      if (error.response && error.response.data) {
        // Show server error message
        toast.error(error.response.data.message, {
          transition: Zoom
        });
      } else {
        // Show general error message
        toast.error('Error adding User ! ' + error.message, {
          transition: Zoom
        });
      }
    }
  };

  return (
    <>
      <h3 className="text-center">Add User</h3>
      <Container>
        <Row>
          <Col>
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="email">
                <Form.Label>Email</Form.Label>
                <Form.Control type="text" value={email} onChange={(e) => setEmail(e.target.value)}
                              placeholder="Enter Email Name" required/>
              </Form.Group>

              <Form.Group controlId="password">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" value={password} onChange={(e) => setPassword(e.target.value)}
                              placeholder="Enter Password" required/>
              </Form.Group>

              <Form.Group controlId="firstName">
                <Form.Label>First Name</Form.Label>
                <FormControl type="text" value={firstName} onChange={e => setFirstName(e.target.value)}
                             placeholder="Enter First Name" required/>
              </Form.Group>

              <Form.Group controlId="lastName">
                <Form.Label>Last Name</Form.Label>
                <FormControl type="text" value={lastName} onChange={e => setLastName(e.target.value)}
                             placeholder="Enter Last Name" required/>
              </Form.Group>

              <Form.Group controlId="userName">
                <Form.Label>User Name</Form.Label>
                <FormControl type="text" value={userName} onChange={e => setUserName(e.target.value)}
                             placeholder="Enter User Name" required/>
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
                              placeholder="Address"/>
              </Form.Group>

              <Form.Group controlId="dob">
                <Form.Label>Date of Birth : </Form.Label>
                <DatePicker selected={dob} onChange={handleDateChange} placeholderText=" Choose Date of Birth"
                            showYearDropdown dateFormat="yyyy-MM-dd" yearDropdownItemNumber={10}/>
              </Form.Group>

              <Form.Group controlId="hobbies">
                <Form.Label>Role</Form.Label>
                <Select options={role} value={selectedRole} onChange={setSelectedRole}/>
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
