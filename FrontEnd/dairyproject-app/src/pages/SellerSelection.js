import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { toast } from "react-toastify";
import SellerDashboard from './SellerDashboard';
import { UserContext } from "../context/userContext";

const SellerSelection = () => {
    const userContext = useContext(UserContext);
    const [sellerPresent, setSellerPresent] = useState({});
    const navigate = useNavigate();
    useEffect(() => {
        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');
        if ((token && (Date.now() <= expiresAfter) && userContext.userDetails) ) {
            navigate('/sellerHome');
        }
    });

    return (
        <div>
            {(sellerPresent.sellerId > 0) ? <SellerDashboard details={sellerPresent} /> : <><div className="container-md">
                <h1 style={{ margin: 30 }}>Become a Seller and start making profit</h1>
                <img src="./images/becomeseller.webp" className="img-fluid" alt="Consumer Home" style={{ width: 1300, height: 320 }} />
            </div><div className="container-sm">
                    <div className="row">
                        <div className="col-sm-6">
                            <div className="card">
                                <div className="card-body">
                                    <h5 className="card-title">Start selling products with us </h5>
                                    <p className="card-text">Dont Worry ! Just click button below, fill information and you are ready to sell</p>
                                    <Link to='/sellerRegistration' className="btn btn-outline-primary">Goto Seller Registration</Link>
                                </div>
                            </div>
                        </div>
                        <div className="col-sm-6">
                            <div className="card">
                                <div className="card-body">
                                    <h5 className="card-title">Already have an account !</h5>
                                    <p className="card-text">Click below button to proceed login page </p>
                                    <Link to='/sellerLogin' className="btn btn-outline-success">Goto Seller Login</Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div></>}
        </div>
    )

}



export default SellerSelection;