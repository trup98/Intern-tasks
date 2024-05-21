import {Button, Modal, ModalFooter, ModalHeader} from "reactstrap";
import {deleteUser} from "../../service/user-service";
import {toast, Zoom} from "react-toastify";
import {addNewRole, deleteRole} from "../../service/role-service";

export const DeleteRole = ({isOpen, toggle, role}) => {

  const roleId = role.id;
  const handleSubmit = async () => {
    try {

      const response = await deleteRole(roleId);

      if (response.status === 200) {
        toast.success(response.data.message, {
          transition: Zoom
        })
        toggle();
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
        toast.error('Error Deleting Role! ' + error.message, {
          transition: Zoom
        });
      }
    }
  }


  return (
    <>
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
    </>
  )
}
