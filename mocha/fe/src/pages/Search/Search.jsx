// src/pages/Search/Search.jsx
import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
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
    //const [sortBy, setSortBy] = useState('관련도');

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
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            console.log('API 응답:', data);

            if (data.creationResponsesList) {
                const formattedResults = data.creationResponsesList.map(item => ({
                    id: item.id,
                    title: item.title,
                    author: Array.isArray(item.creatorList) && item.creatorList.length > 0 ?
                        item.creatorList.map(creator => creator.creator_name).join(', ') :
                        '알 수 없음',
                    genre: Array.isArray(item.genreList) && item.genreList.length > 0 ?
                        item.genreList.map(genre => genre.genre_name).join(', ') :
                        '기타',
                    status: item.is_end ? '완결' : '연재중',
                    coverImage: ""
                }));

                console.log('변환된 결과:', formattedResults);
                setSearchResults(formattedResults);
                setTotalCount(data.totalElements || 0);
            } else {
                setSearchResults([]);
                setTotalCount(0);
            }

        } catch (error) {
            console.error('검색 오류:', error);
            setError('검색 중 오류가 발생했습니다.');
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

    /*
    const handleSortChange = (sortOption) => {
        setSortBy(sortOption);
    };
    */

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
            {/* 정렬
            <div className={styles['results-header']}>
                <span className={styles['count']}>{totalCount}개</span>
                <div className={styles['sort']}>
                    <button className={styles['sort-button']}>
                        {sortBy}
                        <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                            <path d="M6 9l6 6 6-6"/>
                        </svg>
                    </button>
                    <div className={styles['sort-options']}>
                        <button onClick={() => handleSortChange('관련도')}>관련도</button>
                        <button onClick={() => handleSortChange('최신순')}>최신순</button>
                        <button onClick={() => handleSortChange('인기순')}>인기순</button>
                    </div>
                </div>
            </div>
            */}

            {/* 로딩 상태 */}
            {loading && (
                <div className={styles['loading']}>
                    <p>검색 중...</p>
                </div>
            )}

            {/* 에러 상태 */}
            {error && (
                <div className={styles['error']}>
                    <p>{error}</p>
                    <button onClick={() => performSearch(searchQuery)}>다시 시도</button>
                </div>
            )}

            {/* 검색 결과가 없을 때 */}
            {!loading && !error && searchResults.length === 0 && searchQuery && (
                <div className={styles['no-results']}>
                    <p>검색 결과가 없습니다.</p>
                </div>
            )}

            {/* 검색 결과 */}
            {!loading && !error && searchResults.length > 0 && (
                <div className={styles['results']}>
                    {searchResults.map((item) => (
                        <div
                            key={item.id}
                            className={styles['item']}
                            onClick={() => handleItemClick(item.id)}
                        >
                            <div className={styles['item-image']}>
                                <img src={item.coverImage} alt={item.title}/>
                            </div>
                            <div className={styles['item-info']}>
                                <h3 className={styles['item-title']}>{item.title}</h3>
                                <p className={styles['item-meta']}>
                                    {item.author} | {item.genre} | {item.status}
                                </p>
                            </div>
                        </div>
                    ))}
                </div>
            )}

        </div>
    );
};

export default Search;
