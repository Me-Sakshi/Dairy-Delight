import axios from "axios";
import { useState } from "react";
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import axiosInstance from "../axiosInstance";

const ChangeSellerPassword = () => {

    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate();

    const changePassword = () => {
        axiosInstance.post('/seller/changepassword', {
            emailId: sessionStorage.getItem("Seller Email"), oldPassword, newPassword, confirmPassword
        }).then((response) => {
            const result = response.data;
            toast.success(result);
            navigate('/sellerHome');
        }).catch((error) => {
            toast.error(error.response.data.message);
        });
    }

    return (

        <div className="container-md">
            <div>
                <h1 style={{ marginTop: 30, marginBottom: 35, color: "red" }}>Change Password</h1>
            </div>

            <div className="container-md w-25">
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingInput" placeholder="name@example.com" onChange={(event) => setOldPassword(event.target.value)} />
                    <label for="floatingInput">Old Password</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" onChange={(event) => setNewPassword(event.target.value)} />
                    <label for="floatingPassword">New Password</label>
                </div>
                <div class="form-floating mb-3" >
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" onChange={(event) => setConfirmPassword(event.target.value)} />
                    <label for="floatingPassword">Confirm Password</label>
                </div>
                <button className="btn btn-danger" onClick={changePassword}>Change Password</button>
            </div>

        </div>

    )
}


export default ChangeSellerPassword;