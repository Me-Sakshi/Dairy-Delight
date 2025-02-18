import axios from "axios";
import { toast } from "react-toastify";
import { Link, Navigate, useLocation, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import SellerDashboard from './SellerDashboard';
import axiosInstance from "../axiosInstance";
import { UserContext } from "../context/userContext";

const SellerHome = () => {

    const [sellDetails, setSellDetails] = useState({});
    const userContext = useContext(UserContext);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');
        if (!(token && (Date.now() <= expiresAfter))) {
            navigate('/sellerLogin');
        } else if(userContext?.userDetails) {
            setSellDetails(userContext.userDetails);
        } else {
            navigate('/sellerLogin');
        }
    }, []);

    return (
        <div>
            {(sellDetails.sellerId > 0) ? <SellerDashboard details={sellDetails} /> : <h1 style={{ margin: 50 }}>You need to login first </h1>}
        </div>

    )
}

export default SellerHome;