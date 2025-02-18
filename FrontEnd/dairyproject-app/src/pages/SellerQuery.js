import axios from "axios";
import { useState } from "react";
import { toast } from 'react-toastify';
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axiosInstance from "../axiosInstance";


const SellerQuery = () => {
    const navigate = useNavigate();
    const [message, setMessage] = useState('');


    const submitQuery = () => {

        if (message == '') {
            toast.warning('Please enter your query before submiting !');
        }

        axiosInstance.post('/seller/query/submitquery', {
            emailId: sessionStorage.getItem('Seller Email'), message
        }).then((response) => {
            const result = response.data;
            toast.info(result);
            navigate('/sellerHome');
        }).catch((error) => {
            toast.error('Error while submitting Query ! Please try again later');
        });

    }

    return (

        <div className="container-md">

            <div className="container-md w-50">

                <div className="container-md" style={{ marginTop: 50, marginBottom: 30 }}>
                    <h1>Seller Query Form</h1>
                </div>

                <div className="row mt-2 d-grid col-12">
                    <label for="Message" className="text-start form-label">Please Enter Query Here</label>
                    <textarea className="row ms-2 form-control" id="message" name="message" rows="8" onChange={(event) => setMessage(event.target.value)}></textarea>
                </div>

                <div className="d-grid col-6 mx-auto mt-4 mb-3">
                    <button type="button" className="ps-1 btn btn-outline-primary" onClick={submitQuery}>Submit Query</button>
                </div>

            </div>

        </div >

    );
}

export default SellerQuery;