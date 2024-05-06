import React from 'react';
import {Button, Modal, ModalFooter, ModalHeader} from "reactstrap";
import {deleteUser} from "../service/user-service";
import {toast, Zoom} from "react-toastify";

function DeleteUser({isOpen, toggle, user}) {
  const deleteUserId = user.id;

  const handleSubmit = () => {
    deleteUser(deleteUserId).then(resp => {
      if (resp.status === 200) {
        toast.success(resp.data.message,{
          transition: Zoom
        });
        toggle();
      }
    }).catch(error => {
      toast.error(error.response.data.message,{
        transition: Zoom
      })
    })
  }


  return (
    <div>
      <Modal isOpen={isOpen} toggle={toggle}>
        <ModalHeader>Are You Sure.?</ModalHeader>
        <ModalFooter>
          <Button color="outline-primary" onClick={handleSubmit}>
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

export default DeleteUser;
