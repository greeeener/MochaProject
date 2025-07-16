// src/components/Header/Header.jsx
import { useLocation } from 'react-router-dom';
import './Header.css';

function Header() {
    const location = useLocation();
    const isMainPage = location.pathname === '/';

    return (
        <header className="global-header">
            <div className="header-content">
                {/* 메인 페이지가 아닐 때만 MOCA 로고 표시 */}
                {!isMainPage && (
                    <div className="logo-section">
                        {/*<img
                            src="https://page1.genspark.site/v1/base64_upload/9fbabba3d7cb13bfb03a937eae104b80"
                            alt="MOCA 로고"
                            className="header-logo"
                        />*/}
                        <h1 className="header-logo-text">MOCA</h1>
                    </div>
                )}

                <div className="header-icons">
                    <div className="bookmark-icon">
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                            <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2z"/>
                        </svg>
                    </div>
                    <div className="user-icon">
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
