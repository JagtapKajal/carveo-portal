import React, { useState } from "react";
import "./Visitor.css";

const VisitorUpdateForm = ({ visitorData, onUpdated, onCancel }) => {
  const [formData, setFormData] = useState({
    visitorname: visitorData.visitorname || "",
    vehiclename: visitorData.vehiclename || "",
    vehicleRegistrationNumber: visitorData.vehicleRegistrationNumber || "",
    visitpurpose: visitorData.visitpurpose || "",
    timein: visitorData.timein ? visitorData.timein.slice(0, 16) : "",
    timeout: visitorData.timeout ? visitorData.timeout.slice(0, 16) : "",
    phonenumber: visitorData.phonenumber || "",
    isactivevisitor: visitorData.isactivevisitor || false,
    visitorType: visitorData.visitorType || "",
    residentname: visitorData.residentname || "",
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch(
        `https://carveo-portal.onrender.com/visitors/UpdateVisitorById/${visitorData.id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );
      if (res.ok) {
        alert("Visitor updated successfully!");
        onUpdated();
        onCancel();
      } else {
        alert("Failed to update visitor.");
      }
    } catch (error) {
      console.error("Error updating visitor:", error);
      alert("Network error while updating visitor.");
    }
  };

  return (
    <div className="form-overlay">
      <form className="vehicle-form" onSubmit={handleSubmit}>
        <h3>Update Visitor</h3>

        <label>Visitor Name</label>
        <input
          type="text"
          name="visitorname"
          value={formData.visitorname}
          onChange={handleChange}
          required
        />

        <label>Vehicle Name</label>
        <input
          type="text"
          name="vehiclename"
          value={formData.vehiclename}
          onChange={handleChange}
        />

        <label>Vehicle Reg. No</label>
        <input
          type="text"
          name="vehicleRegistrationNumber"
          value={formData.vehicleRegistrationNumber}
          onChange={handleChange}
        />

        <label>Purpose</label>
        <input
          type="text"
          name="visitpurpose"
          value={formData.visitpurpose}
          onChange={handleChange}
        />

        <label>Time In</label>
        <input
          type="datetime-local"
          name="timein"
          value={formData.timein}
          onChange={handleChange}
        />

        <label>Time Out</label>
        <input
          type="datetime-local"
          name="timeout"
          value={formData.timeout}
          onChange={handleChange}
        />

        <label>Phone</label>
        <input
          type="text"
          name="phonenumber"
          value={formData.phonenumber}
          onChange={handleChange}
        />

        <label>Status (Active?)</label>
        <select
          name="isactivevisitor"
          value={formData.isactivevisitor ? "true" : "false"}
          onChange={(e) =>
            setFormData({
              ...formData,
              isactivevisitor: e.target.value === "true",
            })
          }
        >
          <option value="true">Active</option>
          <option value="false">Inactive</option>
        </select>

        <label>Visitor Type</label>
        <input
          type="text"
          name="visitorType"
          value={formData.visitorType}
          onChange={handleChange}
        />

        <label>Resident Name</label>
        <input
          type="text"
          name="residentname"
          value={formData.residentname}
          onChange={handleChange}
        />

        <div className="form-buttons">
          <button type="submit" className="update-btn">Update</button>
          <button type="button" className="cancel-btn" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

export default VisitorUpdateForm;
