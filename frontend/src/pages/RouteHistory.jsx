import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/RouteOptimization.css";

function RouteHistory() {
  const [routes, setRoutes] = useState([]);

  useEffect(() => {
    fetchHistory();
  }, []);

  const fetchHistory = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/routes/history"
      );
      setRoutes(response.data);
    } catch (error) {
      console.error("Error fetching history:", error);
    }
  };

  return (
  <div className="history-card">

    <h2 className="history-title">Route History</h2>

    <table className="history-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Vehicle</th>
          <th>Pickup</th>
          <th>Stops</th>
          <th>Distance</th>
          <th>Time</th>
        </tr>
      </thead>

      <tbody>
        {routes.map((r) => (
          <tr key={r.id}>
            <td>{r.id}</td>
            <td>{r.vehicleNumber}</td>
            <td>{r.pickupLocation}</td>
            <td>{r.stops}</td>
            <td>{r.totalDistance} km</td>
            <td>{r.estimatedTime.toFixed(2)} hrs</td>
          </tr>
        ))}
      </tbody>
    </table>

  </div>
);

}

export default RouteHistory;
