import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Resident from "./Components/addResident/Resident";
import Visitor from "./Components/addVisitor/Visitor";
import Vehicle from "./Components/addVehicle/Vehicle";
import Navbar from "./Components/Navbar";  // 👈 create
import Dashboard from "./Components/Dashboard"; 
function App() {
  return (
    <Router>
      {/* Navbar stays at top */}
      <Navbar />

      {/* Page container */}
      <div className="p-6">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/residents" element={<Resident />} />
          <Route path="/vehicles" element={<Vehicle />} />
          <Route path="/visitors" element={<Visitor />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
