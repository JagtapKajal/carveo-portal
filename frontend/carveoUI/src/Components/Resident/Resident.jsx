// ResidentPage.jsx
import React, { useEffect, useState } from "react";
import ResidentUpdateForm from "./ResidentUpdateForm"; // import form
import "./Resident.css";

const Resident = () => {
  const [residents, setResidents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedResident, setSelectedResident] = useState(null); // For editing
  const recordsPerPage = 5;

  const fetchResidents = () => {
    fetch("http://localhost:8080/residents/getAllResident")
      .then((res) => res.json())
      .then((data) => {
        setResidents(data);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchResidents();
  }, []);

  // Handle update
  const handleUpdateClick = (resident) => {
    setSelectedResident(resident);
  };

  // Handle delete
  const handleDeleteClick = (id) => {
    if (window.confirm("Are you sure you want to delete this resident?")) {
      fetch(`http://localhost:8080/residents/DeleteResident/${id}`, {
        method: "DELETE",
      })
        .then((res) => {
          if (res.ok) {
            alert("Resident deleted successfully");
            fetchResidents(); // refresh data
          } else {
            alert("Delete failed");
          }
        })
        .catch((err) => console.error("Error deleting resident:", err));
    }
  };

  const handleFormClose = () => {
    setSelectedResident(null);
    fetchResidents(); // reload after update
  };

  // Pagination logic
  const indexOfLastRecord = currentPage * recordsPerPage;
  const indexOfFirstRecord = indexOfLastRecord - recordsPerPage;
  const currentRecords = residents.slice(indexOfFirstRecord, indexOfLastRecord);
  const totalPages = Math.ceil(residents.length / recordsPerPage);

  return (
    <div className="resident-page">
      <h2>Resident Details</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <table className="resident-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Flat No</th>
                <th>Mobile</th>
                <th>Email</th>
                <th>Type</th>
                <th>No of Vehicles</th>
                <th>Parking Slot</th>
                <th>Actions</th> {/* Update + Delete */}
              </tr>
            </thead>
            <tbody>
              {currentRecords.map((resident) => (
                <tr key={resident.id}>
                  <td>{resident.id}</td>
                  <td>{resident.fname}</td>
                  <td>{resident.lname}</td>
                  <td>{resident.flatno}</td>
                  <td>{resident.mobileno}</td>
                  <td>{resident.email}</td>
                  <td>{resident.residenttype}</td>
                  <td>{resident.noofvehicles}</td>
                  <td>{resident.parkingslot}</td>
                  <td>
                    <button
                      className="update-btn"
                      onClick={() => handleUpdateClick(resident)}
                    >
                      Update
                    </button>
                    <button
                      className="delete-btn"
                      onClick={() => handleDeleteClick(resident.id)}
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
                onClick={() => setCurrentPage(index + 1)}>
                {index + 1}
              </button>
            ))}
          </div>
        </>
      )}

      {/* Open update form if a resident is selected */}
      {selectedResident && (
        <ResidentUpdateForm
          resident={selectedResident}
          onClose={handleFormClose}/>
      )}
    </div>
  );
};

export default Resident;
