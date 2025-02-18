import { useEffect, useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import axios from "axios";
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";

const DeleteSellerAccount = () => {
    const [sellerId, setSellerId] = useState('');
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const { sellerId } = location.state;
        setSellerId(sellerId);
    }, []);

    const DeleteFinal = (sellerId) => {
        axiosInstance.get('/admin/removeselleraccount', {
            params: {
                sellerId: sellerId
            }
        }).then((response) => {
            const result = response.data;
            sessionStorage.removeItem('Seller Email');
            navigate('/sellerLogin');
            toast.success(result);
        }).catch((error) => {
            toast.error('Something Went Wrong !');
        });
    }

    const CancelDelete = () => {
        navigate('/sellerHome');
    }



    return (
        <div>
            <div className="container-md" style={{ width: 600, marginTop: 100 }}>
                <div className="card text-center">
                    <div className="card-header" style={{ color: "red", fontWeight: 700, fontSize: 25, backgroundColor: "lightgoldenrodyellow" }}>
                        Delete Account Confirmation
                    </div>
                    <div className="card-body">
                        <p className="card-text">You are going to delete your account, which deletes all your data. <br /> Are you sure ? </p>
                        <button className="btn btn-outline-danger" style={{ width: 80 }} onClick={() => DeleteFinal(sellerId)}>Yes</button>
                        <button className="btn btn-outline-success" style={{ marginLeft: 20, width: 80 }} onClick={CancelDelete}>No</button>
                    </div>
                </div>
            </div>
        </div >
    )
}


export default DeleteSellerAccount;