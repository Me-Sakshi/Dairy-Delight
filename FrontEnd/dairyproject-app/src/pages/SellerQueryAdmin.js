import axios from "axios"
import { useEffect, useState } from "react"
import { toast } from 'react-toastify';
import axiosInstance from "../axiosInstance";



const SellerQueryAdmin = () => {

    const [queryData, setQueryData] = useState([]);

    useEffect(() => {
        getAllSellerQuery();
    }, []);

    const getAllSellerQuery = () => {

        axiosInstance.get('/admin/seller/query').then((response) => {
            const result = response.data;
            setQueryData(result);
        }).catch((error) => {
            toast.error('Failed to fecth Seller Queries right now !');
        });
    }



    return (

        <div className="container-md">
            <div>
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "darkorange" }}>Query Submitted By Seller's</h1>
            </div>

            <table className="table table-hover">
                <thead className="table-secondary">
                    <tr>
                        <th>ID</th>
                        <th>Query Message</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Date / Time</th>
                    </tr>
                </thead>
                <tbody>
                    {queryData.map((list) => {
                        return (
                            <>
                                <tr>
                                    <th>{list.qid}</th>
                                    <td style={{ width: 600 }}>{list.message}</td>
                                    <td>{list.sellerDetails.firstName} {list.sellerDetails.lastName}</td>
                                    <td>{list.sellerDetails.emailId}</td>
                                    <td>{list.sellerDetails.phoneNumber}</td>
                                    <td>{list.dateTime}</td>
                                </tr>
                            </>
                        )
                    })}
                </tbody>
            </table>
        </div>


    )
}


export default SellerQueryAdmin;