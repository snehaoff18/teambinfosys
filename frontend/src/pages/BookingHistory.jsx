import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/BookingHistory.css";

function BookingHistory() {

  const navigate = useNavigate();

  const [bookings, setBookings] = useState([]);

  // 🔹 Change name as needed (later from login)
  const customerName = "Sneha";

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/bookings/customer/${customerName}`
      );
      setBookings(res.data);
    } catch (error) {
      console.error(error);
      alert("Failed to load bookings");
    }
  };

  return (
    <div className="history-container">

      {/* 🔙 Back Button */}
      <button
        className="back-btn"
        onClick={() => navigate("/customer")}
      >
        ⬅ Back to Dashboard
      </button>

      <div className="history-card">

        <h2>📜 Booking History</h2>

        {bookings.length === 0 ? (
          <p>No bookings found</p>
        ) : (
          <table className="history-table">
            <thead>
              <tr>
                <th>Pickup</th>
                <th>Drop</th>
                <th>Vehicle</th>
                <th>Fare</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
  {bookings
    .filter(b => b && b.pickupLocation && b.dropLocation)
    .map((b, index) => (
      <tr key={index}>
        <td>{b.pickupLocation}</td>
        <td>{b.dropLocation}</td>
        <td>{b.vehicleType}</td>
        <td>₹{b.estimatedFare}</td>
        <td>{b.status}</td>
      </tr>
  ))}
</tbody>
          </table>
        )}

      </div>
    </div>
  );
}

export default BookingHistory;