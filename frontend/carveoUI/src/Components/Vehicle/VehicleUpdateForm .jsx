import React, { useState, useEffect } from "react";
import "./Vehicle.css";

const VehicleUpdateForm = ({ vehicleData, onUpdate, onCancel }) => {
  const [vehicle, setVehicle] = useState({
    registrationnumber: "",
    vname: "",
    color: "",
    type: "",
    intime: "",
    outtime: "",
    isvehicleactive: true,
    resident: { id: "" },
  });

  useEffect(() => {
    if (vehicleData) setVehicle(vehicleData);
  }, [vehicleData]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setVehicle({ ...vehicle, [name]: value });
  };

  const handleResidentChange = (e) => {
    setVehicle({ ...vehicle, resident: { id: e.target.value } });
  };
const handleSubmit = (e) => {
  e.preventDefault();

  fetch(`http://localhost:8080/vehicles/UpdateVehicleById/${vehicle.id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vehicle),
  })
    .then((res) => {
      if (res.ok) {
        alert("Vehicle updated successfully!");
        onUpdate(); // refresh list
        onClose(); // close modal
      } else {
        alert("Failed to update vehicle.");
      }
    })
    .catch((err) => console.error("Error updating vehicle:", err));
};


  return (
    <div className="form-overlay">
      <form onSubmit={handleSubmit} className="vehicle-form">
        <h3>Update Vehicle</h3>

        <label>Registration Number</label>
        <input
          type="text"
          name="registrationnumber"
          value={vehicle.registrationnumber}
          onChange={handleChange}
        />

        <label>Vehicle Name</label>
        <input
          type="text"
          name="vname"
          value={vehicle.vname}
          onChange={handleChange}
        />

        <label>Color</label>
        <input
          type="text"
          name="color"
          value={vehicle.color}
          onChange={handleChange}
        />

        <label>Type</label>
        <select name="type" value={vehicle.type} onChange={handleChange}>
          <option value="CAR">CAR</option>
          <option value="BIKE">BIKE</option>
        </select>

        <label>In Time</label>
        <input
          type="datetime-local"
          name="intime"
          value={vehicle.intime}
          onChange={handleChange}
        />

        <label>Out Time</label>
        <input
          type="datetime-local"
          name="outtime"
          value={vehicle.outtime}
          onChange={handleChange}
        />

        <label>Active Status</label>
        <select
          name="isvehicleactive"
          value={vehicle.isvehicleactive}
          onChange={(e) =>
            setVehicle({
              ...vehicle,
              isvehicleactive: e.target.value === "true",
            })
          }
        >
          <option value="true">Active</option>
          <option value="false">Inactive</option>
        </select>

        <label>Resident ID</label>
        <input
          type="number"
          name="residentId"
          value={vehicle.resident.id}
          onChange={handleResidentChange}
        />

        <div className="form-buttons">
          <button type="submit" onClick={onUpdate}>Update</button>
          <button type="button" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default VehicleUpdateForm;
