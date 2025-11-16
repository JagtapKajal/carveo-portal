import React, { useEffect, useState } from "react";
import "./Vehicle.css";
import VehicleUpdateForm from "./VehicleUpdateForm ";

const Vehicle = () => {
  const [vehicles, setVehicles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const recordsPerPage = 5;

  // For update form modal
  const [showUpdateForm, setShowUpdateForm] = useState(false);
  const [selectedVehicleId, setSelectedVehicleId] = useState(null);

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = () => {
    setLoading(true);
    fetch("http://localhost:8080/vehicles/getAllVehicles")
      .then((res) => res.json())
      .then((data) => {
        setVehicles(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching vehicles:", err);
        setLoading(false);
      });
  };

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this vehicle?")) {
      fetch(`http://localhost:8080/vehicles/deleteVehicleById/${id}`, {
        method: "DELETE",
      })
        .then((res) => {
          if (res.ok) fetchVehicles();
          else alert("Failed to delete vehicle");
        })
        .catch((err) => console.error(err));
    }
  };

  const [selectedVehicle, setSelectedVehicle] = useState(null);

  const handleUpdate = (vehicle) => {
    setSelectedVehicle({
      ...vehicle,
      id: vehicle.id || vehicle.vehicleid,
    });
    setShowUpdateForm(true);
  };

  // Pagination logic
  const indexOfLastRecord = currentPage * recordsPerPage;
  const indexOfFirstRecord = indexOfLastRecord - recordsPerPage;
  const currentRecords = vehicles.slice(indexOfFirstRecord, indexOfLastRecord);
  const totalPages = Math.ceil(vehicles.length / recordsPerPage);

  return (
    <div className="vehicle-page">
      <h2 className="vehicle-title">Vehicle Details</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <table className="vehicle-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Registration No</th>
                <th>Name</th>
                <th>Color</th>
                <th>Type</th>
                <th>In Time</th>
                <th>Out Time</th>
                <th>IsActive</th>
                <th>Resident</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {currentRecords.map((vehicle) => (
                <tr key={vehicle.id}>
                  <td>{vehicle.id}</td>
                  <td>{vehicle.registrationnumber}</td>
                  <td>{vehicle.vname}</td>
                  <td>{vehicle.color || "—"}</td>
                  <td>{vehicle.type}</td>
                  <td>
                    {vehicle.intime
                      ? new Date(vehicle.intime).toLocaleString()
                      : "—"}
                  </td>
                  <td>
                    {vehicle.outtime
                      ? new Date(vehicle.outtime).toLocaleString()
                      : "—"}
                  </td>
                  <td>{vehicle.isvehicleactive ? "Active" : "Inactive"}</td>
                  <td>{vehicle.residentName || "—"}</td>
                  <td>
                    <button
                      className="update-btn"
                      onClick={() => handleUpdate(vehicle)}
                    >
                      Update
                    </button>

                    <button
                      className="delete-btn"
                      onClick={() => handleDelete(vehicle.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* Pagination */}
          <div className="pagination">
            {Array.from({ length: totalPages }, (_, index) => (
              <button
                key={index + 1}
                className={currentPage === index + 1 ? "active" : ""}
                onClick={() => setCurrentPage(index + 1)}
              >
                {index + 1}
              </button>
            ))}
          </div>

          {/* Vehicle Update Form Modal */}
          {showUpdateForm && selectedVehicle && (
            <VehicleUpdateForm
              vehicleData={selectedVehicle}
              onCancel={() => setShowUpdateForm(false)}
              onUpdate={fetchVehicles}
            />
          )}
        </>
      )}
    </div>
  );
};
export default Vehicle;


