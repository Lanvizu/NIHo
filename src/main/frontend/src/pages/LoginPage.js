import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import '../styles/LoginPage.css';

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/login', {
                email,
                password
            }, {
                withCredentials: true // 쿠키를 포함하여 요청을 보냄
            });
            console.log('로그인 성공:', response.data);
            // 로그인 성공 후 처리 (예: 메인 페이지로 이동)
            navigate('/main');
        } catch (error) {
            console.error('로그인 실패:', error);
            alert('로그인에 실패했습니다. 이메일과 비밀번호를 확인해주세요.');
        }
    };

    return (
        <div className="login-container">
            <h2>로그인</h2>
            <form onSubmit={handleLogin} className="login-form">
                <div className="form-group">
                    <label htmlFor="email">이메일</label>
                    <input
                        id="email"
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">비밀번호</label>
                    <input
                        id="password"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">로그인</button>
            </form>
            <p className="signup-link">
                계정이 없으신가요? <Link to="/signup">회원가입</Link>
            </p>
        </div>
    );
}

export default LoginPage;