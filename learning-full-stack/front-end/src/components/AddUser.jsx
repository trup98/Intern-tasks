import React, {useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import 'react-datepicker/dist/react-datepicker.css';
import {Col, Container, Form, FormCheck, FormControl, FormGroup, Row} from "react-bootstrap";
import DatePicker from "react-datepicker";
import Select from "react-select";
import {addUser} from "../service/user-service";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';


function AddUser({isOpen, toggle}) {

  const [userName, setUserName] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [gender, setGender] = useState('male');
  const [address, setAddress] = useState('');
  const [dob, setDob] = useState(null);
  const [hobbyOptions] = useState([
    {value: '2', label: 'Cricket'},
    {value: '5', label: 'Gym'},
    {value: '3', label: 'Hockey'},
    {value: '1', label: 'Football'},
    {value: '4', label: 'Basketball'},
  ]);

  const [selectedHobby, setSelectedHobby] = useState([]);

  const handleDateChange = (date) => {
    setDob(date)
  }

  const handleHobbyChange = (selectedOption) => {
    setSelectedHobby(selectedOption)
  }

  const handleGenderChange = (e) => {
    setGender(e.target.value);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const hobbyIds = selectedHobby.map(option => option.value);
    const userData = {
      userName,
      firstName,
      lastName,
      address,
      email,
      gender,
      dob,
      hobbyIdList: hobbyIds
    }
    addUser(userData);
    toast.success('User added successfully.');
    console.log(userData)
  }




  return (
    <div>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}> Add User</ModalHeader>
        <ModalBody>
          <Container>
            <Row>
              <Col>
                <Form onSubmit={handleSubmit}>
                  <Form.Group controlId="userName">
                    <Form.Label>User Name</Form.Label>
                    <FormControl type="text" value={userName} onChange={e => setUserName(e.target.value)}
                                 placeholder="Enter User Name" required/>
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
                  <Form.Group controlId="email">
                    <Form.Label>Email</Form.Label>
                    <FormControl type="text" value={email} onChange={e => setEmail(e.target.value)}
                                 placeholder="Enter Email Id" required/>
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
                    <Form.Label>Date of Birth</Form.Label>
                    <DatePicker selected={dob} onChange={handleDateChange} placeholderText="Date of Birth"
                                showYearDropdown dateFormat="yyyy-MM-dd"/>
                  </Form.Group>
                  <Form.Group controlId="hobbies">
                    <Form.Label>Hobbies</Form.Label>
                    <Select options={hobbyOptions} value={selectedHobby} onChange={handleHobbyChange} isMulti/>
                  </Form.Group>

                </Form>
              </Col>
            </Row>
          </Container>
        </ModalBody>
        <ModalFooter>
          <Button color="outline-primary" onClick={handleSubmit}>
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
