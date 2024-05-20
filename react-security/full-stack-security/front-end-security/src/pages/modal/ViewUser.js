import React from 'react';
import {Modal, ModalBody, ModalHeader} from "reactstrap";
import {Col, Container, Row} from "react-bootstrap";


export const ViewUser = ({isOpen, toggle, user}) => {

  const userId = user.id;
  console.log(userId)
  return (
    <>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}> View For User Id : {user.id} </ModalHeader>
        <ModalBody>
          <Container>
            <Row>
              <Col>

                Email : {user.email}<br/>
                User Name : {user.userName}<br/>
                First Name : {user.firstName}<br/>
                Last Name : {user.lastName}<br/>
                Date Of Birth : {user.dateOfBirth}<br/>
                Gender : {user.gender}<br/>
                Role :{user.roleNames}

              </Col>
            </Row>
          </Container>
        </ModalBody>

      </Modal>
    </>
  )
}
