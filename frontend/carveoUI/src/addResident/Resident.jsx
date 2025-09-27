import React, { useState } from "react";
import "./addResident.css";

function Resident() {
  const [formData, setFormData] = useState({
    fname: "",
    lname: "",
    email: "",
    mobileno: "",
    flatno: "",
    residenttype: "",
    parkingslot: "",
    noofvehicles: ""
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      ...formData,
      mobileno: Number(formData.mobileno),
      noofvehicles: Number(formData.noofvehicles || 0), // convert to number
      residenttype: formData.residenttype.toUpperCase(), // match Enum in backend
      vehicleList: [], // backend expects vehicle list
    };

    try {
      const response = await fetch(
        "http://localhost:8080/residents/saveResident",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        }
      );

      if (response.ok) {
        setMessage("✅ Resident saved successfully!");
        setFormData({
          fname: "",
          lname: "",
          email: "",
          mobileno: "",
          flatno: "",
          residenttype: "",
          parkingslot: "",
          noofvehicles: ""
        });
      } else {
        const errorText = await response.text();
        setMessage(`❌ Failed: ${errorText}`);
      }
    } catch (error) {
      setMessage("⚠️ Error connecting to server!");
    }
  };

  const handleCancel = () => {
    setFormData({
      fname: "",
      lname: "",
      email: "",
      mobileno: "",
      flatno: "",
      residenttype: "",
      parkingslot: "",
      noofvehicles: ""
    });
    setMessage("");
  };

  return (
    <div className="resident-container">
      <h2>Add Resident</h2>
      <form className="resident-form" onSubmit={handleSubmit}>
        <input
          type="text"
          name="fname"
          placeholder="First Name"
          value={formData.fname}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="lname"
          placeholder="Last Name"
          value={formData.lname}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="mobileno"
          placeholder="Mobile Number"
          value={formData.mobileno}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="flatno"
          placeholder="Flat No."
          value={formData.flatno}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="parkingslot"
          placeholder="parkingslot"
          value={formData.parkingslot}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="noofvehicles"
          placeholder="noofvehicles"
          value={formData.noofvehicles}
          onChange={handleChange}
          required
        />

        <select
          name="residenttype"
          value={formData.residenttype}
          onChange={handleChange}
          required
        >
          <option value="">Select Resident Type</option>
          <option value="OWNER">OWNER</option>
          <option value="TENANT">TENANT</option>
        </select>

        <div className="form-buttons">
          <button type="submit">Submit</button>
          <button type="button" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </form>
      {message && <p className="form-message">{message}</p>}
    </div>
  );
}

export default Resident;
