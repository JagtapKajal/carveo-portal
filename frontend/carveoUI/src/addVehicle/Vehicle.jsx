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
      resident: { id: e.target.value },
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/vehicles", vehicle);
      alert("Vehicle Added Successfully!");
    } catch (error) {
      console.error(error);
      alert("Error adding vehicle");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
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

      <select name="type" value={vehicle.type} onChange={handleChange}>
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

      <button type="submit">Submit</button>
    </form>
  );
};

export default Vehicle;
