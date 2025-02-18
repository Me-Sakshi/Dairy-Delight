import { useEffect, useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import axios from "axios";
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";

const DeleteConsumerAccount = () => {
    const [consumerId, setConsumerId] = useState('');
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const { consumerId } = location.state;
        setConsumerId(consumerId);
    }, []);

    const DeleteFinal = (consumerId) => {
        axiosInstance.get('/admin/removeconsumeraccount', {
            params: {
                consumerId: consumerId
            }
        }).then((response) => {
            const result = response.data;
            sessionStorage.removeItem('Consumer Email');
            navigate('/consumerLogin');
            toast.success(result);
        }).catch((error) => {
            toast.error('Something Went Wrong !');
        });
    }

    const CancelDelete = () => {
        navigate('/consumerHome');
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
                        <button className="btn btn-outline-danger" style={{ width: 80 }} onClick={() => DeleteFinal(consumerId)}>Yes</button>
                        <button className="btn btn-outline-success" style={{ marginLeft: 20, width: 80 }} onClick={CancelDelete}>No</button>
                    </div>
                </div>
            </div>
        </div >
    )
}


export default DeleteConsumerAccount;