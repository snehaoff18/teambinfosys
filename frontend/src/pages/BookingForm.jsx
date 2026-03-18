import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/BookingForm.css";

import { MapContainer, TileLayer, Marker, Polyline } from "react-leaflet";
import "leaflet/dist/leaflet.css";

function BookingForm() {

  const navigate = useNavigate();

  const [form, setForm] = useState({
    customerName: "",
    pickupLocation: "",
    dropLocation: "",
    passengers: ""
  });

  const [preview, setPreview] = useState(null);
  const [result, setResult] = useState(null);
  const [errorMsg, setErrorMsg] = useState("");

  const [pickupCoords, setPickupCoords] = useState(null);
  const [dropCoords, setDropCoords] = useState(null);
  const [distance, setDistance] = useState(null);

  // 🌍 Convert place → coordinates
  const geocode = async (place) => {
    const res = await fetch(
      `https://nominatim.openstreetmap.org/search?format=json&q=${place}, Chennai`
    );
    const data = await res.json();

    if (data.length > 0) {
      return [parseFloat(data[0].lat), parseFloat(data[0].lon)];
    }
    return null;
  };

  // 🚗 Get road distance + time
  const getRouteInfo = async (a, b) => {
    const res = await fetch(
      `https://router.project-osrm.org/route/v1/driving/${a[1]},${a[0]};${b[1]},${b[0]}?overview=false`
    );

    const data = await res.json();

    if (data.routes.length > 0) {
      const route = data.routes[0];

      const km = (route.distance / 1000).toFixed(2);
      const minutes = Math.round(route.duration / 60);

      return `${km} km • ${minutes} mins`;
    }

    return null;
  };

  // 🔹 Update form fields
  const handleChange = (e) => {
    const updatedForm = {
      ...form,
      [e.target.name]: e.target.value
    };

    setForm(updatedForm);
    generatePreview(updatedForm);
  };

  // ⭐ Trigger map AFTER user finishes typing
  const updateMap = async () => {

    if (!form.pickupLocation || !form.dropLocation) return;

    const pickup = await geocode(form.pickupLocation);
    const drop = await geocode(form.dropLocation);

    if (pickup && drop) {
      setPickupCoords(pickup);
      setDropCoords(drop);

      const route = await getRouteInfo(pickup, drop);
      setDistance(route);
    }
  };

  // 🧠 AI Recommendation
  const generatePreview = (data) => {

    if (!data.passengers || !data.dropLocation) {
      setPreview(null);
      return;
    }

    const p = Number(data.passengers);

    let vehicle;
    let fare = 50 + p * 20;

    if (data.dropLocation.toLowerCase().includes("airport")) {
      vehicle = "Premium Sedan ✨";
      fare += 80;
    }
    else if (p <= 2) vehicle = "Bike 🏍️";
    else if (p <= 4) vehicle = "Sedan 🚗";
    else vehicle = "SUV 🚙";

    setPreview({ vehicle, fare });
  };

  // 🔹 Submit booking
  const submitBooking = async () => {

    if (!form.customerName || !form.pickupLocation || !form.dropLocation || !form.passengers) {
      setErrorMsg("⚠️ Please enter all details");
      return;
    }

    try {
      const res = await axios.post(
        "http://localhost:8080/api/bookings/create",
        form
      );

      setResult(res.data);
      alert("Booking Confirmed 🚀");

    } catch {
      alert("Booking failed");
    }
  };

  return (
    <div className="booking-container">

      <button
        className="back-btn"
        onClick={() => navigate("/customer")}
      >
        ⬅ Back to Dashboard
      </button>

      <div className="booking-layout">

        {/* LEFT — FORM */}
        <div className="booking-card">

          <h2>🚗 Book Your Ride</h2>

          {errorMsg && <p className="error-text">{errorMsg}</p>}

          <input
            name="customerName"
            placeholder="Your Name"
            value={form.customerName}
            onChange={handleChange}
          />

          <input
            name="pickupLocation"
            placeholder="Pickup Location"
            value={form.pickupLocation}
            onChange={handleChange}
            onBlur={updateMap}
          />

          <input
            name="dropLocation"
            placeholder="Drop Location"
            value={form.dropLocation}
            onChange={handleChange}
            onBlur={updateMap}
          />

          <input
            type="number"
            name="passengers"
            placeholder="Passengers"
            value={form.passengers}
            onChange={handleChange}
          />

          {preview && (
            <div className="preview-box">
              <p><b>Vehicle:</b> {preview.vehicle}</p>
              <p><b>Estimated Fare:</b> ₹{preview.fare}</p>
            </div>
          )}

          <button className="booking-btn" onClick={submitBooking}>
            Book Now
          </button>

          {result && (
            <div className="result-box">
              <p><b>Vehicle:</b> {result.vehicleType}</p>
              <p><b>Fare:</b> ₹{result.estimatedFare}</p>
              <p><b>Status:</b> {result.status}</p>
            </div>
          )}
        </div>

        {/* RIGHT — MAP */}
        {pickupCoords && dropCoords && (
          <div className="map-panel">

            <div className="map-box">
              <MapContainer
                center={pickupCoords}
                zoom={12}
                style={{ height: "100%", width: "100%" }}
              >
                <TileLayer
                  url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                <Marker position={pickupCoords} />
                <Marker position={dropCoords} />

                <Polyline positions={[pickupCoords, dropCoords]} />
              </MapContainer>
            </div>

            <p style={{ marginTop: "10px", color: "white" }}>
              📏 Distance: <b>{distance}</b>
            </p>

          </div>
        )}

      </div>
    </div>
  );
}

export default BookingForm;