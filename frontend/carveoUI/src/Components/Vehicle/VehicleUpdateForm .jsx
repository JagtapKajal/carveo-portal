import React, { useState, useEffect } from "react";
import "./Vehicle.css"; // Reuse your existing CSS

const VehicleUpdateForm = ({ vehicle, onClose, onUpdated, residentsList }) => {
  // Initialize formData with the vehicle passed from parent
  const [formData, setFormData] = useState({ ...vehicle });
  const [residents, setResidents] = useState(residentsList || []);

  // Fetch residents if not passed from parent
  useEffect(() => {
    if (!residentsList || residentsList.length === 0) {
      fetch("http://localhost:8080/residents/getAllResidents")
        .then((res) => {
          if (!res.ok) throw new Error("Failed to fetch residents");
          return res.json();
        })
        .then((data) => setResidents(data))
        .catch((err) => console.error("Error fetching residents:", err));
    }
  }, [residentsList]);

  // Handle input changes
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  // Handle form submit
  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`http://localhost:8080/vehicles/UpdateVehicleById/${formData.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    })
      .then((res) => {
        if (res.ok) {
          alert("Vehicle updated successfully");
          if (onUpdated) onUpdated(); // Refresh table in parent
          onClose(); // Close modal
        } else {
          alert("Update failed");
        }
      })
      .catch((err) => console.error("Error updating vehicle:", err));
  };

  if (!formData) return <p>Loading vehicle data...</p>;

  return (
    <div className="form-overlay">
      <form className="vehicle-form" onSubmit={handleSubmit}>
        <h3>Update Vehicle</h3>

        <label>Registration Number</label>
        <input
          name="registrationnumber"
          value={formData.registrationnumber}
          onChange={handleChange}
          required
        />

        <label>Vehicle Name</label>
        <input
          name="vname"
          value={formData.vname}
          onChange={handleChange}
          required
        />

        <label>Color</label>
        <input
          name="color"
          value={formData.color || ""}
          onChange={handleChange}
        />

        <label>Type</label>
        <select name="type" value={formData.type} onChange={handleChange} required>
          <option value="CAR">CAR</option>
          <option value="BIKE">BIKE</option>
          <option value="MOPED">MOPED</option>
        </select>

        <label>Status</label>
        <input
          type="checkbox"
          name="isvehicleactive"
          checked={formData.isvehicleactive}
          onChange={handleChange}
        />

        <label>Resident</label>
        <select
          name="resident"
          value={formData.resident?.id || ""}
          onChange={(e) =>
            setFormData({
              ...formData,
              resident: { id: parseInt(e.target.value) },
            })
          }
          required
        >
          <option value="">Select Resident</option>
          {residents.map((res) => (
            <option key={res.id} value={res.id}>
              {res.fname} {res.lname}
            </option>
          ))}
        </select>

        <div className="form-buttons">
          <button type="submit">Update</button>
          <button type="button" onClick={onClose}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default VehicleUpdateForm;
