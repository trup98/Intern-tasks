import React, {useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import {userSchema} from "../utils/UserValidation";
import {toast, Zoom} from "react-toastify";
import {loadUserService, updateUser} from "../service/user-service";
import {Col, Container, Form, FormControl, Row} from "react-bootstrap";
import DatePicker from "react-datepicker";
import Select from "react-select";

function EditUser({isOpen, toggle, user}) {
  const userId = user.id;
  console.log("user Edit", userId);

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

  const hobbyIds = selectedHobby.map(option => option.value);
  const handleSubmitUser = async (e) => {
    e.preventDefault();

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

    console.log(userData)
    try {


      await userSchema.validate(userData, {abortEarly: false})

      updateUser(userId, userData)
        .then(resp => {
          if (resp.status === 200) {
            toast.success(resp.data.message, {
              transition: Zoom
            });
            toggle();

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
    <div>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}> Update User</ModalHeader>
        <ModalBody>
          <Container>
            <Row>
              <Col>
                <Form onSubmit={handleSubmitUser}>
                  <Form.Group controlId="userName">
                    <Form.Label>User Name</Form.Label>
                    <FormControl type="text" value={userName} onChange={e => setUserName(e.target.value)}
                                 placeholder={user.userName} required/>
                  </Form.Group>
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
                  <Form.Group controlId="email">
                    <Form.Label>Email</Form.Label>
                    <FormControl type="text" value={email} onChange={e => setEmail(e.target.value)}
                                 placeholder={user.email} required/>
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
                    <Form.Label>Date of Birth </Form.Label>
                    <DatePicker selected={dob} onChange={handleDateChange} placeholderText={user.dob}
                                showYearDropdown dateFormat="yyyy-MM-dd" yearDropdownItemNumber={10}/>
                  </Form.Group>
                  <Form.Group controlId="hobbies">
                    <Form.Label>Hobbies</Form.Label>
                    <Select options={hobbyOptions} value={selectedHobby} onChange={handleHobbyChange} isMulti
                            placeholder={user.hobbyNames}/>
                  </Form.Group>

                </Form>
              </Col>
            </Row>
          </Container>
        </ModalBody>
        <ModalFooter>
          <Button color="outline-primary" onClick={handleSubmitUser}>
            Submit
          </Button>
          <Button color="outline-danger" onClick={toggle}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );

}

export default EditUser;
