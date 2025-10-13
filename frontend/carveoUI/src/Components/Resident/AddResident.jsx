import React, { useState } from "react";
import "./Resident.css";

const AddResident = ({onClose}) => {
  const [formData, setFormData] = useState({
    fname: "",
    lname: "",
    flatno: "",
    mobileno: "",
    email: "",
    residenttype: "OWNER",
    noofvehicles: "",
    parkingslot: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch("http://localhost:8080/residents/AddResident", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((res) => {
        if (res.ok) {
          alert("Resident added successfully!");
          onClose();
        } else {
          alert("Failed to add resident.");
        }
      })
      .catch((err) => console.error("Error:", err));
  };

  return (
    <div className="form-overlay">
      <form className="resident-form" onSubmit={handleSubmit}>
        <h3>Add New Resident</h3>

        <label>First Name</label>
        <input name="fname" value={formData.fname} onChange={handleChange} />

        <label>Last Name</label>
        <input name="lname" value={formData.lname} onChange={handleChange} />

        <label>Flat No</label>
        <input name="flatno" value={formData.flatno} onChange={handleChange} />

        <label>Mobile No</label>
        <input name="mobileno" value={formData.mobileno} onChange={handleChange} />

        <label>Email</label>
        <input name="email" value={formData.email} onChange={handleChange} />

        <label>Resident Type</label>
        <select name="residenttype" value={formData.residenttype} onChange={handleChange}>
          <option value="OWNER">OWNER</option>
          <option value="TENANT">TENANT</option>
        </select>

        <label>No of Vehicles</label>
        <input name="noofvehicles" value={formData.noofvehicles} onChange={handleChange} />

        <label>Parking Slot</label>
        <input name="parkingslot" value={formData.parkingslot} onChange={handleChange} />

        <div className="form-buttons">
          <button type="submit" className="add-btn">Add</button>
          <button type="button" onClick={onClose} className="cancel-btn">Cancel</button>
        </div>
      </form>
    </div>
  );
};

export default AddResident;
