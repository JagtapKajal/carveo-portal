import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Visitor.css"; // same style file you used for visitor table

const AddVisitor = () => {
  const navigate = useNavigate();

  const [visitor, setVisitor] = useState({
    registrationNo: "",
    name: "",
    color: "",
    type: "",
    inTime: "",
    outTime: "",
    status: "",
    resident: "",
  });

  // handle input
  const handleChange = (e) => {
    const { name, value } = e.target;
    setVisitor({ ...visitor, [name]: value });
  };

  // handle form submit
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/visitors", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(visitor),
      });

      if (response.ok) {
        alert("Visitor added successfully!");
        navigate("/visitor"); // redirect back to visitor list
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
          <label>Registration No:</label>
          <input
            type="text"
            name="registrationNo"
            value={visitor.registrationNo}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={visitor.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Color:</label>
          <input
            type="text"
            name="color"
            value={visitor.color}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Type:</label>
          <input
            type="text"
            name="type"
            value={visitor.type}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>In Time:</label>
          <input
            type="datetime-local"
            name="inTime"
            value={visitor.inTime}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Out Time:</label>
          <input
            type="datetime-local"
            name="outTime"
            value={visitor.outTime}
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
          <label>Resident:</label>
          <input
            type="text"
            name="resident"
            value={visitor.resident}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="submit-btn">Add Visitor</button>
        <button type="button" className="cancel-btn" onClick={() => navigate("/visitor")}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default AddVisitor;
