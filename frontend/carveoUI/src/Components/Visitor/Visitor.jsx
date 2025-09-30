import React, { useEffect, useState } from "react";
import "./Visitor.css";

const Visitor = () => {
  const [visitors, setVisitors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const recordsPerPage = 7;

  useEffect(() => {
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
  }, []);

  // Pagination logic
  const indexOfLastRecord = currentPage * recordsPerPage;
  const indexOfFirstRecord = indexOfLastRecord - recordsPerPage;
  const currentRecords = visitors.slice(indexOfFirstRecord, indexOfLastRecord);
  const totalPages = Math.ceil(visitors.length / recordsPerPage);

  return (
    <div className="visitor-page">
      <h2 className="visitor-title">Visitor Details</h2>
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
                  <td>{visitor.timein ? new Date(visitor.timein).toLocaleString() : "—"}</td>
                  <td>{visitor.timeout ? new Date(visitor.timeout).toLocaleString() : "—"}</td>
                  <td>{visitor.phonenumber || "—"}</td>
                  <td>{visitor.isactivevisitor ? "Active" : "Inactive"}</td>
                  <td>{visitor.visitorType}</td>
                  <td>{visitor.residentname || "—"}</td> 
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
        </>
      )}
    </div>
  );
};

export default Visitor;
