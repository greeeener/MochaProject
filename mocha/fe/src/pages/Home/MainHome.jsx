// src/pages/Home/MainHome.jsx
import { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import styles from './MainHome.module.css';

function MainHome() {
    const [showLogo, setShowLogo] = useState(true);
    const [searchQuery, setSearchQuery] = useState('');
    const location = useLocation();
    const navigate = useNavigate();

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
        if (searchQuery.trim()) {
            navigate(`/search?q=${encodeURIComponent(searchQuery.trim())}`);
        }
    };

    return (
        <div className={`${styles['home']} ${hasHeader ? styles['with-header'] : ''}`}>
            {/* 메인 컨텐츠 */}
            <main className={styles['content']}>
                <div className={styles['animation-container']}>
                    <div
                        className={`${styles['logo-container']} ${showLogo ? styles['logo-container-show'] : styles['logo-container-hide']}`}>
                        <h1 className={styles['header-logo-text']}>MOCA</h1>
                        {/*
                        <img
                            src="" //TODO: 로고 이미지 추가
                            alt="MOCA 로고"
                            className={styles['logo-image']}
                        />
                        */}
                    </div>
                    <div className={`${styles['text-container']} ${!showLogo ? styles['text-container-show'] : styles['text-container-hide']}`}>
                        <p className={styles['search-text']}>내가 찾는 이 작품,</p>
                        <p className={styles['search-text']}>어디서 볼 수 있을까?</p>
                    </div>
                </div>

                <form className={styles['search-form']} onSubmit={handleSearch}>
                    <div className={styles['search-container']}>
                        <input
                            type="text"
                            className={styles['search-input']}
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            placeholder=""
                        />
                        <button type="submit" className={styles['search-button']}>
                            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                 strokeWidth="2">
                                <circle cx="11" cy="11" r="8"/>
                                <path d="M21 21l-4.35-4.35"/>
                            </svg>
                        </button>
                    </div>
                </form>
            </main>

            <footer className={styles['footer']}>
                <p className={styles['disclaimer']}>
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
