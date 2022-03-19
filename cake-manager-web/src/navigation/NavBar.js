import React from "react";
import { Link } from "react-router-dom";
import { Navbar, NavbarBrand } from "reactstrap";
import Login from "../login/Login";

const NavBar = () => {
  return (
    <Navbar color="dark" dark expand="md">
      <NavbarBrand>
        <Login />
      </NavbarBrand>
      <NavbarBrand tag={Link} to="/">
        Cakes
      </NavbarBrand>
      <NavbarBrand tag={Link} to="/cakes">
        Add Cake
      </NavbarBrand>
    </Navbar>
  );
};

export default NavBar;
