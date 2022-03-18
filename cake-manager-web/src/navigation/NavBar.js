import React from "react";
import { Link } from "react-router-dom";
import { Navbar, NavbarBrand } from "reactstrap";

const NavBar = () => {
  return (
    <Navbar color="dark" dark expand="md">
      <NavbarBrand tag={Link} to="/">
        Cakes
      </NavbarBrand>
      <NavbarBrand tag={Link} to="/addCake">
        Add Cake
      </NavbarBrand>
    </Navbar>
  );
};

export default NavBar;
