// Dashboard.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

const Dashboard = () => {
  const [counts, setCounts] = useState({ residents: 0, vehicles: 0, visitors: 0 });
  const navigate = useNavigate();

  useEffect(() => {
    fetchCounts();
  }, []);

  const fetchCounts = async () => {
    try {
      const [residentsRes, vehiclesRes, visitorsRes] = await Promise.all([
        fetch("http://localhost:8080/residents/getAllResident"),
        fetch("http://localhost:8080/vehicles/getAllVehicles"),
        fetch("http://localhost:8080/visitors/getAllVisitors"),
      ]);

      const [residents, vehicles, visitors] = await Promise.all([
        residentsRes.json(),
        vehiclesRes.json(),
        visitorsRes.json(),
      ]);

      setCounts({
        residents: residents.length,
        vehicles: vehicles.length,
        visitors: visitors.length,
      });
    } catch (error) {
      console.error("Error fetching counts:", error);
    }
  };

  return (
    <div className="dashboard-container">
      <h1>Carveo Management Dashboard</h1>

      <div className="dashboard-cards">
        <div className="card" onClick={() => navigate("/resident")}>
          <h3>Total Residents</h3>
          <p>{counts.residents}</p>
        </div>

        <div className="card" onClick={() => navigate("/vehicle")}>
          <h3>Total Vehicles</h3>
          <p>{counts.vehicles}</p>
        </div>

        <div className="card" onClick={() => navigate("/Visitor")}>
          <h3>Total Visitors</h3>
          <p>{counts.visitors}</p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
