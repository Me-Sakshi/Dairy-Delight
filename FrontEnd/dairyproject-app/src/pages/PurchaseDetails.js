import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";


const PurchaseDetails = () => {
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
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "darkblue" }}>Purchase Details</h1>
            </div>


            <table className="table table-hover">
                <thead>
                    <tr className="table-success">
                        <th>Purchase ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Total Amount (Rs.)</th>
                        <th>Seller Name</th>
                        <th>Payment Mode</th>
                        <th>Status</th>
                        <th>Transaction ID</th>
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
                                    <td>{details.sellerDetails.firstName} {details.sellerDetails.lastName}</td>
                                    <td>{details.paymentMode}</td>
                                    <th>{details.status}</th>
                                    <td>{details.transactionId}</td>
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


export default PurchaseDetails;