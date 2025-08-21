// src/pages/Search/Search.jsx
import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import ResultList from '../../components/Result/ResultList';
import styles from './Search.module.css';

const Search = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [searchQuery, setSearchQuery] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [activeTab, setActiveTab] = useState('웹소설');
    const [totalCount, setTotalCount] = useState(0);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // URL에서 검색어 가져오기
    useEffect(() => {
        const urlParams = new URLSearchParams(location.search);
        const query = urlParams.get('q') || '';
        setSearchQuery(query);

        if (query) {
            performSearch(query);
        }
    }, [location.search]);

    const performSearch = async (query) => {
        try {
            setLoading(true);
            setError(null);

            const requestBody = {
                searchKeyword: query || '',
                publisher: null,
                genreList: [],
                keywordList: []
            };

            const response = await fetch(`/mc/creation/getCreationList?page=0&size=20`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            });

            if (!response.ok) {
                throw new Error(`서버 오류: ${response.status}`);
            }

            const data = await response.json();

            // 결과 처리
            if (data.creationResponsesList && Array.isArray(data.creationResponsesList)) {
                const formattedResults = data.creationResponsesList.map(item => ({
                    id: item.id || item.creationId,
                    title: item.title || '제목 없음',
                    author: Array.isArray(item.creatorList) && item.creatorList.length > 0 ?
                        item.creatorList.map(creator => creator.creator_name || '알 수 없음').join(', ') :
                        '알 수 없음',
                    genre: Array.isArray(item.genreList) && item.genreList.length > 0 ?
                        item.genreList.map(genre => genre.genre_name || '기타').join(', ') :
                        '기타',
                    status: item.is_end ? '완결' : '연재중',
                    coverImage: item.coverImage || ""
                }));

                setSearchResults(formattedResults);
                setTotalCount(data.totalElements || formattedResults.length);
            } else {
                setSearchResults([]);
                setTotalCount(0);
            }

        } catch (error) {
            console.error('검색 오류:', error);
            setError('검색 중 오류가 발생했습니다. 다시 시도해주세요.');
            setSearchResults([]);
            setTotalCount(0);
        } finally {
            setLoading(false);
        }
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        if (searchQuery.trim()) {
            navigate(`/search?q=${encodeURIComponent(searchQuery.trim())}`);
        }
    };

    const handleItemClick = (id) => {
        navigate(`/content/${id}`);
    };

    const handleRetry = () => {
        if (searchQuery) {
            performSearch(searchQuery);
        }
    };

    return (
        <div className={styles['page']}>
            {/* 검색창 */}
            <div className={styles['header']}>
                <form className={styles['form']} onSubmit={handleSearchSubmit}>
                    <div className={styles['input-container']}>
                        <input
                            type="text"
                            className={styles['input']}
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            placeholder="작품명/작가명으로 검색"
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
            </div>

            {/* 탭 메뉴 */}
            <div className={styles['tabs']}>
                <button
                    className={`${styles['tab']} ${activeTab === '웹툰' ? styles['tab-active'] : ''}`}
                    onClick={() => setActiveTab('웹툰')}
                >
                    웹툰
                </button>
                <button
                    className={`${styles['tab']} ${activeTab === '웹소설' ? styles['tab-active'] : ''}`}
                    onClick={() => setActiveTab('웹소설')}
                >
                    웹소설
                </button>
            </div>

            {/* 결과 헤더 */}
            <div className={styles['results-header']}>
                <span className={styles['count']}>{totalCount}개</span>
            </div>

            {/* 검색 결과 */}
            <ResultList
                results={searchResults}
                loading={loading}
                error={error}
                onItemClick={handleItemClick}
                onRetry={handleRetry}
            />
        </div>
    );
};

export default Search;
