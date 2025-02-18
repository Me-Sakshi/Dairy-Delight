import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";
import { UserContext } from "../context/userContext";


const PurchaseProduct = () => {
    const navigate = useNavigate();
    const [productData, setProductData] = useState([]);
    const [productName, setProductName] = useState('');
    const [productPrice, setProductPrice] = useState('');
    const [quantity, setQuantity] = useState('');
    const [paymentMode, setPaymentMode] = useState('');
    const userContext = useContext(UserContext);

    useEffect(() => {
        getAllProductList();
    }, []);

    const getAllProductList = () => {
        axiosInstance.get('/consumer/getallproducts').then((response) => {
            const result = response.data;
            setProductData(result);
        }).catch((error) => {
            toast.error('Error while fetching product details');
        });
    }

    const ProductPrice = (proName) => {
        if (proName == "") {
            setProductPrice('');
        }
        for (let i = 0; i < productData.length; i++) {
            if (productData[i].name == proName) {
                setProductPrice(productData[i].price);
            }
        }
    }

    const SearchSeller = () => {

        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');

        if (!(token && (Date.now() <= expiresAfter) && userContext.userDetails)) {
            toast.warning('Please login to continue your purchase !');
            navigate('/consumerLogin');
        } else if (productName === '') {
            toast.warning('Select Product to Buy');
        } else if (quantity == '') {
            toast.warning('Please Enter Quantity');
        } else if (parseInt(quantity) < 1) {
            toast.warning('Please Enter Correct Quantity');
        } else if (paymentMode === '') {
            toast.warning('Please Select Payment Mode');
        } else {
            navigate('/consumerSellerSelection', { state: { ProductName: productName, paymentMode: paymentMode, quantity: quantity } });
        }
    }



    return (

        <div className="container-md">

            <div className="card mb-3" style={{ marginTop: 50, marginLeft: 245, width: 780 }}>
                <img src="./images/banner.jpg" className="card-img-top" alt="banner" style={{ height: 300 }} />
                <div className="card-body">
                    <h5 className="card-title">Purchase Details </h5><hr />
                    <div>
                        <div className="row g-3">
                            <div className="col-sm-4">
                                <select className="form-select" aria-label="Default select example" onChange={(event) => setProductName(event.target.value)} onBlur={() => ProductPrice(productName)}>
                                    <option selected Value="">Choose Product</option>
                                    {productData.map((product) => {
                                        return (
                                            <>
                                                <option value={product.name} >{product.name} ({product.unit})</option>
                                            </>
                                        )
                                    })}
                                </select>
                            </div>
                            <div className="col-sm-4">
                                <div className="input-group">
                                    <div className="input-group-text">Quantity</div>
                                    <input type="number" min={0} max={100} className="form-control" placeholder="Upto 100" onChange={(event) => setQuantity(event.target.value)} />
                                </div>
                            </div>
                            <div className="col-sm-4">
                                <select className="form-select" aria-label="Default select example" onChange={(event) => setPaymentMode(event.target.value)}>
                                    <option selected defaultValue="">Select Payment Mode</option>
                                    <option value="UPI">UPI ID</option>
                                    <option value="Credit">Credit Card</option>
                                    <option value="Debit">Debit Card</option>
                                    <option value="COD">COD (Cash on Delivery)</option>
                                </select>
                            </div>
                            <div className="col-12" style={{ marginLeft: 260 }}>
                                <button className="btn btn-outline-primary" onClick={SearchSeller}>Search Nearby Seller</button>
                            </div>
                            <div className="col-4" style={{ marginTop: -38, marginLeft: 0 }}>
                                <h3>Price - {productPrice}</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    )
}


export default PurchaseProduct;