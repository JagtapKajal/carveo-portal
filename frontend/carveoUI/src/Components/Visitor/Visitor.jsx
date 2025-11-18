import React, { useEffect, useState } from "react";
import "./Visitor.css";
import VisitorUpdateForm from "./VisitorUpdateForm";
import { useNavigate } from "react-router-dom";

const Visitor = () => {
  const [visitors, setVisitors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchTerm, setSearchTerm] = useState(""); // ✅ Added search state
  const recordsPerPage = 5;

  const [showUpdateForm, setShowUpdateForm] = useState(false);
  const [selectedVisitor, setSelectedVisitor] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchVisitors();
  }, []);

  const fetchVisitors = () => {
    setLoading(true);
    fetch("http://localhost:8080/visitors/getAllVisitors")
      .then((res) => res.json())
      .then((data) => {
        setVisitors(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching visitors:", err);
        setLoading(false);
      });
  };

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this visitor?")) {
      fetch(`http://localhost:8080/visitors/deleteVisitor/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
      })
        .then((res) => {
          if (res.ok) {
            alert("Visitor deleted successfully!");
            fetchVisitors();
          } else {
            alert("Failed to delete visitor");
          }
        })
        .catch((err) => console.error("Error deleting visitor:", err));
    }
  };

  const handleUpdate = (visitor) => {
    setSelectedVisitor(visitor);
    setShowUpdateForm(true);
  };

  // ✅ Filter visitors based on search term
  const filteredVisitors = visitors.filter((v) =>
    v.visitorname.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // Pagination logic
  const indexOfLastRecord = currentPage * recordsPerPage;
  const indexOfFirstRecord = indexOfLastRecord - recordsPerPage;
  const currentRecords = filteredVisitors.slice(
    indexOfFirstRecord,
    indexOfLastRecord
  );
  const totalPages = Math.ceil(filteredVisitors.length / recordsPerPage);

  return (
    <div className="visitor-page">
      <h2 className="visitor-title">👨‍👩‍👧Visitor Details</h2>

      {/* ✅ Search bar (left) + Add button (right) */}
      <div className="table-header">
        <input
          type="text"
          placeholder="Search Visitor Here..."
          value={searchTerm}
          onChange={(e) => {
            setSearchTerm(e.target.value);
            setCurrentPage(1);
          }}
          className="search-input"
        />

        <button
          className="visitor-add-btn"
          onClick={() => navigate("/AddVisitor")}
        >
          Add Visitor
        </button>
      </div>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <table className="visitor-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Visitor Name</th>
                <th>Vehicle Name</th>
                <th>Vehicle Reg. No</th>
                <th>Purpose</th>
                <th>Time In</th>
                <th>Time Out</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Visitor Type</th>
                <th>Resident Name</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {currentRecords.map((visitor) => (
                <tr key={visitor.id}>
                  <td>{visitor.id}</td>
                  <td>{visitor.visitorname}</td>
                  <td>{visitor.vehiclename || "—"}</td>
                  <td>{visitor.vehicleRegistrationNumber || "—"}</td>
                  <td>{visitor.visitpurpose || "—"}</td>
                  <td>
                    {visitor.timein
                      ? new Date(visitor.timein).toLocaleString()
                      : "—"}
                  </td>
                  <td>
                    {visitor.timeout
                      ? new Date(visitor.timeout).toLocaleString()
                      : "—"}
                  </td>
                  <td>{visitor.phonenumber || "—"}</td>
                  <td>{visitor.isactivevisitor ? "Active" : "Inactive"}</td>
                  <td>{visitor.visitorType}</td>
                  <td>{visitor.residentname || "—"}</td>
                  <td className="button">
                    <button
                      className="visitor-update-btn"
                      onClick={() => handleUpdate(visitor)}
                    >
                      Update
                    </button>
                    <button
                      className="visitor-delete-btn"
                      onClick={() => handleDelete(visitor.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

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

          {showUpdateForm && selectedVisitor && (
            <VisitorUpdateForm
              visitorData={selectedVisitor}
              onUpdated={() => {
                fetchVisitors();
                setShowUpdateForm(false);
              }}
              onCancel={() => setShowUpdateForm(false)}
            />
          )}
        </>
      )}
    </div>
  );
};

export default Visitor;
