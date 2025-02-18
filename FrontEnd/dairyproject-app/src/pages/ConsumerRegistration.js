import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axiosInstance from "../axiosInstance";

const ConsumerRegistration = () => {
    const Navigate = useNavigate();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [username, setUsername] = useState('');
    const [emailId, setEmailId] = useState('');
    const [password, setPassword] = useState('');
    const [gender, setGender] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [street, setStreet] = useState('');
    const [district, setDistrict] = useState('');
    const [state, setState] = useState('');
    const [pincode, setPincode] = useState('');
    const [country, setCountry] = useState('India');
    const [town, setTown] = useState('');

    const Register = () => {

        if (firstName === '') {
            toast.error('Enter First Name');
        } else if (username === '') {
            toast.error('Enter Username');
        } else if (emailId === '') {
            toast.error('Enter valid Email ID');
        } else if (password === '') {
            toast.error('Enter Correct Passoword');
        } else if (gender === '') {
            toast.error('Select your Gender');
        } else if (phoneNumber === '') {
            toast.error('Enter Your Correct Phone Number');
        } else if (district === '') {
            toast.error('Enter Your District');
        } else if (pincode === '') {
            toast.error('Enter Correct Pincode');
        } else if (state === '') {
            toast.error('Select Your State');
        } else {

            axios.post('http://localhost:9090/consumer/registerdetails', {
                firstName, lastName, emailId, password, username, gender, street, phoneNumber, address: { pincode, district, town, state, country }
            }).then((response) => {
                const result = response.data;
                toast.success('Hello ' + firstName + '! Registration Successful. Please login');
                Navigate('/consumerLogin', { state: { consumerDetails: result } })
            }).catch((error) => {
                toast.warning(error.response.data.message);
            })
        }
    }

    return (
        <div>
            <div className="container-sm">
                <h1>Consumer Registration Form</h1>
            </div>
            <div className="image-1">
                <img src="./images/registration.jpg" />
            </div>
            <div className="containersm">
                <div className="row g-2 needs-validation">
                    <div className="col-md-4">
                        <label htmlFor="validationCustom01" className="form-label row g-0">First Name*</label>
                        <input type="text" className="form-control" id="validationCustom01" required onChange={(event) => setFirstName(event.target.value)} placeholder={"First Name"} pattern="^[a-zA-Z]{3,18}$" title="Please enter your correct name" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom02" className="form-label row g-0">Last Name</label>
                        <input type="text" className="form-control" id="validationCustom02" onChange={(event) => setLastName(event.target.value)} placeholder={"Last Name"} pattern="^[a-zA-Z]{0,18}$" title="Please enter your correct last name" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom02" className="form-label row g-0">Username*</label>
                        <input type="text" className="form-control" id="validationCustom02" required onChange={(event) => setUsername(event.target.value)} placeholder={"Username"} pattern="^[a-zA-Z0-9_-]{5,15}$" title="Please enter correct username" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom02" className="form-label row g-0">Email*</label>
                        <input type="email" className="form-control" id="validationCustom02" required onChange={(event) => setEmailId(event.target.value)} placeholder={"Email Address"} pattern="^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$" title="Please enter your valid email address" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom02" className="form-label row g-0">Password*</label>
                        <input type="password" className="form-control" id="validationCustom02" required onChange={(event) => setPassword(event.target.value)} placeholder={"Password"} title="password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom04" className="form-label row g-0">Gender*</label>
                        <select className="form-select" id="validationCustom04" required onChange={(event) => setGender(event.target.value)}>
                            <option disabled selected>Choose Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                        <div className="invalid-feedback">
                            Please select your Gender.
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom02" className="form-label row g-0">Phone Number*</label>
                        <input type="number" className="form-control" id="validationCustom02" required min={0} maxLength={10} onChange={(event) => setPhoneNumber(event.target.value)} placeholder={"Phone Number"} pattern="^[6789][0-9]{9}$" title="Please enter correct phone number" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom02" className="form-label row g-0">Street</label>
                        <input type="text" className="form-control" id="validationCustom02" onChange={(event) => setStreet(event.target.value)} placeholder={"Street"} maxLength={40} title="Street cannot be more than 40 characters" />
                        <div className="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom03" className="form-label row g-0">Town</label>
                        <input type="text" className="form-control" id="validationCustom03" onChange={(event) => setTown(event.target.value)} placeholder={"Town"} pattern="^[a-zA-Z]{0,20}$" title="Please enter correct town name" />
                        <div className="invalid-feedback">
                            Please provide a valid town.
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom03" className="form-label row g-0">District*</label>
                        <input type="text" className="form-control" id="validationCustom03" required onChange={(event) => setDistrict(event.target.value)} placeholder={"District"} pattern="^[a-zA-Z]{3,20}$" title="Please enter correct district name" />
                        <div className="invalid-feedback">
                            Please provide a valid disctrict.
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom05" className="form-label row g-0">Pincode*</label>
                        <input type="number" min={1} className="form-control" id="validationCustom05" required onChange={(event) => setPincode(event.target.value)} placeholder={"Pincode"} pattern="^[1-9]{1}[0-9]{2}[0-9]{3}$" title="Please enter correct 6 digits pincode number" />
                        <div className="invalid-feedback">
                            Please provide a valid pincode.
                        </div>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="validationCustom04" className="form-label row g-0">State*</label>
                        <select className="form-select" id="validationCustom04" required onChange={(event) => setState(event.target.value)}>
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
                        <div className="invalid-feedback">
                            Please select a valid state.
                        </div>
                    </div>
                    <div className="col-md-12">
                        <label htmlFor="validationCustom04" className="form-label row g-0">Country*</label>
                        <select className="form-select" id="validationCustom04" required disabled onChange={(event) => setCountry(event.target.value)}>
                            <option defaultValue="India">India</option>
                        </select>
                        <div className="invalid-feedback">
                            Please select your country.
                        </div>
                    </div>
                    <div className="col-12" style={{ marginTop: 20 }}>
                        <button className="btn btn-outline-primary" onClick={Register}>Register</button>
                    </div>
                </div>
            </div >
        </div >
    )
}


export default ConsumerRegistration;