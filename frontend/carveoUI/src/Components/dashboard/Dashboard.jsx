import React from "react";
import "./Dashboard.css";

const Dashboard = () => {
  return (
    <div className="dashboard-container">
      <h1>Welcome to Carveo Management Dashboard</h1>
      <div className="dashboard-cards">
        <div className="card">
          <h3>Total Residents</h3>
          <p>56</p>
        </div>
        <div className="card">
          <h3>Total Vehicles</h3>
          <p>34</p>
        </div>
        <div className="card">
          <h3>Visitors Today</h3>
          <p>12</p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
