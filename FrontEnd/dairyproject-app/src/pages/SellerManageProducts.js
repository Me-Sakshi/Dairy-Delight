import axios from "axios";
import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { toast } from 'react-toastify';
import axiosInstance from "../axiosInstance";

const SellerManageProducts = () => {

    const [details, setDetails] = useState([]);
    const [sellDetails, setSellDetails] = useState({});
    const [productDetails, setProductDetails] = useState([]);
    const [selcProduct, setSelcProduct] = useState('');
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const { orderDetails } = location.state;
        const { sellDetails } = location.state;
        setDetails(orderDetails);
        AvailableProductList();
        setSellDetails(sellDetails);
    }, []);

    const AddProduct = () => {
        axiosInstance.post('/seller/products/addproducts', {
            emailId: sellDetails.emailId, password: sellDetails.password, productDetails: [selcProduct]
        }).then((response) => {
            const result = response.data;
            toast.success('New product added to list');
            setDetails(result);
        }).catch((error) => {
            toast.error('Select product to add !');
        });
    }

    const AvailableProductList = () => {

        axiosInstance.get('/seller/getallproducts').then((response) => {
            const result = response.data;
            setProductDetails(result);
        }).catch((error) => {
            toast.error('Something Went Wrong !');
        });

    }

    const DeleteProduct = (emailId, pid) => {

        axiosInstance.get('/seller/product/removeproduct', {
            params: {
                emailId, pid
            }
        }).then((response) => {
            const result = response.data;
            toast.info(result);
            navigate('/sellerHome');
        }).catch((error) => {
            toast.error(error.response.data.message);
        });
    }

    return (
        <div className="container-md">
            <div >
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "green" }}>Manage Products</h1>
            </div>

            <table className="table table-hover">
                <thead>
                    <tr className="table-primary">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price / Unit</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    {details.map((details) => {
                        return (
                            <>
                                <tr>
                                    <td>{details.pid}</td>
                                    <td>{details.name}</td>
                                    <td>{details.price}</td>
                                    <td><button className="btn btn-danger btn-sm" onClick={() => DeleteProduct(sellDetails.emailId, details.pid)}>Delete</button></td>
                                </tr>
                            </>
                        )
                    })}
                </tbody>
            </table>
            <div>
                <div class="card text-center" style={{ width: 600, marginLeft: 340, marginTop: 40 }}>
                    <div class="card-header" style={{ fontWeight: 'bolder' }}>
                        Product
                    </div>
                    <div class="card-body">
                        <p class="card-text">Select product from below list to add</p>
                        <div style={{ width: 250, marginLeft: 160 }}>
                            <select class="form-select" aria-label="Default select example" onChange={(event) => setSelcProduct(event.target.value)}>
                                <option selected value="">Select Product</option>
                                {productDetails.map((product) => {
                                    return (
                                        <>
                                            <option value={product.name}>{product.name}</option>
                                        </>
                                    )
                                })}
                            </select>
                        </div>
                        <button class="btn btn-success" style={{ marginTop: 20 }} type="button" onClick={AddProduct}>Add Product</button>
                    </div>
                </div>
            </div>


        </div >
    )

}


export default SellerManageProducts;