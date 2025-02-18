import axios from "axios";
import { toast } from "react-toastify";
import { Link, Navigate, useLocation, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../context/userContext";

const AdminHome = () => {
    const userContext = useContext(UserContext);
    const navigate = useNavigate();

    const Logout = () => {
        toast.success('Admin Logout Successfull');
        localStorage.removeItem('access_token');
        localStorage.removeItem('expiresAfter');
        userContext.addUser(null);
        navigate('/adminLogin');
    }

    useEffect(() => {
        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');
        if (!(token && (Date.now() <= expiresAfter) && userContext.userDetails) ) {
            navigate('/adminLogin');
        }
    });

    return (
        <div>
            <div className="container-md">
                <h1 style={{ marginLeft: 445, marginBottom: 20, float: "left" }}>Welcome Administrator</h1>
                <div className="d-grid gap-2 d-md-flex justify-content-md-end" style={{ marginTop: 20 }}>
                    <button className="btn btn-outline-primary" onClick={Logout} type="button">Logout</button>
                </div><br />
            </div >
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Manage Products</h5>
                                <p className="card-text">Add / remove products to make available for sell </p>
                                <Link className="btn btn-outline-success" to="/manageProductsAdmin">Manage Products</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Get Consumers Detail</h5>
                                <p className="card-text">Manage all registered consumers</p>
                                <Link className="btn btn-outline-primary" to="/allConsumerAdmin">Consumers Detail</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Get Sellers Detail</h5>
                                <p className="card-text">Manage all registered sellers</p>
                                <Link className="btn btn-outline-warning" to="/allSellerAdmin">Sellers Detail</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Purchase Details</h5>
                                <p className="card-text">Get all Purchase Records </p>
                                <Link className="btn btn-outline-dark" to="/allPurchaseDetailsForAdmin">Show Record</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Deleted Consumers Record</h5>
                                <p className="card-text">Get all deleted consumer history</p>
                                <Link className="btn btn-outline-danger" to="/deletedConsumerRecord">Get Record</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Deleted Sellers Record</h5>
                                <p className="card-text">Get all deleted seller history <br /> </p>
                                <Link className="btn btn-outline-primary" to="/deletedSellerRecord">Get Record</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="container-md" style={{ marginTop: 20 }}>
                <div className="row">
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Consumer Queries</h5>
                                <p className="card-text">Get all Queries submited by Consumers </p>
                                <Link className="btn btn-outline-warning" to="/consumerQueryAdmin">Get Queries</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Seller Queries</h5>
                                <p className="card-text">Get all Queries submited by Sellers</p>
                                <Link className="btn btn-outline-primary" to="/sellerQueryAdmin">Get Queries</Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">Change Password</h5>
                                <p className="card-text">Secure account by frquently changing password</p>
                                <Link className="btn btn-outline-danger" to="/changeAdminPassword">Change Password</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div >
    )


}

export default AdminHome;