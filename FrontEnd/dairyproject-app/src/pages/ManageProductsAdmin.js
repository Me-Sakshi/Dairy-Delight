import axios from "axios";
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import axiosInstance from "../axiosInstance";



const ManageProductsAdmin = () => {

    const [productData, setProductData] = useState([]);
    const [unit, setUnit] = useState('');
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchAllProducts();
    }, []);

    const fetchAllProducts = () => {
        axiosInstance.get('/consumer/getallproducts').then((response) => {
            const result = response.data;
            setProductData(result);
        }).catch((error) => {
            toast.error('Error while fetching product list');
        });
    }

    const DeleteProduct = (pid) => {

        axiosInstance.get('/admin/products/removeproduct', {
            params: {
                pid
            }
        }).then((response) => {
            const result = response.data;
            toast.success(result);
            navigate('/adminHome');
        }).catch((error) => {
            toast.error('Something went wrong while removing product ! Please again later');
        });
    }

    const AddProduct = () => {

        if (name === '') {
            toast.warning('Enter Correct Product Name');
        } else if (price === '' || parseFloat(price) < 8) {
            toast.warning('Enter Correct Product Price');
        } else if (unit === '') {
            toast.warning('Select Appropriate Unit');
        } else {
            axiosInstance.post('/admin/products/addnewproduct', {
                name, price, unit
            }).then((response) => {
                const result = response.data;
                toast.success(result);
                navigate('/adminHome');
            }).catch((error) => {
                toast.error('Error while adding product !');
            });

        }
    }

    return (

        <div className="container-md">

            <div >
                <h1 style={{ marginTop: 20, marginBottom: 25, color: "green" }}>Manage Products</h1>
            </div>
            <div>
                <table className="table table-hover" >
                    <thead>
                        <tr className="table-secondary">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price (Rs.)</th>
                            <th>Unit</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        {productData.map((data) => {
                            return (
                                <>
                                    <tr>
                                        <th>{data.pid}</th>
                                        <td>{data.name}</td>
                                        <td>{data.price}</td>
                                        <td>{data.unit}</td>
                                        <td>
                                            <div>
                                                <button className="btn btn-outline-danger" onClick={() => (DeleteProduct(data.pid))}>Remove</button>
                                            </div>
                                        </td>
                                    </tr>
                                </>
                            )
                        })}
                    </tbody>
                </table>
            </div>

            <div>
                <div className="card text-center" style={{ width: 640, marginLeft: 320, marginTop: 40, marginBottom: 35 }}>
                    <div className="card-header" style={{ fontWeight: 'bolder' }}> Add New Product</div>
                    <div className="card-body">
                        <div>
                            <form class="row gy-2 gx-3 align-items-center">
                                <div class="col-5">
                                    <div class="input-group">
                                        <div class="input-group-text">Name</div>
                                        <input type="text" class="form-control" id="autoSizingInputGroup" placeholder="Product Name" onChange={(event) => setName(event.target.value)} />
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div class="input-group">
                                        <div class="input-group-text">Price</div>
                                        <input type="number" min={8} class="form-control" id="autoSizingInputGroup" placeholder="> 8" onChange={(event) => setPrice(event.target.value)} />
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="input-group">
                                        <div class="input-group-text">Unit</div>
                                        <select class="form-select" id="autoSizingSelect" onChange={(event) => setUnit(event.target.value)}>
                                            <option selected value="">Choose</option>
                                            <option value="L">L</option>
                                            <option value="kg">kg</option>
                                        </select>
                                    </div>
                                </div>
                                <div>
                                    <button className="btn btn-success" style={{ marginTop: 10 }} type="button" onClick={AddProduct}>Add Product</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )

}


export default ManageProductsAdmin;
