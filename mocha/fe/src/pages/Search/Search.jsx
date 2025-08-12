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

    // src/pages/Search/Search.jsx - performSearch 함수만 수정
    const performSearch = async (query) => {
        try {
            console.log('🔍 검색 시작:', query);
            setLoading(true);
            setError(null);

            const requestBody = {
                searchKeyword: query || '',
                publisher: null,
                genreList: [],
                keywordList: []
            };

            console.log('📤 요청 데이터:', requestBody);
            console.log('📍 요청 URL:', `/mc/creation/getCreationList?page=0&size=20`);

            const response = await fetch(`/mc/creation/getCreationList?page=0&size=20`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            });

            console.log('📥 응답 상태:', response.status, response.statusText);
            console.log('📥 응답 헤더:', response.headers);

            if (!response.ok) {
                const errorText = await response.text();
                console.error('❌ 서버 오류 응답:', errorText);
                throw new Error(`HTTP error! status: ${response.status} - ${errorText}`);
            }

            const data = await response.json();
            console.log('✅ API 원본 응답:', data);

            // 응답 구조 자세히 확인
            if (data.creationResponsesList && data.creationResponsesList.length > 0) {
                console.log('📊 첫 번째 아이템 구조:', data.creationResponsesList[0]);

                // 필드명들 확인
                const firstItem = data.creationResponsesList[0];
                console.log('🔑 사용 가능한 필드들:', Object.keys(firstItem));

                // CreatorList, GenreList 확인
                if (firstItem.CreatorList) {
                    console.log('👤 CreatorList 구조:', firstItem.CreatorList);
                }
                if (firstItem.creatorList) {
                    console.log('👤 creatorList 구조:', firstItem.creatorList);
                }
                if (firstItem.GenreList) {
                    console.log('🎭 GenreList 구조:', firstItem.GenreList);
                }
                if (firstItem.genreList) {
                    console.log('🎭 genreList 구조:', firstItem.genreList);
                }
            }

            if (data.creationResponsesList) {
                const formattedResults = data.creationResponsesList.map((item, index) => {
                    console.log(`🔄 아이템 ${index + 1} 변환 중:`, item);

                    // 안전한 데이터 접근
                    const result = {
                        id: item.creationId || item.id || index, // 여러 가능성 시도
                        title: item.title || '제목 없음',
                        author: '작가 정보 처리 중...',
                        genre: '장르 정보 처리 중...',
                        status: item.is_end ? '완결' : '연재중',
                        coverImage: item.coverImage || ""
                    };

                    // CreatorList 처리 (대문자/소문자 모두 시도)
                    try {
                        const creators = item.CreatorList || item.creatorList || [];
                        if (Array.isArray(creators) && creators.length > 0) {
                            result.author = creators.map(creator =>
                                creator.creator_name || creator.name || '알 수 없음'
                            ).join(', ');
                        } else {
                            result.author = '알 수 없음';
                        }
                    } catch (authorError) {
                        console.error('👤 작가 정보 처리 오류:', authorError);
                        result.author = '작가 정보 오류';
                    }

                    // GenreList 처리
                    try {
                        const genres = item.GenreList || item.genreList || [];
                        if (Array.isArray(genres) && genres.length > 0) {
                            result.genre = genres.map(genre =>
                                genre.genre_name || genre.name || '기타'
                            ).join(', ');
                        } else {
                            result.genre = '기타';
                        }
                    } catch (genreError) {
                        console.error('🎭 장르 정보 처리 오류:', genreError);
                        result.genre = '장르 정보 오류';
                    }

                    console.log(`✅ 변환된 아이템 ${index + 1}:`, result);
                    return result;
                });

                console.log('🎉 최종 변환된 결과:', formattedResults);
                setSearchResults(formattedResults);
                setTotalCount(data.totalElements || data.total || formattedResults.length);
            } else {
                console.log('❌ creationResponsesList가 없습니다');
                setSearchResults([]);
                setTotalCount(0);
            }

        } catch (error) {
            console.error('💥 검색 오류 상세:', error);
            console.error('💥 오류 스택:', error.stack);
            console.error('💥 오류 이름:', error.name);
            console.error('💥 오류 메시지:', error.message);

            setError(`검색 중 오류가 발생했습니다: ${error.message}`);
            setSearchResults([]);
            setTotalCount(0);
        } finally {
            setLoading(false);
            console.log('🏁 검색 완료');
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
        performSearch(searchQuery);
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

            {/* 검색 결과 - ResultList 컴포넌트 사용 */}
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
