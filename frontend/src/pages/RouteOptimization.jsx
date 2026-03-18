import { useState } from "react";
import axios from "axios";

import RouteHistory from "../pages/RouteHistory";
import RouteMap from "../pages/RouteMap";

import "../styles/RouteOptimization.css";

function RouteOptimization() {
  const [pickup, setPickup] = useState("");
  const [drops, setDrops] = useState("");
  const [weight, setWeight] = useState("");
  const [result, setResult] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const dropArray = drops.split(",").map((d) => d.trim());

    const requestData = {
      pickupLocation: pickup,
      dropLocations: dropArray,
      totalWeight: parseFloat(weight),
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/routes/optimize",
        requestData
      );
      setResult(response.data);
    } catch (error) {
      console.error("Error:", error);
      alert("Optimization failed");
    }
  };

  return (
    <div className="optimization-container">

      <h2 className="optimization-title">🧠 AI Route Optimization</h2>

      {/* 📝 FORM */}
      <div className="optimization-card">
        <form onSubmit={handleSubmit} className="form-grid">

          <input
            type="text"
            placeholder="Pickup Location"
            value={pickup}
            onChange={(e) => setPickup(e.target.value)}
            required
          />

          <input
            type="text"
            placeholder="Drop Locations (comma separated)"
            value={drops}
            onChange={(e) => setDrops(e.target.value)}
            required
          />

          <input
            type="number"
            placeholder="Total Weight (kg)"
            value={weight}
            onChange={(e) => setWeight(e.target.value)}
            required
            className="full-width"
          />

          {/* ⭐ MAIN BUTTON */}
          <button type="submit" className="optimize-btn full-width">
            Optimize Route
          </button>

        </form>
      </div>

      {/* 📊 RESULT */}
      {result && (
        <>
          <div className="optimization-card result-section">
            <h3>Optimization Result</h3>

            <p><b>Vehicle:</b> {result.vehicleNumber}</p>
            <p><b>Total Distance:</b> {result.totalDistance} km</p>
            <p><b>Estimated Time:</b> {result.estimatedTime.toFixed(2)} hrs</p>

            <h4>Route:</h4>
            <ol className="route-list">
              {result.optimizedRoute.map((stop, index) => (
                <li key={index}>{stop}</li>
              ))}
            </ol>
          </div>

          <RouteMap route={result.optimizedRoute} />
        </>
      )}

      {/* 📚 HISTORY */}
      <RouteHistory />

    </div>
  );
}

export default RouteOptimization;