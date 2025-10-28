// ResidentList.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ResidentList.css";

const ResidentList = () => {
  const [residents, setResidents] = useState([]);
  const [search, setSearch] = useState("");
  const [filteredResidents, setFilteredResidents] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchResidents();
  }, []);

  const fetchResidents = async () => {
    try {
      const response = await fetch("http://localhost:8080/residents/getAllResident");
      const data = await response.json();
      setResidents(data);
      setFilteredResidents(data);
    } catch (error) {
      console.error("Error fetching residents:", error);
    }
  };

  const handleSearch = (e) => {
    const value = e.target.value;
    setSearch(value);
    const filtered = residents.filter((r) =>
      (r.fname + " " + r.lname).toLowerCase().includes(value.toLowerCase())
    );
    setFilteredResidents(filtered);
  };

  return (
    <div className="resident-page">
      <button className="back-btn" onClick={() => navigate("/")}>← Back</button>
      <h2>Resident List</h2>

      <input
        type="text"
        placeholder="Search by name..."
        value={search}
        onChange={handleSearch}
        className="search-input"
      />

      {filteredResidents.length > 0 ? (
        <table className="resident-table">
          <thead>
            <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Flat Number</th>
            </tr>
          </thead>
          <tbody>
            {filteredResidents.map((r, index) => (
              <tr key={index}>
                <td>{r.fname}</td>
                <td>{r.lname}</td>
                <td>{r.flatNo || "N/A"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No residents found.</p>
      )}
    </div>
  );
};

export default ResidentList;
