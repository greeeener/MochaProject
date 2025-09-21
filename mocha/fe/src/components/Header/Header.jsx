// src/components/Header/Header.jsx
import {useLocation, useNavigate} from 'react-router-dom';
import styles from './Header.module.css';
import { SAFE_REDIRECT_PATHS } from '../../constants/paths';

function Header() {
    const location = useLocation();
    const navigate = useNavigate();
    const isMainPage = location.pathname === '/';

    //로그인 상태 확인
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    //로고 클릭시 메인화면 이동
    const handleLogoClick = () => {
        navigate('/');
    };

    //북마크 이동
    const handleBookmarkClick = () => {
        if(isLoggedIn){
            navigate('/bookmark');
        } else {
            /*
            const currentPath = location.pathname;
            if (SAFE_REDIRECT_PATHS.includes(currentPath) ||
                currentPath.startsWith('/detail/')) {
                sessionStorage.setItem('redirectAfterLogin', currentPath);
            }
             */
            sessionStorage.setItem('redirectAfterLogin', '/bookmark');
            navigate('/login');
        }
    };

    //마이페이지 이동
    const handleUserClick = () => {
        if(isLoggedIn) {
            navigate('/mypage');
        } else {
            /*const currentPath = location.pathname;
            if (SAFE_REDIRECT_PATHS.includes(currentPath) ||
                currentPath.startsWith('/detail/')) {
                sessionStorage.setItem('redirectAfterLogin', currentPath);
            }*/
            sessionStorage.setItem('redirectAfterLogin', '/mypage');
            navigate('/login');
        }
    };

    return (
        <header className={styles['global-header']}>
            <div className={styles['header-content']}>
                {/* 로고 표시 X : 메인 페이지, 정보 수정  */}
                {!isMainPage && (
                    <div className={styles['logo-section']}>
                        <h1 className={styles['header-logo-text']}
                            onClick={handleLogoClick}
                            style={{cursor: 'pointer'}}>MOCA</h1>
                        {/*
                        <img
                            src="" //TODO: 로고 이미지 추가
                            alt="MOCA"
                            className={styles['logo-image']}
                        />
                        */}
                    </div>
                )}

                <div className={styles['header-icons']}>
                    <div className={styles['bookmark-icon']}
                        onClick={handleBookmarkClick}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                            <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2z"/>
                        </svg>
                    </div>
                    <div className={styles['user-icon']}
                        onClick={handleUserClick}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                            <circle cx="12" cy="7" r="4"/>
                        </svg>
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;
