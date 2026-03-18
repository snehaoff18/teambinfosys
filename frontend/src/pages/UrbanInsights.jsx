import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";

import "../styles/UrbanInsights.css";

function UrbanInsights() {

  const navigate = useNavigate();

  /* ================== STATE ================== */

  const [kpiData, setKpiData] = useState([]);
  const [demandData, setDemandData] = useState([]);
  const [hotspots, setHotspots] = useState([]);
  const [traffic, setTraffic] = useState({
    congestion: "",
    peakHours: ""
  });

  const [loading, setLoading] = useState(true);

  /* ================== FETCH DATA ================== */

  useEffect(() => {
    fetchInsights();
  }, []);

  const fetchInsights = async () => {
    try {
      const [kpiRes, demandRes, hotspotRes, trafficRes] = await Promise.all([
        axios.get("http://localhost:8080/api/insights/kpis"),
        axios.get("http://localhost:8080/api/insights/demand"),
        axios.get("http://localhost:8080/api/insights/hotspots"),
        axios.get("http://localhost:8080/api/insights/traffic")
      ]);

      setKpiData([
        { title: "Active Trips", value: kpiRes.data.activeTrips, icon: "🚗" },
        { title: "Avg Trip Duration", value: kpiRes.data.avgDuration, icon: "⏱️" },
        { title: "Fleet Utilization", value: kpiRes.data.utilization, icon: "📊" },
        { title: "Demand Index", value: kpiRes.data.demandIndex, icon: "🔥" }
      ]);

      setDemandData(demandRes.data);
      setHotspots(hotspotRes.data);
      setTraffic(trafficRes.data);

    } catch (error) {
      console.error("Failed to load insights:", error);
    } finally {
      setLoading(false);
    }
  };

  /* ================== LOADING STATE ================== */

  if (loading) {
    return <div className="loading">Loading insights...</div>;
  }

  /* ================== UI ================== */

  return (
    <div className="insights-page">

      {/* ===== HEADER ===== */}
      <div className="insights-header">
        <h1>🌆 Urban Mobility Insights</h1>

        <button
          className="back-btn"
          onClick={() => navigate("/admin")}
        >
          ⬅ Back to Dashboard
        </button>
      </div>

      {/* ===== KPI GRID ===== */}
      <div className="kpi-grid">
        {kpiData.map((kpi, index) => (
          <KPI
            key={index}
            title={kpi.title}
            value={kpi.value}
            icon={kpi.icon}
          />
        ))}
      </div>

      {/* ===== DEMAND CHART ===== */}
      <div className="card">
        <h2>📈 Booking Demand by Hour</h2>

        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={demandData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="time" />
            <YAxis />
            <Tooltip />
            <Line
              type="monotone"
              dataKey="bookings"
              stroke="#5b6cff"
              strokeWidth={3}
            />
          </LineChart>
        </ResponsiveContainer>
      </div>

      {/* ===== HOTSPOTS ===== */}
      <div className="card">
        <h2>📍 Top Pickup Hotspots</h2>

        <ul className="hotspot-list">
          {hotspots.map((zone, i) => (
            <li key={i}>{zone}</li>
          ))}
        </ul>
      </div>

      {/* ===== TRAFFIC INTELLIGENCE ===== */}
      <div className="card">
        <h2>🚦 Traffic Intelligence</h2>

        <div className="traffic-grid">
          <div>
            <strong>Congestion Level:</strong>
            <p className="highlight">{traffic.congestion}</p>
          </div>

          <div>
            <strong>Peak Hours:</strong>
            <p className="highlight">{traffic.peakHours}</p>
          </div>
        </div>
      </div>

    </div>
  );
}

/* ================== KPI COMPONENT ================== */

function KPI({ title, value, icon }) {
  return (
    <div className="kpi-card">
      <div className="kpi-icon">{icon}</div>
      <h3>{title}</h3>
      <p>{value}</p>
    </div>
  );
}

export default UrbanInsights;

