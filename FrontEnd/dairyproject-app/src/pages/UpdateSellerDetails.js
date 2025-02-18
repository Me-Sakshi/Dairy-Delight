import axios from "axios";
import { useContext, useEffect, useState } from 'react';
import { toast } from "react-toastify";
import { useLocation, useNavigate } from 'react-router-dom';
import axiosInstance from "../axiosInstance";
import { UserContext } from "../context/userContext";


const UpdateSellerDetails = () => {

    const [sellDetails, setSellDetails] = useState({});
    const [sellAddress, setSellAddress] = useState({});
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [street, setStreet] = useState('');
    const [district, setDistrict] = useState('');
    const [town, setTown] = useState('');
    const [pincode, setPincode] = useState('');
    const [country, setCountry] = useState('India');
    const [state, setState] = useState('');
    const navigate = useNavigate();
    const userContext = useContext(UserContext);
    useEffect(() => {
        const token = localStorage.getItem('access_token');
        const expiresAfter = localStorage.getItem('expiresAfter');
        if (!(token && (Date.now() <= expiresAfter))) {
            navigate('/sellerLogin');
        } else {
            setSellAddress(userContext.userDetails.address);
            setSellDetails(userContext.userDetails);
        }

    }, []);

    const updateDetails = () => {

        if (firstName == '') {
            toast.warning("Enter First Name");
        } else if (phoneNumber == '') {
            toast.warning("Enter Phone Number");
        } else if (district == '') {
            toast.warning("Enter District");
        } else if (town == '') {
            toast.warning("Enter Town");
        } else if (pincode == '') {
            toast.warning("Enter Pincode");
        } else if (state == '') {
            toast.warning("Enter State");
        } else {

            axiosInstance.post('/seller/updatedetails', {
                firstName, lastName, gender: sellDetails.gender, emailId: sellDetails.emailId, password: sellDetails.password, street, username: sellDetails.username, phoneNumber, age: sellDetails.age, address: {
                    district, town, pincode, state, country
                }
            }).then((response) => {
                const result = response.data;
                userContext.addUser(result);
                toast.success('Profile Information Updated');
                navigate("/sellerHome");
            }).catch((error) => {
                toast.error('Error while updating profile information ! Try again later');
            });
        }

    }

    return (

        <div className="container-md">

            <div>
                <h1 style={{ marginTop: 30, marginBottom: 35, color: "darkblue" }}>Update Profile Information</h1>
            </div>

            <div className="container-md w-50">

                <div className="row row-cols-2 text-start">
                    <div className="col-md-6">
                        <label htmlFor="inputfirstname" class="form-label mb-1">First Name</label>
                        <input type="text" className="form-control" placeholder="Enter First Name" defaultValue={sellDetails.firstName} onMouseMove={(event) => setFirstName(event.target.value)} />
                    </div>
                    <div className="col-md-6">
                        <label htmlFor="inputlastname" class="form-label mb-1">Last Name</label>
                        <input type="text" className="form-control" placeholder="Enter Last Name" defaultValue={sellDetails.lastName} onMouseMove={(event) => setLastName(event.target.value)} />
                    </div>
                </div>

                <div className="row row-cols-2 mt-3 text-start">
                    <div className="col-md-6">
                        <label htmlFor="inputphonenumber" class="form-label mb-1">Phone Number</label>
                        <input type="text" className="form-control" placeholder="Enter Phone number" defaultValue={sellDetails.phoneNumber} onMouseMove={(event) => setPhoneNumber(event.target.value)} />
                    </div>
                    <div className="col-md-6">
                        <label htmlFor="inputstreet" class="form-label mb-1">Street</label>
                        <input type="text" className="form-control" placeholder="Enter street/locality" defaultValue={sellDetails.street} onMouseMove={(event) => setStreet(event.target.value)} />
                    </div>
                </div>
                <div className="row row-cols-2 mt-3 text-start">
                    <div className="col-md-6">
                        <label htmlFor="inputDistrict" class="form-label mb-1">District</label>
                        <input type="text" className="form-control" placeholder="Enter District" defaultValue={sellAddress.district} onMouseMove={(event) => setDistrict(event.target.value)} />
                    </div>
                    <div className="col-md-6">
                        <label htmlFor="inputTown" class="form-label mb-1">Town</label>
                        <input type="text" className="form-control" placeholder="Enter Town" defaultValue={sellAddress.town} onMouseMove={(event) => setTown(event.target.value)} />
                    </div>
                </div>
                <div className="row row-cols-2 mt-3 text-start">
                    <div className="col-md-6">
                        <label htmlFor="inputPincode" class="form-label mb-1">Pincode</label>
                        <input type="text" className="form-control" placeholder="Enter Pincode" defaultValue={sellAddress.pincode} onMouseMove={(event) => setPincode(event.target.value)} />
                    </div>
                    <div class="col-md-6">
                        <label htmlFor="inputState" class="form-label mb-1">State</label>
                        <select id="inputState" class="form-select" defaultValue={sellAddress.state} onMouseMove={(event) => setState(event.target.value)}>
                            <option disabled selected >Choose State</option>
                            <option value="Maharashtra">Maharashtra</option>
                            <option value="Delhi">Delhi</option>
                            <option value="Goa">Goa</option>
                            <option value="Haryana">Haryana</option>
                            <option value="Uttar Pradesh">Uttar Pradesh</option>
                            <option value="Madhya Pradesh">Madhya Pradesh</option>
                            <option value="Odisha">Odisha</option>
                            <option value="Telangana">Telangana</option>
                            <option value="Rajasthan">Rajasthan</option>
                        </select>
                    </div>

                    <div className="col-md-12">
                        <label htmlFor="validationCustom04" className="form-label row g-0">Country*</label>
                        <select className="form-select" id="validationCustom04" required disabled >
                            <option value="India">India</option>
                        </select>
                        <div className="invalid-feedback">
                            Please select your country.
                        </div>
                    </div>

                </div>

                <div className="row offset-md-4 mb-4">
                    <button type="submit" className="mt-4 btn btn-outline-primary col-md-6" onClick={updateDetails}>Update Details</button>
                </div>
            </div >
        </div >

    );

}

export default UpdateSellerDetails;







