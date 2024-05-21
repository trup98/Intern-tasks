import {Col, Container, Form, Row} from "react-bootstrap";
import {useState} from "react";
import {Button} from "reactstrap";
import {useNavigate} from "react-router-dom";
import {toast, Zoom} from "react-toastify";
import {addNewRole} from "../../service/role-service";

export const AddRole = () => {

  const navigate = useNavigate();

  const [roleName, setRoleName] = useState("");
  const handleCancel = () => {
    navigate("/role")
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = {
      roleName
    };

    try {

      const response = await addNewRole(userData);

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
        toast.error('Error adding Role! ' + error.message, {
          transition: Zoom
        });
      }
    }
  };

  return (
    <>
      <h3 className="text-center">Add Role</h3>
      <Container>
        <Row>
          <Col>
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="roleName">
                <Form.Label>Role Name</Form.Label>
                <Form.Control type="text" value={roleName} onChange={(e) => setRoleName(e.target.value)}
                              placeholder="Use capital Letter ROLE_XYZ" required/>
              </Form.Group>
            </Form>
            <div className="d-flex justify-content-end mt-4">
              <Button color="outline-primary" className="mx-2" onClick={handleSubmit}>
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
