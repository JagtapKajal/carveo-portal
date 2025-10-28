// ResidentList.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ResidentList.css";

const ResidentList = () => {
  const [residents, setResidents] = useState([]);
  const [search, setSearch] = useState("");
  const [filteredResidents, setFilteredResidents] = useState([]);
  const navigate = useNavigate();

  // Fetch all residents from backend
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

  // Handle Search with Validation
  const handleSearch = (e) => {
    const value = e.target.value.trim();
    setSearch(value);

    if (!value) {
      setFilteredResidents(residents);
      return;
    }

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
              <th>Flat No</th>
              <th>Contact</th>
            </tr>
          </thead>
          <tbody>
            {filteredResidents.map((r, index) => (
              <tr key={index}>
                <td>{r.fname}</td>
                <td>{r.lname}</td>
                <td>{r.flatNo || "N/A"}</td>
                <td>{r.contact || "N/A"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p className="no-result">No residents found for “{search}”.</p>
      )}
    </div>
  );
};

export default ResidentList;
