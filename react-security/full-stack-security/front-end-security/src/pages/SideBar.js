import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import MenuIcon from '@mui/icons-material/Menu';
import HomeIcon from '@mui/icons-material/Home';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import {useNavigate} from "react-router-dom";

function SideBar() {

  const navigate = useNavigate();

  const [state, setState] = React.useState({
    top: false,
    left: false,
    bottom: false,
    right: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setState({...state, [anchor]: open});
  };

  const handleRole = () => {
    navigate("/role")
  }

  const handleUser = () => {
    navigate("/user")
  }


  const list = (anchor) => (
    <Box
      sx={{width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 250}}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        <ListItem disablePadding>
          <ListItemIcon>
            <HomeIcon onClick={() => handleUser()} className="pe-auto"/>
          </ListItemIcon>
          <ListItemText primary={"User"} onClick={() => handleUser()} className="pe-auto"/>
        </ListItem>
        <ListItem disablePadding>
          <ListItemIcon>
            <AdminPanelSettingsIcon onClick={() => handleRole()} className="pe-auto"/>
          </ListItemIcon>
          <ListItemText primary={"Role"} onClick={() => handleRole()} className="pe-auto"/>
        </ListItem>
      </List>
      <Divider/>
      <List>

      </List>
    </Box>
  );
  return (
    <div>
      <MenuIcon onClick={toggleDrawer("left", true)}/>
      <Drawer
        anchor={"left"}
        open={state["left"]}
        onClose={toggleDrawer("left", false)}>
        {list("left")}
      </Drawer>
    </div>
  );
}

export default SideBar;
