import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/VehicleTelemetry.css";   // ⭐ IMPORT CSS

const VEHICLE_API = "http://localhost:8080/api/vehicles";
const TELEMETRY_API = "http://localhost:8080/api/telemetry";

function VehicleTelemetry() {

  const [vehicles, setVehicles] = useState([]);
  const [vehicleId, setVehicleId] = useState("");
  const [latest, setLatest] = useState(null);

  const [form, setForm] = useState({
    latitude: "",
    longitude: "",
    speed: "",
    fuelLevel: "",
    engineTemp: "",
    batteryHealth: "",
    odometer: ""
  });

  // 🚗 Fetch vehicles
  useEffect(() => {
    axios.get(VEHICLE_API).then(res => setVehicles(res.data));
  }, []);

  // 📊 Fetch latest telemetry
  const fetchLatest = async (id) => {
    if (!id) return;
    const res = await axios.get(`${TELEMETRY_API}/latest/${id}`);
    setLatest(res.data);
  };

  // 📡 Send telemetry
  const sendTelemetry = async () => {
    if (!vehicleId) {
      alert("Select a vehicle first!");
      return;
    }

    await axios.post(`${TELEMETRY_API}/${vehicleId}`, form);

    alert("Telemetry Sent 🚀");
    fetchLatest(vehicleId);
  };

  return (
    <div className="telemetry-page">

      {/* ===== HEADER ===== */}
      <div className="telemetry-header">
        <h1 className="telemetry-title">📡 Vehicle Telemetry</h1>
      </div>

      <div className="telemetry-content">

        {/* 🚗 Vehicle Selection */}
        <div className="telemetry-card">
          <h2>Select Vehicle</h2>

          <select
            className="vehicle-select"
            value={vehicleId}
            onChange={(e) => {
              setVehicleId(e.target.value);
              fetchLatest(e.target.value);
            }}
          >
            <option value="">-- Select --</option>

            {vehicles.map(v => (
              <option key={v.id} value={v.id}>
                {v.vehicleNumber} ({v.type})
              </option>
            ))}
          </select>
        </div>

        {/* 📡 Telemetry Form */}
        <div className="telemetry-card">
          <h2>Telemetry Simulator</h2>

          <div className="telemetry-form">

            {Object.keys(form).map(field => (
              <input
                key={field}
                placeholder={field}
                value={form[field]}
                onChange={(e) =>
                  setForm({ ...form, [field]: e.target.value })
                }
              />
            ))}

            <button className="send-btn" onClick={sendTelemetry}>
              Send Telemetry
            </button>

          </div>
        </div>

        {/* 📊 Latest Status */}
        <div className="telemetry-card">
          <h2>Latest Status</h2>

          {latest ? (
            <div className="status-box">
              <p>Speed: {latest.speed} km/h</p>
              <p>Fuel: {latest.fuelLevel}%</p>
              <p>Engine Temp: {latest.engineTemp} °C</p>
              <p>Battery: {latest.batteryHealth}%</p>
              <p>
                Location: ({latest.latitude}, {latest.longitude})
              </p>
              <p>Odometer: {latest.odometer} km</p>
            </div>
          ) : (
            <p>No telemetry data yet</p>
          )}

        </div>

      </div>
    </div>
  );
}

export default VehicleTelemetry;