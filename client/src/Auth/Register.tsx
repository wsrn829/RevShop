import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import config from '../config';
import '../CSS/register.css';

const RegisterPage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [type, setType] = useState('BUYER');
    const [businessDetails, setBusinessDetails] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            setError('Passwords do not match');
            return;
        }

        try {
            const requestBody = {
                username,
                email,
                password,
                firstName,
                lastName,
                type,
                businessDetails,
                banned: false,
            };

            const response = await axios.post(`${config.BASE_URL}/api/users`, requestBody, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.status !== 200) {
                throw new Error('Registration failed');
            }

            setSuccess(true);
            setError('');
            window.alert('Registration successful! You will be redirected to the login page.');
            navigate('/');
        } catch (error) {
            setError('Registration failed. Please try again.');
        }
    };

    return (
        <div className="register-container">
            <div className="register-image"></div>
            <div className="register-form">
                <div className="card">
                    <div className="card-body">
                        <h2 className="card-title text-center">Register</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label>Username</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Email</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Confirm Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    value={confirmPassword}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>First Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={firstName}
                                    onChange={(e) => setFirstName(e.target.value)}
                                />
                            </div>
                            <div className="form-group">
                                <label>Last Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={lastName}
                                    onChange={(e) => setLastName(e.target.value)}
                                />
                            </div>
                            <div className="form-group">
                                <label>Type</label>
                                <select
                                    className="form-control"
                                    value={type}
                                    onChange={(e) => setType(e.target.value)}
                                >
                                    <option value="BUYER">Buyer</option>
                                    <option value="SELLER">Seller</option>
                                    <option value="BOTH">Both</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label>Business Details</label>
                                <textarea
                                    className="form-control"
                                    value={businessDetails}
                                    onChange={(e) => setBusinessDetails(e.target.value)}
                                ></textarea>
                            </div>
                            {error && <p className="text-danger">{error}</p>}
                            {success && (
                                <p className="text-success">
                                    Registration successful! Redirecting to login...
                                </p>
                            )}
                            <button type="submit" className="btn btn-primary btn-block">
                                Register
                            </button>
                            <p className="register-link text-center">
                                Already have an account? <a href="/">Log in here</a>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
