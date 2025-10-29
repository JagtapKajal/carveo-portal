// ResidentPage.jsx
import React, { useEffect, useState } from "react";
import ResidentUpdateForm from "./ResidentUpdateForm"; // import form
import "./Resident.css";

const Resident = () => {
  const [residents, setResidents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedResident, setSelectedResident] = useState(null); // For editing
  const [searchTerm, setSearchTerm] = useState(""); // ✅ for search input
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

  // ✅ Filter logic for search
  const filteredResidents = residents.filter((r) =>
    (r.fname +
      " " +
      r.lname +
      " " +
      r.flatno +
      " " +
      r.mobileno +
      " " +
      r.email)
      .toLowerCase()
      .includes(searchTerm.toLowerCase())
  );

  // Pagination logic (applied after filtering)
  const indexOfLastRecord = currentPage * recordsPerPage;
  const indexOfFirstRecord = indexOfLastRecord - recordsPerPage;
  const currentRecords = filteredResidents.slice(
    indexOfFirstRecord,
    indexOfLastRecord
  );
  const totalPages = Math.ceil(filteredResidents.length / recordsPerPage);

  const handleUpdateClick = (resident) => {
    setSelectedResident(resident);
  };

  const handleDeleteClick = (id) => {
    if (window.confirm("Are you sure you want to delete this resident?")) {
      fetch(`http://localhost:8080/residents/DeleteResident/${id}`, {
        method: "DELETE",
      })
        .then((res) => {
          if (res.ok) {
            alert("Resident deleted successfully");
            fetchResidents();
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

  return (
    <div className="resident-page">
      <h2>Resident Details</h2>

      {/* ✅ Search Bar */}
      <input
        type="text"
        placeholder="Search by name, flat, mobile, or email..."
        value={searchTerm}
        onChange={(e) => {
          setSearchTerm(e.target.value);
          setCurrentPage(1); // reset to page 1 on search
        }}
        className="search-input"
      />

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
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {currentRecords.length > 0 ? (
                currentRecords.map((resident) => (
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
                ))
              ) : (
                <tr>
                  <td colSpan="10" style={{ textAlign: "center" }}>
                    No residents found.
                  </td>
                </tr>
              )}
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
        </>
      )}

      {/* Update Form */}
      {selectedResident && (
        <ResidentUpdateForm
          resident={selectedResident}
          onClose={handleFormClose}
        />
      )}
    </div>
  );
};

export default Resident;
