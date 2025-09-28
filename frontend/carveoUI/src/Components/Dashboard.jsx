import { Link } from "react-router-dom";
import "./Dashboard.css";

function Dashboard() {
  return (
    <div className="dashboard">
      <h1>Dashboard</h1>
      <p>Welcome to Society Management Portal</p>

      <div className="card-container">
        <Link to="/residents" className="card-link">
          <div className="card">
            <h3>Residents</h3>
            <p className="count">120</p>
          </div>
        </Link>

        <Link to="/vehicles" className="card-link">
          <div className="card">
            <h3>Vehicles</h3>
            <p className="count">85</p>
          </div>
        </Link>

        <Link to="/visitors" className="card-link">
          <div className="card">
            <h3>Visitors Today</h3>
            <p className="count">12</p>
          </div>
        </Link>
      </div>
    </div>
  );
}

export default Dashboard;
