import { useState } from "react";
import { registerUser } from "../services/authService";
import { Link, useNavigate } from "react-router-dom";
import "../styles/Register.css";

function Register() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    phone: "",
    role: "CUSTOMER",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      await registerUser(form);
      alert("Registered Successfully!");
      navigate("/"); // go back to login
    } catch (error) {
      alert("Registration Failed");
      console.error("Registration error:", error);
    }
  };

  return (
    <div className="register-container">
      <div className="register-box">
        <h2>Register</h2>

        <form onSubmit={handleRegister} className="register-form">
          <input
            name="name"
            placeholder="Full Name"
            value={form.name}
            onChange={handleChange}
            required
          />

          <input
            name="email"
            type="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
          />

          <input
            name="password"
            type="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <input
            name="phone"
            placeholder="Phone"
            value={form.phone}
            onChange={handleChange}
            required
          />

          <select
            name="role"
            value={form.role}
            onChange={handleChange}
          >
            <option value="CUSTOMER">Customer</option>
            <option value="FLEET_MANAGER">Fleet Manager</option>
            <option value="ADMIN">Admin</option>
          </select>

          <button type="submit">Register</button>
        </form>

        <div className="login-redirect">
          Already have an account?{" "}
          <Link to="/" className="login-link">
            Login
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Register;
