import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-left">
        <Link to="/" className="brand">
          🚗Vehicle Management System
        </Link>
      </div>
      <div className="navbar-right">
        <Link to="/resident" className="nav-link">Resident</Link>
        <Link to="/vehicle" className="nav-link">Vehicle</Link>
        <Link to="/visitor" className="nav-link">Visitor</Link>
      </div>
    </nav>
  );
};

export default Navbar;
