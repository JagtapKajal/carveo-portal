import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./Components/navbar/Navbar";
import Dashboard from "./Components/dashboard/Dashboard";
import Resident from "./Components/Resident/Resident";
import Vehicle from "./Components/Vehicle/Vehicle";
import Visitor from "./Components/Visitor/Visitor";
import Sidebar from './sidebar/Sidebar';
import ResidentList from "./Components/Resident/ResidentList";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/sidebar" element={<Sidebar />} />
            <Route path="/resident" element={<Resident />} />
            <Route path="/vehicle" element={<Vehicle />} />
            <Route path="/visitor" element={<Visitor />} />
            <Route path="/resident" element={<ResidentList />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
