import React, { useState } from "react";
import axios from "axios";
import "./addVehicle.css";

const Vehicle = () => {
  const [vehicle, setVehicle] = useState({
    registrationnumber: "",
    vname: "",
    color: "",
    type: "",
    intime: "",
    outtime: "",
    isvehicleactive: true,
    resident: { id: "" }, // foreign key to Resident
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setVehicle({
      ...vehicle,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleResidentChange = (e) => {
    setVehicle({
      ...vehicle,
      resident: { id: Number(e.target.value) }, // ✅ convert to number
    });
  };

  // helper: format datetime-local into backend-friendly format
  const formatDateTime = (dt) => {
    if (!dt) return null;
    return dt.length === 16 ? dt + ":00" : dt; // "yyyy-MM-ddTHH:mm:ss"
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      ...vehicle,
      intime: formatDateTime(vehicle.intime),
      outtime: formatDateTime(vehicle.outtime),
    };

    try {
      await axios.post("http://localhost:8080/vehicles/createVehicle", payload);
      alert("✅ Vehicle Added Successfully!");
    } catch (error) {
      console.error(error.response?.data || error.message);
      alert("❌ Error adding vehicle: " + (error.response?.data || error.message));
    }
  };

  return (
    <div className="vehicle-container">
      <form className="vehicle-form" onSubmit={handleSubmit}>
        <h2>Add Vehicle</h2>

        <input
          type="text"
          name="registrationnumber"
          placeholder="Registration Number"
          value={vehicle.registrationnumber}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="vname"
          placeholder="Vehicle Name"
          value={vehicle.vname}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="color"
          placeholder="Color"
          value={vehicle.color}
          onChange={handleChange}
        />

        <select name="type" value={vehicle.type} onChange={handleChange} required>
          <option value="">-- Select Vehicle Type --</option>
          <option value="CAR">Car</option>
          <option value="BIKE">Bike</option>
          <option value="MOPED">Moped</option>
        </select>

        <input
          type="datetime-local"
          name="intime"
          value={vehicle.intime}
          onChange={handleChange}
        />
        <input
          type="datetime-local"
          name="outtime"
          value={vehicle.outtime}
          onChange={handleChange}
        />

        <label>
          Active Vehicle:
          <input
            type="checkbox"
            name="isvehicleactive"
            checked={vehicle.isvehicleactive}
            onChange={handleChange}
          />
        </label>

        <input
          type="number"
          placeholder="Resident ID"
          value={vehicle.resident.id}
          onChange={handleResidentChange}
          required
        />

        <div className="vehicle-form-buttons">
          <button type="submit">Submit</button>
          <button type="button" className="cancel-btn">
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default Vehicle;
