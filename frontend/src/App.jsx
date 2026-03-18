import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import AdminDashboard from "./pages/AdminDashboard";
import FleetDashboard from "./pages/FleetDashboard";
import CustomerDashboard from "./pages/CustomerDashboard";
import RouteOptimization from "./pages/RouteOptimization";
import MaintenanceForm from "./pages/MaintenanceForm";
import MaintenanceDashboard from "./pages/MaintenanceDashboard";
import BookingForm from "./pages/BookingForm";
import BookingHistory from "./pages/BookingHistory";
import UrbanInsights from "./pages/UrbanInsights";
import VehicleTelemetry from "./pages/VehicleTelemetry";

function App() {
  return (
    <Router>
      <Routes>
        {/* Public Routes */}
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Role Based Dashboards */}
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/fleet" element={<FleetDashboard />} />
        <Route path="/customer" element={<CustomerDashboard />} />
        <Route path="/optimize" element={<RouteOptimization />} />
        <Route path="/maintenance" element={<MaintenanceForm />} />
        <Route path="/maintenance-dashboard" element={<MaintenanceDashboard />} />
        <Route path="/insights" element={<UrbanInsights />} />
        <Route path="/telemetry" element={<VehicleTelemetry />} />

        {/* 🚗 Module 5 — Booking */}
        <Route path="/booking" element={<BookingForm />} />
        <Route path="/booking-history" element={<BookingHistory />} />

      </Routes>
    </Router>
  );
}

export default App;