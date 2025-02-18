import axios from "axios";
import { useEffect, useState } from 'react';
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";

const AllPurchaseDetailsForAdmin = () => {

    const [purchaseDetails, setPurchaseDetails] = useState([]);

    useEffect(() => {
        fetchAllPurchaseDetails();
    }, []);

    const fetchAllPurchaseDetails = () => {

        axiosInstance.get('/admin/products/getallpurchasedetails').then((response) => {
            const result = response.data;
            setPurchaseDetails(result);
        }).catch((error) => {
            toast.error('Error while fetching purchase details !');
        });

    }

    return (

        <div className="container-md">
            <div>
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "black" }}>Purchase Details</h1>
            </div>
            <div className="container-md">
                <table className="table table-hover">
                    <thead className="table-secondary">
                        <tr>
                            <th>ID</th>
                            <th>Consumer Name</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Total Price</th>
                            <th>Payment Mode</th>
                            <th>Seller Name</th>
                            <th>Status</th>
                            <th>Transaction ID</th>
                            <th>Date / Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        {purchaseDetails.map((list) => {
                            return (
                                <>
                                    <tr>
                                        <th>{list.purchaseId}</th>
                                        <td>{list.consumerDetails.firstName} {list.consumerDetails.lastName}</td>
                                        <td>{list.productDetails.name}</td>
                                        <td>{list.quantity}</td>
                                        <td>{list.totalPrice}</td>
                                        <td>{list.paymentMode}</td>
                                        <td>{list.sellerDetails.firstName} {list.sellerDetails.lastName}</td>
                                        <th>{list.status}</th>
                                        <td>{list.transactionId}</td>
                                        <td>{list.dateTime}</td>
                                    </tr>
                                </>
                            )
                        })}
                    </tbody>
                </table>
            </div>

        </div>
    )


}


export default AllPurchaseDetailsForAdmin;
