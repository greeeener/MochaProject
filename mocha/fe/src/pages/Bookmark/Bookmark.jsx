// src/pages/Bookmark/Bookmark.jsx
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ResultList from '../../components/Result/ResultList';
import styles from './Bookmark.module.css';

const Bookmark = () => {
    const navigate = useNavigate();
    const [bookmarkedItems, setBookmarkedItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // 로그인 상태 확인
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        if (!isLoggedIn) {
            navigate('/login');
            return;
        }

        loadBookmarks();
    }, [navigate]);

    const loadBookmarks = async () => {
        try {
            setLoading(true);
            setError(null);

            // 임시로 localStorage에서 북마크 데이터 가져오기
            // 실제로는 API 호출로 변경 예정
            const savedBookmarks = localStorage.getItem('bookmarks');

            if (savedBookmarks) {
                const bookmarkIds = JSON.parse(savedBookmarks);

                if (bookmarkIds.length > 0) {
                    // 각 북마크 ID로 작품 정보 조회
                    const bookmarkPromises = bookmarkIds.map(id =>
                        fetchCreationById(id)
                    );

                    const bookmarkData = await Promise.all(bookmarkPromises);
                    const validBookmarks = bookmarkData.filter(item => item !== null);

                    setBookmarkedItems(validBookmarks);
                } else {
                    setBookmarkedItems([]);
                }
            } else {
                setBookmarkedItems([]);
            }
        } catch (error) {
            console.error('북마크 로드 오류:', error);
            setError('북마크를 불러오는 중 오류가 발생했습니다.');
        } finally {
            setLoading(false);
        }
    };

    // 개별 작품 정보 조회 함수
    const fetchCreationById = async (creationId) => {
        try {
            const response = await fetch(`/mc/creation/getCreation/${creationId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            // ResultList에서 사용할 형태로 데이터 변환
            return {
                id: data.creationId,
                title: data.title,
                author: Array.isArray(data.CreatorList) && data.CreatorList.length > 0 ?
                    data.CreatorList.map(creator => creator.creator_name).join(', ') :
                    '알 수 없음',
                genre: Array.isArray(data.GenreList) && data.GenreList.length > 0 ?
                    data.GenreList.map(genre => genre.genre_name).join(', ') :
                    '기타',
                status: data.is_end ? '완결' : '연재중',
                coverImage: ""
            };
        } catch (error) {
            console.error(`작품 ${creationId} 조회 오류:`, error);
            return null;
        }
    };

    const handleItemClick = (id) => {
        navigate(`/content/${id}`);
    };

    const handleRetry = () => {
        loadBookmarks();
    };

    const removeBookmark = (creationId) => {
        try {
            const savedBookmarks = localStorage.getItem('bookmarks');
            if (savedBookmarks) {
                const bookmarkIds = JSON.parse(savedBookmarks);
                const updatedBookmarks = bookmarkIds.filter(id => id !== creationId);

                localStorage.setItem('bookmarks', JSON.stringify(updatedBookmarks));

                // 화면에서 제거
                setBookmarkedItems(prev => prev.filter(item => item.id !== creationId));
            }
        } catch (error) {
            console.error('북마크 제거 오류:', error);
        }
    };

    return (
        <div className={styles['page']}>

            {/* 북마크 개수 */}
            {!loading && (
                <div className={styles['count-header']}>
                    <span className={styles['count']}>
                        {bookmarkedItems.length}개의 북마크
                    </span>
                </div>
            )}

            {/* 북마크 목록 - ResultList 컴포넌트 */}
            <ResultList
                results={bookmarkedItems}
                loading={loading}
                error={error}
                onItemClick={handleItemClick}
                onRetry={handleRetry}
                emptyMessage="아직 북마크한 작품이 없습니다."
            />

            {/* 북마크가 있을 때만 관리 버튼들 표시 */}
            {!loading && !error && bookmarkedItems.length > 0 && (
                <div className={styles['actions']}>
                    <button
                        className={styles['clear-all-btn']}
                        onClick={() => {
                            if (window.confirm('모든 북마크를 삭제하시겠습니까?')) {
                                localStorage.removeItem('bookmarks');
                                setBookmarkedItems([]);
                            }
                        }}
                    >
                        전체 삭제
                    </button>
                </div>
            )}
        </div>
    );
};

export default Bookmark;
