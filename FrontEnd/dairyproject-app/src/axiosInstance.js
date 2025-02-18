import axios from "axios";

// Create an instance of Axios
const axiosInstance = axios.create({
  baseURL: "http://localhost:9090", // Replace with your API URL
  withCredentials: true, // Include cookies (if needed)
});

// Add an interceptor to attach the Authorization token
axiosInstance.interceptors.request.use(
  (config) => {
    // Get the token from localStorage (or sessionStorage, cookies, etc.)
    const token = localStorage.getItem("access_token"); // Adjust storage as needed

    if (token) {
      config.headers.Authorization = token; // Set Authorization header
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;