import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/MaintenanceDashboard.css";
import { useNavigate } from "react-router-dom";

function MaintenanceDashboard() {
  const navigate = useNavigate();
  const [reports, setReports] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/maintenance/reports")
      .then(res => setReports(res.data))
      .catch(err => console.error(err));
  }, []);

  const getBadgeClass = (level) => {
    if (level === "Healthy") return "badge badge-healthy";
    if (level === "Warning") return "badge badge-warning";
    return "badge badge-critical";
  };

  return (
    <div className="dashboard-container">

      {/* ⬅ Back Button */}
      <button
        className="back-btn"
        onClick={() => navigate("/admin")}
      >
        ⬅ Back to Dashboard
      </button>

      <h2 className="dashboard-title">
        📊 Maintenance Analytics Dashboard
      </h2>

      {reports.length === 0 ? (
        <p style={{ textAlign: "center" }}>
          Loading reports...
        </p>
      ) : (
        <table className="report-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Vehicle</th>
              <th>Score</th>
              <th>Status</th>
              <th>Recommendation</th>
              <th>Generated At</th>
            </tr>
          </thead>

          <tbody>
            {reports.map(r => (
              <tr key={r.id}>
                <td>{r.id}</td>
                <td>{r.vehicleId}</td>
                <td>{r.healthScore}</td>

                <td>
                  <span className={getBadgeClass(r.riskLevel)}>
                    {r.riskLevel}
                  </span>
                </td>

                <td className="recommendation">
                  {r.recommendation}
                </td>

                <td>
                  {new Date(r.generatedAt).toLocaleString()}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

    </div>
  );
}

export default MaintenanceDashboard;