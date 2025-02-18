import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";

const ChangeDeliveryStatus = () => {
    const location = useLocation();
    const [details, setDetails] = useState([]);
    const [status, setStatus] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const { proDetails } = location.state;
        setDetails(proDetails);
    }, []);

    const UpdateStatus = (purchaseId) => {
        axiosInstance.get('/seller/changedeliverystatus', {
            params: {
                purchaseId, status
            }
        }).then((response) => {
            const result = response.data;
            toast.success('Status Updated');
            navigate('/sellerHome');
        }).catch((error) => {
            toast.error('Select Status to update !');
        });
    }

    return (
        <div className="container-md">

            <div>
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "darkgreen" }}>Change Delivery Status</h1>
            </div>

            <table className="table table-hover">
                <thead>
                    <tr className="table-dark">
                        <th>Purchase ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Total Amount (Rs.)</th>
                        <th>Consumer Name</th>
                        <th>Delivery Address</th>
                        <th>Current Status</th>
                        <th>Update Status</th>
                        <th>Submit</th>
                    </tr>
                </thead>
                <tbody>
                    {details.map((details) => {
                        return (
                            <>
                                <tr>
                                    <th>{details.purchaseId}</th>
                                    <td>{details.productDetails.name}</td>
                                    <td>{details.quantity}</td>
                                    <td>{details.totalPrice}</td>
                                    <td>{details.consumerDetails.firstName} {details.consumerDetails.lastName}</td>
                                    <td>{details.consumerDetails.street}, {details.consumerDetails.address.town}, {details.consumerDetails.address.pincode}</td>
                                    <td>{details.status}</td>
                                    <td>
                                        <div style={{ width: 110, marginLeft: 22 }}>
                                            <select className="form-select form-select-sm" aria-label=".form-select-sm example" onClick={(event) => setStatus(event.target.value)}>
                                                <option selected value=''>Choose</option>
                                                {/* <option value="Placed">Placed</option> */}
                                                <option value="Shipped">Shipped</option>
                                                <option value="Delivered">Delivered</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div>
                                            <button className="btn btn-success btn-sm" onClick={() => UpdateStatus(details.purchaseId)}>Update</button>
                                        </div>
                                    </td>
                                </tr>
                            </>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default ChangeDeliveryStatus;