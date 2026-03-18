import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { loginUser } from "../services/authService";
import "../styles/Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const { data } = await loginUser({ email, password });
      const token = data?.data;

      if (!token) throw new Error("Token not received");

      localStorage.setItem("token", token);

      const { role } = jwtDecode(token);

      switch (role) {
        case "ADMIN":
          navigate("/admin");
          break;
        case "FLEET_MANAGER":
          navigate("/fleet");
          break;
        default:
          navigate("/customer");
      }
    } catch (error) {
      alert("Invalid Email or Password");
      console.error(error);
    }
  };

  return (
    <div className="page">
      <h1 className="welcome-heading">Welcome to NeuroFleet</h1>

      <div className="login-container">
        <div className="login-box">
          <h2>Login</h2>

          <form onSubmit={handleLogin} className="login-form">
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            <button type="submit" className="login-btn">
              Login
            </button>
          </form>

          <div className="register-section">
            <p>Don't have an account?</p>
            <Link to="/register" className="register-btn">
              Register
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
