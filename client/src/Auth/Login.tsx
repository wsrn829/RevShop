import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import config from '../config';
import '../CSS/login.css';
import { useAuth } from '../Context/AuthContext';

const LoginPage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(false);
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const requestBody = {
                username,
                password,
            };

            const response = await axios.post(`${config.BASE_URL}/api/auth`, requestBody, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });


            if (response.status !== 200) {
                throw new Error('Login failed');
            }
            const responseData = response.data;
            login(responseData);
            setSuccess(true);
            setError('');
            navigate('/home');
        } catch (error) {
            setError('Login failed. Please try again.');
        }
    };

    return (
        <div className="login-container">
            <div className="login-form">
                <div className="card">
                    <div className="card-body">
                        <h2 className="card-title text-center">Login</h2>
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
                                <label>Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                            {error && <p className="text-danger">{error}</p>}
                            {success && (
                                <p className="text-success">
                                    Login successful! Redirecting...
                                </p>
                            )}
                            <button type="submit" className="btn btn-primary btn-block">
                                Login
                            </button>
                            <p className="register-link text-center">
                                Don't have an account? <a href="/register">Register here</a>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
            <div className="login-image"></div>
        </div>
    );
};

export default LoginPage;
