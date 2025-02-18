import axios from "axios";
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";


const SellerProductsDetailsForAdmin = () => {

    const [emailId, setEmailId] = useState('');
    const [productData, setProductData] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const { emailId } = location.state;
        setEmailId(emailId);
        fetchSellerProducts(emailId);
    }, []);

    const fetchSellerProducts = (emailId) => {
        axiosInstance.get('/seller/products/getallproducts', {
            params: {
                emailId
            }
        }).then((response) => {
            const result = response.data;
            setProductData(result);
        }).catch((error) => {
            toast.error('Error while fetching seller product list !');
        });
    }

    const DeleteProduct = (emailId, pid) => {

        axiosInstance.get('/seller/product/removeproduct', {
            params: {
                emailId, pid
            }
        }).then((response) => {
            const result = response.data;
            toast.info('Product removed from seller\'s product list');
            navigate('/allSellerAdmin');
        }).catch((error) => {
            toast.error(error.response.data.message);
        });
    }


    return (
        <div className="container-md">
            <div >
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "green" }}>Manage Seller Products</h1>
            </div>

            <div className="container">
                <table className="table table-hover">
                    <thead>
                        <tr className="table-primary">
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Price / Unit</th>
                            <th>Unit</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        {productData.map((details) => {
                            return (
                                <>
                                    <tr>
                                        <td>{details.pid}</td>
                                        <td>{details.name}</td>
                                        <td>{details.price}</td>
                                        <td>{details.unit}</td>
                                        <td><button className="btn btn-danger btn-sm" onClick={() => DeleteProduct(emailId, details.pid)}>Delete</button></td>
                                    </tr>
                                </>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        </div >

    )
}


export default SellerProductsDetailsForAdmin;