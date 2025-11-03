import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Visitor.css";

const AddVisitor = () => {
  const navigate = useNavigate();

  const [visitor, setVisitor] = useState({
    visitorName: "",
    vehicleName: "",
    vehicleRegNo: "",
    purpose: "",
    timeIn: "",
    timeOut: "",
    phone: "",
    status: "",
    visitorType: "",
    residentName: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setVisitor({ ...visitor, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("https://carveo-portal.onrender.com/visitors/addVisitor", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(visitor),
      });

      if (response.ok) {
        alert("Visitor added successfully!");
        navigate("/visitor"); // redirect to visitor list
      } else {
        alert("Failed to add visitor.");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Something went wrong while adding visitor.");
    }
  };

  return (
    <div className="visitor-form-container">
      <h2>Add Visitor</h2>
      <form onSubmit={handleSubmit} className="visitor-form">

        <div className="form-group">
          <label>Visitor Name:</label>
          <input
            type="text"
            name="visitorName"
            value={visitor.visitorName}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Vehicle Name:</label>
          <input
            type="text"
            name="vehicleName"
            value={visitor.vehicleName}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Vehicle Reg. No:</label>
          <input
            type="text"
            name="vehicleRegNo"
            value={visitor.vehicleRegNo}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Purpose:</label>
          <input
            type="text"
            name="purpose"
            value={visitor.purpose}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Time In:</label>
          <input
            type="datetime-local"
            name="timeIn"
            value={visitor.timeIn}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Time Out:</label>
          <input
            type="datetime-local"
            name="timeOut"
            value={visitor.timeOut}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Phone:</label>
          <input
            type="text"
            name="phone"
            value={visitor.phone}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Status:</label>
          <input
            type="text"
            name="status"
            value={visitor.status}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Visitor Type:</label>
          <input
            type="text"
            name="visitorType"
            value={visitor.visitorType}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Resident Name:</label>
          <input
            type="text"
            name="residentName"
            value={visitor.residentName}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="visitor-submit-btn">Add</button>
        <button
          type="button"
          className="visitor-cancel-btn"
          onClick={() => navigate("/visitor")}
        >
          Cancel
        </button>
      </form>
    </div>
  );
};

export default AddVisitor;
