import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";


const OrderDetails = () => {
    const location = useLocation();
    const [details, setDetails] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const { purchaseData } = location.state;
        setDetails(purchaseData);
    }, []);

    return (
        <div className="container-md">

            <div>
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "darkblue" }}>Order Details</h1>
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
                        <th>Contact</th>
                        <th>Payment Mode</th>
                        <th>Date/Time</th>
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
                                    <td>{details.consumerDetails.phoneNumber}</td>
                                    <td>{details.paymentMode}</td>
                                    <td>{details.dateTime}</td>
                                </tr>
                            </>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}


export default OrderDetails;