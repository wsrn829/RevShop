import React, { useState } from 'react'
import { User } from '../Interface/types';
import config from '../config';
import { useAuth } from '../Context/AuthContext';
import { useNavigate } from 'react-router-dom';

const Home: React.FC = () => {
    const [userId, setUserId] = useState('');
    const [fetchedUser, setFetchedUser] = useState<User | null>(null);
    const [error, setError] = useState('');
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    const handleFetchUser = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await fetch(`${config.BASE_URL}/api/users/${userId}`, {
                credentials: 'include',
            });

            if (!response.ok) {
                throw new Error('User not found');
            }

            const data: User = await response.json();
            setFetchedUser(data);
            setError('');
        } catch (error) {
            setError('Failed to fetch user. Please check the user ID and try again.');
            setFetchedUser(null);
        }
    };
    const handleLogout = () => {
        logout();
        navigate('/');
    };

    return (
        <div>
            <h2>Home Page</h2>
            <button onClick={handleLogout}>Logout</button>
            <form onSubmit={handleFetchUser}>
                <label>
                    Enter User ID:
                    <input
                        type="text"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                        required
                    />
                </label>
                <button type="submit">Fetch Other User</button>
            </form>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {fetchedUser && (
                <div>
                    <h3>Fetched User Details</h3>
                    <p><strong>Username:</strong> {fetchedUser.username}</p>
                    <p><strong>Email:</strong> {fetchedUser.email}</p>
                    <p><strong>Business Details:</strong> {fetchedUser.businessDetails}</p>
                </div>
            )}
            {user && (
                <div>
                    <h3>Current Authenticated User</h3>
                    <p>Username: {user.username}</p>
                    <p>Email: {user.email}</p>
                    <p>Business Details: {user.businessDetails}</p>
                </div>
            )}
        </div>
    );
};

export default Home;
