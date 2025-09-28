import React, { useState } from "react";
import "./addVisitor.css";

function Visitor() {
  const [formData, setFormData] = useState({
    visitorname: "",
    vehiclename: "",
    vehicleRegistrationNumber: "",
    visitpurpose: "",
    timein: "",
    timeout: "",
    phonenumber: "",
    isactivevisitor: true,
    visitorType: "",
    residentId: "" // optional link to a resident
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      ...formData,
      phonenumber: Number(formData.phonenumber),
      visitorType: formData.visitorType.toUpperCase(),
      resident: formData.residentId
        ? { id: Number(formData.residentId) }
        : null,
      timein: formData.timein ? new Date(formData.timein).toISOString() : null,
      timeout: formData.timeout ? new Date(formData.timeout).toISOString() : null
    };

    try {
      const response = await fetch(
        "http://localhost:8080/visitors/addVisitor",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload)
        }
      );

      if (response.ok) {
        setMessage("✅ Visitor saved successfully!");
        setFormData({
          visitorname: "",
          vehiclename: "",
          vehicleRegistrationNumber: "",
          visitpurpose: "",
          timein: "",
          timeout: "",
          phonenumber: "",
          isactivevisitor: true,
          visitorType: "",
          residentId: ""
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
      visitorname: "",
      vehiclename: "",
      vehicleRegistrationNumber: "",
      visitpurpose: "",
      timein: "",
      timeout: "",
      phonenumber: "",
      isactivevisitor: true,
      visitorType: "",
      residentId: ""
    });
    setMessage("");
  };

  return (
    <div className="page-wrapper">
    <div className="visitor-container">
      <h2>Add Visitor</h2>
      <form className="visitor-form" onSubmit={handleSubmit}>
        
        <input
          type="text"
          name="visitorname"
          placeholder="Visitor Name"
          value={formData.visitorname}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="vehiclename"
          placeholder="Vehicle Name"
          value={formData.vehiclename}
          onChange={handleChange}
        />
        <input
          type="text"
          name="vehicleRegistrationNumber"
          placeholder="Vehicle Registration No."
          value={formData.vehicleRegistrationNumber}
          onChange={handleChange}
        />
        <input
          type="text"
          name="visitpurpose"
          placeholder="Purpose of Visit"
          value={formData.visitpurpose}
          onChange={handleChange}
          required
        />
        <input
          type="datetime-local"
          name="timein"
          value={formData.timein}
          onChange={handleChange}
          required
        />
        <input
          type="datetime-local"
          name="timeout"
          value={formData.timeout}
          onChange={handleChange}
        />
        <input
          type="text"
          name="phonenumber"
          placeholder="Phone Number"
          value={formData.phonenumber}
          onChange={handleChange}
          required
        />
        <label>
          Active Visitor:
          <input
            type="checkbox"
            name="isactivevisitor"
            checked={formData.isactivevisitor}
            onChange={handleChange}
          />
        </label>
        <select
          name="visitorType"
          value={formData.visitorType}
          onChange={handleChange}
          required
        >
          <option value="">Select Visitor Type</option>
          <option value="GUEST">Guest</option>
          <option value="DELIVERY">Delivery</option>
        </select>
        <input
          type="text"
          name="residentId"
          placeholder="Linked Resident ID (optional)"
          value={formData.residentId}
          onChange={handleChange}
        />

        <div className="form-buttons">
          <button type="submit">Submit</button>
          <button type="button" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </form>
      {message && <p className="form-message">{message}</p>}
    </div>
    </div>
  );
}

export default Visitor;
