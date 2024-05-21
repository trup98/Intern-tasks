import {Modal, ModalBody, ModalHeader} from "reactstrap";
import {Col, Container, Row} from "react-bootstrap";
import React from "react";

export const ViewRole = ({isOpen,toggle,role}) => {
  return (
    <>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader toggle={toggle}> View For Role Id : {role.id} </ModalHeader>
        <ModalBody>
          <Container>
            <Row>
              <Col>

                  Role Name : {role.role}<br/>

              </Col>
            </Row>
          </Container>
        </ModalBody>

      </Modal>
    </>
  )
}
