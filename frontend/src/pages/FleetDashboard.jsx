import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/FleetDashboard.css";

const API = "http://localhost:8080/api/vehicles";

function FleetDashboard() {
  const [vehicles, setVehicles] = useState([]);
  const [form, setForm] = useState({
    vehicleNumber: "",
    type: "",
    capacity: "",
    model: "",
    manufacturer: "",
    year: "",
    status: "Available"
  });

  const navigate = useNavigate();

  // 📥 Fetch vehicles
  const fetchVehicles = async () => {
    const res = await axios.get(API);
    setVehicles(res.data);
  };

  useEffect(() => {
    fetchVehicles();
  }, []);

  // ➕ Add vehicle
  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post(API, form);
    fetchVehicles();

    setForm({
      vehicleNumber: "",
      type: "",
      capacity: "",
      model: "",
      manufacturer: "",
      year: "",
      status: "Available"
    });
  };

  // ❌ Delete vehicle
  const handleDelete = async (id) => {
    await axios.delete(`${API}/${id}`);
    fetchVehicles();
  };

  return (
    <div className="fleet-page">

      {/* ===== HEADER ===== */}
      <div className="fleet-header">

        {/* LEFT — TWO MODULE BUTTONS */}
        <div className="header-left">

          <button
            className="optimize-btn"
            onClick={() => navigate("/optimize")}
          >
            🧠 AI Route Optimization
          </button>

          <button
            className="telemetry-btn"
            onClick={() => navigate("/telemetry")}
          >
            📡 Vehicle Telemetry
          </button>

        </div>

        {/* CENTER — TITLE */}
        <h1 className="fleet-title">🚗 Fleet Inventory</h1>

        {/* RIGHT — BACK BUTTON */}
        <div className="header-right">
          <button
            className="back-btn"
            onClick={() => navigate("/admin")}
          >
            ⬅ Back to Dashboard
          </button>
        </div>

      </div>

      {/* ===== MAIN CONTENT ===== */}
      <div className="fleet-content">

        {/* ADD VEHICLE FORM */}
        <div className="card form-card">
          <h3>Add Vehicle</h3>

          <form onSubmit={handleSubmit} className="form-grid">

            <input
              placeholder="Vehicle Number"
              value={form.vehicleNumber}
              onChange={(e) =>
                setForm({ ...form, vehicleNumber: e.target.value })
              }
              required
            />

            <input
              placeholder="Type"
              value={form.type}
              onChange={(e) =>
                setForm({ ...form, type: e.target.value })
              }
              required
            />

            <input
              type="number"
              placeholder="Capacity"
              value={form.capacity}
              onChange={(e) =>
                setForm({ ...form, capacity: e.target.value })
              }
            />

            <input
              placeholder="Model"
              value={form.model}
              onChange={(e) =>
                setForm({ ...form, model: e.target.value })
              }
            />

            <input
              placeholder="Manufacturer"
              value={form.manufacturer}
              onChange={(e) =>
                setForm({ ...form, manufacturer: e.target.value })
              }
            />

            <input
              type="number"
              placeholder="Year"
              value={form.year}
              onChange={(e) =>
                setForm({ ...form, year: e.target.value })
              }
            />

            <select
              value={form.status}
              onChange={(e) =>
                setForm({ ...form, status: e.target.value })
              }
            >
              <option>Available</option>
              <option>In Use</option>
              <option>Maintenance</option>
            </select>

            <button type="submit" className="add-btn">
              Add Vehicle
            </button>

          </form>
        </div>

        {/* VEHICLE TABLE */}
        <div className="card table-card">
          <h3>Fleet Vehicles</h3>

          <table className="vehicle-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Number</th>
                <th>Type</th>
                <th>Capacity</th>
                <th>Model</th>
                <th>Manufacturer</th>
                <th>Year</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {vehicles.map((v) => (
                <tr key={v.id}>
                  <td>{v.id}</td>
                  <td>{v.vehicleNumber}</td>
                  <td>{v.type}</td>
                  <td>{v.capacity}</td>
                  <td>{v.model}</td>
                  <td>{v.manufacturer}</td>
                  <td>{v.year}</td>

                  <td>
                    <span
                      className={`status ${v.status
                        .replace(" ", "")
                        .toLowerCase()}`}
                    >
                      {v.status}
                    </span>
                  </td>

                  <td>
                    <button
                      className="delete-btn"
                      onClick={() => handleDelete(v.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>

          </table>
        </div>

      </div>

    </div>
  );
}

export default FleetDashboard;