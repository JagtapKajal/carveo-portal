import React from "react";
import { NavLink } from "react-router-dom";
import { Home, Users, Car, User } from "lucide-react";
import "./Sidebar.css";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <h2 className="sidebar-title">Carveo Portal</h2>
      <nav>
        <NavLink to="/" end className="nav-link">
          <Home size={18} /> Dashboard
        </NavLink>
        <NavLink to="/residents" className="nav-link">
          <Users size={18} /> Residents
        </NavLink>
        <NavLink to="/vehicles" className="nav-link">
          <Car size={18} /> Vehicles
        </NavLink>
        <NavLink to="/visitors" className="nav-link">
          <User size={18} /> Visitors
        </NavLink>
      </nav>
    </div>
  );
};

export default Sidebar;
