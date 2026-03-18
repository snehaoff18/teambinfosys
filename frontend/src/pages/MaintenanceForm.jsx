import { useState } from "react";
import axios from "axios";
import "../styles/MaintenanceForm.css";
import { useNavigate } from "react-router-dom";   // ⭐ ADD THIS

function MaintenanceForm() {

  const navigate = useNavigate();   // ⭐ ADD THIS

  const [form, setForm] = useState({
    vehicleId: "",
    engineTemp: "",
    batteryHealth: "",
    tirePressure: "",
    mileageSinceService: "",
    serviceOverdue: false
  });

  const [result, setResult] = useState(null);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setForm({
      ...form,
      [name]: type === "checkbox" ? checked : value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post(
        "http://localhost:8080/api/maintenance/analyze",
        form
      );

      setResult(res.data);

    } catch (err) {
      alert("Error analyzing vehicle");
      console.error(err);
    }
  };

  return (
  <div className="maintenance-container">

    {/* ⬅ Back Button */}
    <button
  className="back-btn"
  onClick={() => navigate("/admin")}
>
  ⬅ Back to Dashboard
</button>

    <h2 className="maintenance-title">
      🚗 Vehicle Health Analysis
    </h2>

    <div className="form-card">

      <form onSubmit={handleSubmit}>

        <input
          name="vehicleId"
          placeholder="Vehicle ID"
          onChange={handleChange}
          required
        />

        <input
          name="engineTemp"
          type="number"
          placeholder="Engine Temperature"
          onChange={handleChange}
          required
        />

        <input
          name="batteryHealth"
          type="number"
          placeholder="Battery Health (%)"
          onChange={handleChange}
          required
        />

        <input
          name="tirePressure"
          type="number"
          placeholder="Tire Pressure"
          onChange={handleChange}
          required
        />

        <input
          name="mileageSinceService"
          type="number"
          placeholder="Mileage Since Service"
          onChange={handleChange}
          required
        />

        {/* ⭐ Fixed checkbox alignment */}
        <div className="checkbox-row">
          <label>Service Overdue</label>
          <input
            type="checkbox"
            name="serviceOverdue"
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="analyze-btn">
          Analyze Vehicle
        </button>

      </form>
    </div>

    {/* Result */}

    {result && (
      <div className="result-card">
        <h3>Analysis Result</h3>

        <p><b>Vehicle:</b> {result.vehicleId}</p>
        <p><b>Health Score:</b> {result.healthScore}</p>

        <p>
          <b>Status:</b>{" "}
          <span
            className={
              result.riskLevel === "Healthy"
                ? "status-healthy"
                : result.riskLevel === "Warning"
                ? "status-warning"
                : "status-critical"
            }
          >
            {result.riskLevel}
          </span>
        </p>

        <p><b>Recommendation:</b> {result.recommendation}</p>
      </div>
    )}
  </div>
);
}
export default MaintenanceForm;