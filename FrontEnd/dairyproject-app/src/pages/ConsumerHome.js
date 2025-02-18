import axios from "axios";
import { toast } from "react-toastify";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import Login from './Login';
import ConsumerDasboard from "./ConsumerDashboard";
import axiosInstance from "../axiosInstance";
import { UserContext } from "../context/userContext";

const ConsumerHome = () => {
    const userContext = useContext(UserContext);
    const navigate = useNavigate();
    const [conDetails, setConDetails] = useState({});


    useEffect(() => {
        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');
        if (!(token && (Date.now() <= expiresAfter) && userContext.userDetails)) {
            navigate('/consumerLogin');
        } else {
            setConDetails(userContext.userDetails);
        }

    }, []);

    return (

        <div>
            {(conDetails?.consumerId > 0) ? <ConsumerDasboard details={conDetails} /> : <h1 style={{ margin: 50 }}>You need to login first </h1>}
        </div>

    )
}

export default ConsumerHome;