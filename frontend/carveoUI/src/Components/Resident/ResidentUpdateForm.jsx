// ResidentUpdateForm.jsx
import React, { useState } from "react";
import "./Resident.css";

const ResidentUpdateForm = ({ resident, onClose }) => {
  const [formData, setFormData] = useState({ ...resident });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // backend url to update resident
    fetch(`http://localhost:8080/residents/UpdateResident/${resident.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((res) => {
        if (res.ok) {
          alert("Resident updated successfully");
          onClose(); 
        } else {
          alert("Update failed");
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <div className="form-overlay">

<form className="resident-form" onSubmit={handleSubmit}>
  <h3>Update Resident</h3>

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
    <button type="submit">Update</button>
    <button type="button" onClick={onClose}>Cancel</button>
  </div>
</form>

    </div>
  );
};

export default ResidentUpdateForm;
