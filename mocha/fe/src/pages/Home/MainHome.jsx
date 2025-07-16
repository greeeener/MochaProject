// src/pages/Home/MainHome.jsx (클래스 조건부 적용 예시)
import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import './MainHome.css';

function MainHome() {
    const [showLogo, setShowLogo] = useState(true);
    const [searchQuery, setSearchQuery] = useState('');
    const location = useLocation();

    // 헤더가 숨겨지는 페이지인지 확인
    const hideHeaderPaths = ['/password-change', '/login'];
    const hasHeader = !hideHeaderPaths.includes(location.pathname);

    useEffect(() => {
        const interval = setInterval(() => {
            setShowLogo(prev => !prev);
        }, 4000);

        return () => clearInterval(interval);
    }, []);

    const handleSearch = (e) => {
        e.preventDefault();
        console.log('검색어:', searchQuery);
    };

    return (
        <div className={`main-home ${hasHeader ? 'with-header' : ''}`}>
            {/* 메인 컨텐츠 */}
            <main className="main-content">
                <div className="animation-container">
                    <div className={`logo-container ${showLogo ? 'show' : 'hide'}`}>
                        <img
                            src="https://page1.genspark.site/v1/base64_upload/9fbabba3d7cb13bfb03a937eae104b80"
                            alt="MOCA 로고"
                            className="logo-image"
                        />
                    </div>
                    <div className={`search-text-container ${!showLogo ? 'show' : 'hide'}`}>
                        <p className="search-text">내가 찾는 이 작품,</p>
                        <p className="search-text">어디서 볼 수 있을까?</p>
                    </div>
                </div>

                <form className="search-form" onSubmit={handleSearch}>
                    <div className="search-container">
                        <input
                            type="text"
                            className="search-input"
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            placeholder=""
                        />
                        <button type="submit" className="search-button">
                            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                 strokeWidth="2">
                                <circle cx="11" cy="11" r="8"/>
                                <path d="M21 21l-4.35-4.35"/>
                            </svg>
                        </button>
                    </div>
                </form>
            </main>

            <footer className="main-footer">
                <p className="disclaimer">
                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                        <circle cx="12" cy="12" r="10"/>
                        <line x1="12" y1="18" x2="12" y2="11.5"/>
                        <circle cx="12" cy="8" r="0.2" fill="currentColor"/>
                    </svg>
                    불법 사이트에 대한 정보는 제공해드리지 않습니다
                </p>
            </footer>
        </div>
    );
}

export default MainHome;
