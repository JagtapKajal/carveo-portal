import React, { useState, useEffect } from "react";
import "./Vehicle.css";

const VehicleUpdateForm = ({ vehicle, onClose }) => {
  const [formData, setFormData] = useState({
    ...vehicle,
    residentId: vehicle.resident?.id || "",
  });
  const [residents, setResidents] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/residents/getAllResident")
      .then((res) => res.json())
      .then((data) => setResidents(data));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      registrationnumber: formData.registrationnumber,
      vname: formData.vname,
      color: formData.color,
      type: formData.type,
      intime: formData.intime,
      outtime: formData.outtime,
      isvehicleactive:
        formData.isvehicleactive === "true" ||
        formData.isvehicleactive === true,
      resident: {
        id: parseInt(formData.residentId), 
      },
    };

    fetch(`http://localhost:8080/vehicles/UpdateVehicleById/${vehicle.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((res) => {
        if (res.ok) {
          alert("Vehicle updated successfully");
          onClose();
        } else {
          alert("Update failed");
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <div className="form-overlay">
      <form className="vehicle-form" onSubmit={handleSubmit}>
        <h3>Update Vehicle</h3>

        <label>Registration No</label>
        <input
          name="registrationnumber"
          value={formData.registrationnumber}
          onChange={handleChange}
        />

        <label>Name</label>
        <input name="vname" value={formData.vname} onChange={handleChange} />

        <label>Color</label>
        <input name="color" value={formData.color} onChange={handleChange} />

        <label>Type</label>
        <select name="type" value={formData.type} onChange={handleChange}>
          <option value="CAR">CAR</option>
          <option value="BIKE">BIKE</option>
          <option value="MOPED">MOPED</option>
        </select>

        <label>In Time</label>
        <input
          type="datetime-local"
          name="intime"
          value={formData.intime}
          onChange={handleChange}
        />

        <label>Out Time</label>
        <input
          type="datetime-local"
          name="outtime"
          value={formData.outtime}
          onChange={handleChange}
        />

        <label>Status</label>
        <select
          name="isvehicleactive"
          value={formData.isvehicleactive}
          onChange={handleChange}
        >
          <option value={true}>Active</option>
          <option value={false}>Inactive</option>
        </select>

        <label>Resident</label>
        <select
          name="residentId"
          value={formData.residentId}
          onChange={handleChange}
        >
          <option value="">-- Select Resident --</option>
          {residents.map((r) => (
            <option key={r.id} value={r.id}>
              {r.fname} {r.lname}
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
