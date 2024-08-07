import React, { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import {User} from '../Interface/types';

interface AuthContextType {
    user: User | null; 
    login: (data: any) => void;
    logout: () => void;
    token: string | null;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null); 
    const [token, setToken] = useState<string | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        const storedToken = Cookies.get('Authentication');
       
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
        if (storedToken) {
            setToken(storedToken);
            console.log("Retrieved Token from Cookie if storedToken: ", storedToken);
        }
    }, []);

    const login = (data: { user: User, Authentication: string }) => { 
        setUser(data.user);
        setToken(data.Authentication);
        localStorage.setItem('user', JSON.stringify(data.user));
        Cookies.set('Authentication', data.Authentication, { expires: 7, path: '/' }); 
        navigate('/home');
    };

    const logout = () => {
        setUser(null);
        setToken(null);
        localStorage.removeItem('user');
        Cookies.remove('Authentication', { path: '/' });
        navigate('/');
    };

    return (
        <AuthContext.Provider value={{ user, login, logout, token }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};
