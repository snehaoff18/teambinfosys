import { useNavigate } from "react-router-dom";
import "../styles/CustomerDashboard.css";

function CustomerDashboard() {

  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <div className="dashboard-card">

        <h1 className="dashboard-title">
          👤 Customer Dashboard
        </h1>

        <p className="dashboard-subtitle">
          Welcome! You can easily book rides, manage your trips,
          and explore smart travel options here 🚗
        </p>

        <div className="dashboard-actions">

          <button
            className="dashboard-btn book-btn"
            onClick={() => navigate("/booking")}
          >
            🚗 Book a Ride
          </button>

          <button
            className="dashboard-btn history-btn"
            onClick={() => navigate("/booking-history")}
          >
            📜 View Booking History
          </button>

        </div>

      </div>
    </div>
  );
}

export default CustomerDashboard;