import axios from "axios";
import { toast } from "react-toastify";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import axiosInstance from "../axiosInstance";
import { UserContext } from "../context/userContext";

const SellerDashboard = (props) => {
    const userContext = useContext(UserContext);
    const { details } = props;
    const navigate = useNavigate();
    const [proDetails, setProDetails] = useState([]);

    const Logout = () => {
        toast.success('Logout Successfully');
        localStorage.removeItem('access_token');
        localStorage.removeItem('expiresAfter');
        userContext.addUser(null);
        navigate('/sellerLogin');
    }

    const GetProductDetails = (emailId) => {


        if (emailId != null) {

            axiosInstance.get('/seller/getpurchaserecords', {
                params: {
                    emailId
                }
            }).then((response) => {
                const result = response.data;
                navigate('/orderDetails', { state: { purchaseData: result } });
            }).catch((error) => {
                toast.error("Something Wend Wrong !");
            });
        }
    }

    const ManageProducts = (emailId) => {
        axiosInstance.get('/seller/products/getallproducts', {
            params: {
                emailId
            }
        }).then((response) => {
            const result = response.data;
            navigate('/sellerManageProducts', { state: { orderDetails: result, sellDetails: details } });

        }).catch((error) => {
            toast.error('Something Went Wrong !');
        });
    }

    const UpdateStatus = (emailId) => {
        axiosInstance.get('/seller/getpurchaserecords', {
            params: {
                emailId
            }
        }).then((response) => {
            const result = response.data;
            navigate('/changeDeliveryStatus', { state: { proDetails: result } });
        }).catch((error) => {
            toast.error('Error while fetching order details');
        });
    }


    const DeleteAccount = (sellerId) => {
        navigate('/deleteSellerAccount', { state: { sellerId: sellerId } });
    }

    return (
        <div>
            <div className="container-md">
                <h1 style={{ marginBottom: 20, float: "left" }}>Hello, {details.firstName}</h1>
                <div className="d-grid gap-2 d-md-flex justify-content-md-end" style={{ marginTop: 20 }}>
                    <button className="btn btn-outline-primary" onClick={Logout} type="button">Logout</button>
                </div>
                <img src="./images/consumerdashboard.png" className="img-fluid" alt="Consumer Home" style={{ width: 1300, height: 300 }} />
            </div>
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Update Information</h5>
                                <p className="card-text">Update all your profile information such as Name, Address, Phone Number, etc.</p>
                                <Link className="btn btn-outline-success" to="/updateSellerDetails">Update Profile</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Change Password</h5>
                                <p className="card-text">It is always better for security to frequently change your password</p>
                                <Link className="btn btn-outline-danger" to="/changeSellerPassword">Change Password</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Order Details</h5>
                                <p className="card-text">Here you get to see all your sell records </p>
                                <button className="btn btn-outline-info" onClick={() => GetProductDetails(details.emailId)} >Get Details</button>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Change Delivery Status</h5>
                                <p className="card-text">Having any issue with our service ?</p>
                                <button className="btn btn-outline-success" onClick={() => UpdateStatus(details.emailId)}>Update Status </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Manage Products</h5>
                                <p className="card-text">Add or remove products that you added for sell </p>
                                <button className="btn btn-outline-primary" onClick={() => ManageProducts(details.emailId)}>Get Product List</button>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-6">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Raise Query</h5>
                                <p className="card-text">Having any issue with our service ?</p>
                                <Link href="#" className="btn btn-outline-warning" to="/sellerQuery">Get Query Form</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-12">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Delete Account</h5>
                                <p className="card-text">Want to quit selling with us ? You can again register whenever you want <br /> </p>
                                <button className="btn btn-danger" onClick={() => DeleteAccount(details.sellerId)}>Remove Account</button>
                                <p className="card-text">(all information get deleted)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>




            <div style={{ margin: 30 }}>

            </div>
        </div >
    )


}


export default SellerDashboard;