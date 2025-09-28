import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";  // 👈 import CSS

export default function Navbar() {
  return (
    <nav className="navbar">
      {/* Logo */}
      <div className="navbar-logo">🏠 Carveo Management Portal</div>

      {/* Links */}
      <ul className="navbar-links">
        <li><Link to="/">Dashboard</Link></li>
        <li><Link to="/residents">Residents</Link></li>
        <li><Link to="/vehicles">Vehicles</Link></li>
        <li><Link to="/visitors">Visitors</Link></li>
      </ul>
    </nav>
  );
}
