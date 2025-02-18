import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import ConsumerDasboard from './ConsumerDashboard';
import axiosInstance from "../axiosInstance";
import { UserContext } from "../context/userContext";


const Login = () => {
    const userContext = useContext(UserContext);
    const navigate = useNavigate();
    const [emailId, setEmailId] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');
        if (token && (Date.now() <= expiresAfter) && userContext.userDetails) {
            navigate('/consumerHome');
        }
    });

    const LoginCon = () => {

        if (emailId === '') {
            toast.error('Enter Valid Email ID');
        } else if (password === '') {
            toast.error('Enter Password');
        } else {
            axios.post('http://localhost:9090/consumer/fetchdetailsbyemail', {
                emailId, password
            }).then((response) => {
                const result = response.data;
                if (result.consumerId > 0) {
                    userContext.addUser(result);
                    localStorage.setItem("access_token", response.headers["authorization"]);
                    localStorage.setItem("expiresAfter", Date.now() + 30 * 60 * 1000);
                    sessionStorage.setItem("Consumer Email", result.emailId);
                    toast.success('Login Successful');
                    navigate('/consumerHome');
                } else {
                    toast.warning('Something went wrong !');
                }
            }).catch((error) => {
                toast.warning('Incorrect Email or Password !');
                // toast.error(error.response.data.message);
            });
        }

    }

    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div className="card border-1 shadow rounded-3 my-5">
                            <div className="card-body p-4 p-sm-5">
                                <h5 className="card-title text-center mb-4 fw-dark fs-3">Consumer Login</h5>

                                <div className="form-floating mb-2">
                                    <input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" onChange={(event) => setEmailId(event.target.value)} />
                                    <label htmlFor="floatingInput">Email address</label>
                                </div>
                                <div className="form-floating mb-3">
                                    <input type="password" className="form-control" id="floatingPassword" placeholder="Password" onChange={(event) => setPassword(event.target.value)} />
                                    <label htmlFor="floatingPassword">Password</label>
                                </div>

                                <div className="form-check mb-4">
                                    <label className="form-check-label" htmlFor="rememberPasswordCheck">Dont have an account ? <Link to='/consumerRegistration'>Create account here</Link> </label>
                                </div>
                                <div className="d-grid">
                                    <button className="btn btn-outline-success btn-login text-uppercase fw-bold" onClick={LoginCon}>Sign
                                        in</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div >
    )


}

export default Login;