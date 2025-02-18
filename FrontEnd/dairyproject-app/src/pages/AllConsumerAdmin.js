import axios from "axios";
import { toast } from 'react-toastify';
import { useState } from 'react';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from "../axiosInstance";


const AllConsumerAdmin = () => {

    const [consumerDetails, setConsumerDetails] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchAllConsumerDetails();
    }, []);

    const fetchAllConsumerDetails = () => {

        axiosInstance.get('/admin/fetchallconsumers').then((response) => {
            const result = response.data;
            setConsumerDetails(result);
        }).catch((error) => {
            toast.error('Error fetching registered consumer');
        });

    }

    const DeleteConsumer = (consumerId) => {

        axiosInstance.get('/admin/removeconsumeraccount', {
            params: {
                consumerId
            }
        }).then((response) => {
            const result = response.data;
            toast.success(result);
            navigate('/adminHome');
        }).catch((error) => {
            toast.error('Error while deleting consumer account !');
        });

    }

    return (

        <div className="container-md">
            <div>
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "blue" }}>Consumers Detail</h1>
            </div>
            <div className="container">
                <table className="table table-hover">
                    <thead>
                        <tr className="table-light">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Phone</th>
                            <th>Street</th>
                            <th>Town</th>
                            <th>District</th>
                            <th>Pincode</th>
                            <th>State</th>
                            <th>Country</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        {consumerDetails.map((list) => {
                            return (
                                <>
                                    <tr>
                                        <th>{list.consumerId}</th>
                                        <td>{list.firstName} {list.lastName}</td>
                                        <td>{list.emailId}</td>
                                        <td>{list.gender}</td>
                                        <td>{list.phoneNumber}</td>
                                        <td>{list.street}</td>
                                        <td>{list.address.town}</td>
                                        <td>{list.address.district}</td>
                                        <td>{list.address.pincode}</td>
                                        <td>{list.address.state}</td>
                                        <td>{list.address.country}</td>
                                        <td>
                                            <div>
                                                <button className="btn btn-outline-danger btn-sm" onClick={() => DeleteConsumer(list.consumerId)}>Remove</button>
                                            </div>
                                        </td>
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


export default AllConsumerAdmin;