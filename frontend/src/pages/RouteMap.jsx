import { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Polyline, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";

import L from "leaflet";
import markerIcon from "leaflet/dist/images/marker-icon.png";
import markerShadow from "leaflet/dist/images/marker-shadow.png";

// ⭐ Fix marker icons
const DefaultIcon = L.icon({
  iconUrl: markerIcon,
  shadowUrl: markerShadow
});
L.Marker.prototype.options.icon = DefaultIcon;


function RouteMap({ route }) {
  const [coords, setCoords] = useState([]);

  // ⭐ Convert location names → coordinates
  const geocodeLocation = async (place) => {
    const query = `${place}, Chennai, Tamil Nadu, India`;
    const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(query)}`;


    const response = await fetch(url);
    const data = await response.json();

    if (data.length > 0) {
      return [parseFloat(data[0].lat), parseFloat(data[0].lon)];
    }

    return null;
  };

  useEffect(() => {
    if (!route || route.length === 0) return;

    const fetchCoords = async () => {
      const results = await Promise.all(
        route.map((loc) => geocodeLocation(loc))
      );

      setCoords(results.filter(Boolean));
    };

    fetchCoords();
  }, [route]);

  if (coords.length === 0) return null;

  return (
    <div className="optimization-card">
      <h3>🗺️ Route Map</h3>

      <MapContainer
        bounds={coords}
        boundsOptions={{ padding: [50, 50] }}
        style={{ height: "420px", width: "100%", borderRadius: "10px" }}
      >
        <TileLayer
          attribution="© OpenStreetMap"
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />

        {/* 📍 Markers */}
        {coords.map((pos, index) => (
          <Marker key={index} position={pos}>
            <Popup>
              {index === 0 && "🚀 Pickup: "}
              {index === coords.length - 1 && index !== 0 && "🏁 Destination: "}
              {route[index]}
            </Popup>
          </Marker>
        ))}

        {/* 🔵 Route Line */}
        <Polyline positions={coords} color="#2563eb" weight={5} />
      </MapContainer>
    </div>
  );
}

export default RouteMap;
