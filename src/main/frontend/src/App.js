import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<MainPage />} />
            </Routes>
        </Router>
    );
}

export default App;