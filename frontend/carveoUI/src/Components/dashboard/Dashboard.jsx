import React, { useEffect, useState } from "react";
import "./Dashboard.css";

const Dashboard = () => {
  const [counts, setCounts] = useState({
    residents: 0,
    vehicles: 0,
    visitors: 0,
  });

  const [listData, setListData] = useState([]); // store names for selected section
  const [selectedType, setSelectedType] = useState(""); // track which card was clicked

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

  const handleCardClick = async (type) => {
    setSelectedType(type);

    let url = "";
    if (type === "Resident") url = "http://localhost:8080/residents/getAllResident";
    else if (type === "Vehicle") url = "http://localhost:8080/vehicles/getAllVehicles";
    else if (type === "Visitor") url = "http://localhost:8080/visitors/getAllVisitors";

    try {
      const response = await fetch(url);
      const data = await response.json();

      if (type === "Resident") {
        setListData(data.map((r) => r.fname + " " + r.lname));
      } else if (type === "Vehicle") {
        setListData(data.map((v) => v.vehiclename || v.vehicleNo || "Unknown Vehicle"));
      } else if (type === "Visitor") {
        setListData(data.map((v) => v.visitorname || v.name || "Unknown Visitor"));
      }
    } catch (error) {
      console.error("Error fetching list data:", error);
    }
  };

  return (
    <div className="dashboard-container">
      <h1>Carveo Management Dashboard</h1>

      <div className="dashboard-cards">
        <div className="card" onClick={() => handleCardClick("Resident")}>
          <h3>Total Residents</h3>
          <p>{counts.residents}</p>
        </div>

        <div className="card" onClick={() => handleCardClick("Vehicle")}>
          <h3>Total Vehicles</h3>
          <p>{counts.vehicles}</p>
        </div>

        <div className="card" onClick={() => handleCardClick("Visitor")}>
          <h3>Total Visitors</h3>
          <p>{counts.visitors}</p>
        </div>
      </div>

      {/* Dynamic List Display Section */}
      {selectedType && (
        <div className="data-section">
          <h2>{selectedType} List</h2>
          {listData.length > 0 ? (
            <ul className="data-list">
              {listData.map((item, index) => (
                <li key={index}>{item}</li>
              ))}
            </ul>
          ) : (
            <p>No {selectedType.toLowerCase()} data found.</p>
          )}
        </div>
      )}
    </div>
  );
};

export default Dashboard;
