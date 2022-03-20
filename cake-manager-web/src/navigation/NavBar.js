import React from "react";
import { Link } from "react-router-dom";
import { Navbar, NavbarBrand } from "reactstrap";
import Login from "../login/Login";

const NavBar = () => {
  const [userInfo, setUserInfo] = useState(undefined);

  useEffect(async () => {
    const response = await fetch("/user");
    const userResponse = await response.json();
    setUserInfo(userResponse);
  }, []);

  return (
    <Navbar color="dark" dark expand="md">
      <NavbarBrand>
        <Login />
      </NavbarBrand>
      <NavbarBrand tag={Link} to="/">
        Cakes
      </NavbarBrand>
      {userInfo && (
        <NavbarBrand tag={Link} to="/cakes">
          Add Cake
        </NavbarBrand>
      )}
    </Navbar>
  );
};

export default NavBar;
