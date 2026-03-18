import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/AdminDashboard.css";

function AdminDashboard() {

  const navigate = useNavigate();
  const [stats, setStats] = useState(null);

  // 🔥 Fetch dashboard data
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/admin/dashboard/stats")
      .then(res => setStats(res.data))
      .catch(err => console.error(err));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div className="admin-container">

      {/* 🔹 Sidebar */}
      <div className="sidebar">
        <h2>NeuroFleetX</h2>

        <a href="#">Dashboard</a>
        <a href="/fleet">Fleet</a>
        <a href="#">Managers</a>
        <a onClick={() => navigate("/insights")}>
  Insights
</a>

        {/* Module 4 */}
        <a href="#" onClick={() => navigate("/maintenance")}>
          Predictive Maintenance
        </a>

        <a href="#" onClick={() => navigate("/maintenance-dashboard")}>
          Maintenance Reports
        </a>
      </div>

      {/* 🔹 Main Content */}
      <div className="main-content">

        {/* Top Header */}
        <div className="topbar">
          <span>🚦 Admin Dashboard</span>

          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </div>

        {/* Loading */}
        {!stats && <h2>Loading data...</h2>}

        {/* Cards */}
        {stats && (
         <div className="cards-grid">

  {/* 🚗 Fleet Overview */}
  <div className="card blue">
    <h3>Total Fleet Vehicles</h3>
    <p>{stats.totalVehicles} 🚗</p>
  </div>

  {/* 🟢 Available */}
  <div className="card green">
    <h3>Available Vehicles</h3>
    <p>{stats.availableVehicles} 🟢</p>
  </div>

  {/* 🚚 In Use */}
  <div className="card purple">
    <h3>Vehicles In Use</h3>
    <p>{stats.inUseVehicles} 🚚</p>
  </div>

  {/* 🛠 Maintenance */}
  <div className="card orange">
    <h3>Under Maintenance</h3>
    <p>{stats.maintenanceVehicles} 🛠</p>
  </div>

  {/* 📅 Bookings */}
  <div className="card teal">
    <h3>Total Bookings</h3>
    <p>{stats.totalBookings} 📅</p>
  </div>

  {/* 💰 Revenue */}
  <div className="card dark">
    <h3>Total Revenue</h3>
    <p>₹ {stats.totalRevenue} 💰</p>
  </div>

  {/* 🚨 Alerts */}
  <div className="card red">
    <h3>Active Alerts</h3>
    <p>{stats.activeAlerts} 🚨</p>
  </div>

  <div className="card orange">
    <h3>Critical Alerts</h3>
    <p>{stats.criticalAlerts} ⚠️</p>
  </div>

</div>
        )}

      </div>
    </div>
  );
}

export default AdminDashboard;