// src/pages/Home/Login.jsx
import { useState} from 'react';
import { useNavigate} from 'react-router-dom';
import styles from './Login.module.css';
import { SAFE_REDIRECT_PATHS } from '../../constants/paths';

function Login() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        id:'',
        password:''
    });

    //
    const handleInputChange = (e) => {
        const {name, value} = e.target; //구조 분해 할당
        setFormData(prev => ({ //state 업데이트
            ...prev, //기존 데이터 유지
            [name]: value //해당 필드만 업데이트
        }));
    };

    //로그인하기
    const handleLogin = (e) => {
        e.preventDefault();

        // 로그인 처리
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('loginTime', Date.now().toString());

        const redirectPath = sessionStorage.getItem('redirectAfterLogin');
        sessionStorage.removeItem('redirectAfterLogin');

        // 안전한 경로만 허용
        const safePath = (redirectPath && SAFE_REDIRECT_PATHS.includes(redirectPath))
            ? redirectPath
            : '/';

        navigate(safePath);
    };

    //회원가입으로 이동
    const handleSignup = () => {

    };

    //아이디 찾기로 이동
    const handleFindId = () => {

    };

    //비밀번호 찾기로 이동
    const handleFindPassword = () => {

    };

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                <h1 className={styles.logo}>MOCA</h1>

                <form onSubmit={handleLogin} className={styles['form-container']}>
                    <input
                        type="text"
                        name="id"
                        placeholder="Id"
                        value={formData.id}
                        onChange={handleInputChange}
                        className={styles['input-field']}
                        required
                    />

                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={formData.password}
                        onChange={handleInputChange}
                        className={styles['input-field']}
                        required
                    />

                    <button type="submit" className={styles['login-button']}>
                        로그인
                    </button>
                </form>
                <button
                    type="button"
                    onClick={handleSignup}
                    className={styles['signup-button']}
                >
                    회원가입
                </button>

                <div className={styles['link-container']}>
                    <button
                        type="button"
                        onClick={handleFindId}
                        className={styles['link-button']}
                    >
                        아이디 찾기
                    </button>
                    <span className={styles.separator}>|</span>
                    <button
                        type="button"
                        onClick={handleFindPassword}
                        className={styles['link-button']}
                    >
                        비밀번호 찾기
                    </button>
                </div>
            </div>
        </div>
    );
}
export default Login;