import React, { useEffect, useState } from "react";
import axios from "axios";
import "./addResident.css";

const Resident = () => {
  const [residents, setResidents] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const rowsPerPage = 7; // ✅ only 7 rows per page

  useEffect(() => {
    axios.get("http://localhost:8080/residents/getAllResident") 
      .then((response) => {
        setResidents(response.data);
      })
      .catch((error) => {
        console.error("Error fetching residents:", error);
      });
  }, []);

  // Calculate data for current page
  const indexOfLastRow = currentPage * rowsPerPage;
  const indexOfFirstRow = indexOfLastRow - rowsPerPage;
  const currentRows = residents.slice(indexOfFirstRow, indexOfLastRow);

  // Calculate total pages
  const totalPages = Math.ceil(residents.length / rowsPerPage);

  const goToPage = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div className="resident-list-container">
      <h2>Resident List</h2>
      <table className="resident-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Flat No</th>
            <th>Mobile</th>
            <th>Email</th>
            <th>Parking Slot</th>
            <th>No of Vehicles</th>
            <th>Type</th>
          </tr>
        </thead>
        <tbody>
          {currentRows.map((res) => (
            <tr key={res.id}>
              <td>{res.id}</td>
              <td>{res.fname}</td>
              <td>{res.lname}</td>
              <td>{res.flatno}</td>
              <td>{res.mobileno}</td>
              <td>{res.email}</td>
              <td>{res.parkingslot}</td>
              <td>{res.noofvehicles}</td>
              <td>{res.residenttype}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Pagination Controls */}
      <div className="pagination">
        <button 
          onClick={() => goToPage(currentPage - 1)} 
          disabled={currentPage === 1}
        >
          Prev
        </button>

        {[...Array(totalPages).keys()].map((num) => (
          <button
            key={num + 1}
            onClick={() => goToPage(num + 1)}
            className={currentPage === num + 1 ? "active" : ""}
          >
            {num + 1}
          </button>
        ))}

        <button 
          onClick={() => goToPage(currentPage + 1)} 
          disabled={currentPage === totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default Resident;
