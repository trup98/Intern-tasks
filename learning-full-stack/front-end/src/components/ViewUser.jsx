import React from 'react';
import {Modal, ModalBody, ModalHeader} from "reactstrap";
import {Col, Container, Row} from "react-bootstrap";


function ViewUser({isOpen, toggle,user}) {

  const userId = user.id;
  console.log("user View User", userId);



  return (
    <div>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}> View For User Id : {user.id} </ModalHeader>
        <ModalBody>
          <Container>
            <Row>
              <Col>

                User Name : {user.userName}<br/>
                User Email : {user.email}<br/>
                First Name : {user.firstName} <br/>
                Last Name : {user.lastName} <br/>
                Hobby : {user.hobbyNames}<br/>
                Gender : {user.gender}<br/>
                Date Of Birth : {user.dob}

              </Col>
            </Row>
          </Container>
        </ModalBody>

      </Modal>
    </div>
);
}

export default ViewUser;
