// src/pages/MyPage/MyPage.jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Settings } from 'lucide-react';
import styles from './MyPage.module.css';

function MyPage() {
    const navigate = useNavigate();
    const [searchQuery, setSearchQuery] = useState('');

    // 사용자 정보 TODO: API로 변경
    const userName = localStorage.getItem('userId') || '사용자';

    const handleSearch = (e) => {
        e.preventDefault();
        if (searchQuery.trim()) {
            navigate(`/search?q=${encodeURIComponent(searchQuery.trim())}`);
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('userId');
        localStorage.removeItem('loginTime');
        navigate('/');
    };

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                <div className={styles.greeting}>
                    <span className={styles['greeting-text']}>
                        <span className={styles['username']}>{userName}</span>님, 안녕하세요!
                    </span>
                    <Settings className={styles['settings-icon']} />
                </div>

                <form onSubmit={handleSearch} className={styles['search-form']}>
                    <div className={styles['search-container']}>
                        <button
                            type="button"
                            onClick={handleLogout}
                            className={styles['logout-button']}
                        >
                            로그아웃
                        </button>
                    </div>
                </form>

                {/* 추가 마이페이지 콘텐츠는 여기에 */}
            </div>
        </div>
    );
}

export default MyPage;
