import axios from "axios"
import { useEffect, useState } from "react"
import { toast } from 'react-toastify';
import axiosInstance from "../axiosInstance";



const ConsumerQueryAdmin = () => {

    const [queryData, setQueryData] = useState([]);

    useEffect(() => {
        getAllConsumerQuery();
    }, []);

    const getAllConsumerQuery = () => {

        axiosInstance.get('/admin/consumer/query').then((response) => {
            const result = response.data;
            setQueryData(result);
        }).catch((error) => {
            toast.error('Failed to fecth Consumer Queries right now !');
        });
    }



    return (

        <div className="container-md">
            <div>
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "darkorange" }}>Query Submitted By Consumer's</h1>
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
                                    <td>{list.consumerDetails.firstName} {list.consumerDetails.lastName}</td>
                                    <td>{list.consumerDetails.emailId}</td>
                                    <td>{list.consumerDetails.phoneNumber}</td>
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

export default ConsumerQueryAdmin;
