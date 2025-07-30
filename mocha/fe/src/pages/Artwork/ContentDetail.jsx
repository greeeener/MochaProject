// src/pages/Artwork/ContentDetail.jsx
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import styles from './ContentDetail.module.css';

const ContentDetail = () => {
    const { id } = useParams();
    const [novelData, setNovelData] = useState(null);
    const [isBookmarked, setIsBookmarked] = useState(false);
    const [showDetails, setShowDetails] = useState(false);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchNovelData = async () => {
            try {
                setLoading(true);
                setError(null);

                const response = await fetch(`/mc/creation/getCreation/${id}`);

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                console.log('작품 상세 API 응답:', data);

                // API 응답을 화면에 맞는 형태로 변환
                const formattedData = {
                    title: data.title,
                    author: Array.isArray(data.creatorList) ?
                        data.creatorList.map(creator => creator.creator_name).join(', ') :
                        '작가 미상',
                    genre: Array.isArray(data.genreList) ?
                        data.genreList.map(genre => genre.genre_name).join(', ') :
                        '기타',
                    coverImage: "", //TODO: DB에 추가되면 바꾸기
                    description: data.description,
                    episodes: data.episodes,
                    status: data.is_end ? '완결' : '연재중',
                    gidamu: data.gidamu,
                    publisher: data.publisher,
                    age_limit: data.age_limit,
                    free_episodes: data.free_episodes,
                    rent_cost: data.rent_cost,
                    buy_cost: data.buy_cost,
                    start_date: data.start_date,
                    latest_date: data.latest_date,
                    platforms: Array.isArray(data.platformList) ?
                        data.platformList.map((platform) => ({
                            id: platform.platform_id,
                            name: platform.platform_name,
                            logo: getPlatformLogo(platform.platform_name),
                            episodes: data.episodes,
                            freeEpisodes: data.free_episodes,
                            totalPrice: data.buy_cost,
                            rentPrice: data.rent_cost,
                            currency: "원",
                            rentInfo: data.rent_cost > 0 ? `대여 ${data.rent_cost}원` : "대여 구매 불가",
                            coinCost: platform.coin_cost
                        })) : []
                };

                setNovelData(formattedData);
            } catch (error) {
                console.error('작품 데이터 로딩 실패:', error);
                setError('작품 정보를 불러오는 중 오류가 발생했습니다.');
            } finally {
                setLoading(false);
            }
        };

        if (id) {
            fetchNovelData();
        }
    }, [id]);

    const getPlatformLogo = (platformName) => {
        const logoMap = {
            //TODO: 플랫폼 로고 추가
        };
        return logoMap[platformName] || '';
    };

    const handleBookmark = async () => {
        try {
            // TODO: 북마크 API 호출
            setIsBookmarked(!isBookmarked);
        } catch (error) {
            console.error('북마크 처리 실패:', error);
        }
    };

    const toggleDetails = () => {
        setShowDetails(!showDetails);
    };

    //로딩
    if (loading) {
        return (
            <div className={styles['loading']}>
                <p>작품 정보를 불러오는 중...</p>
            </div>
        );
    }

    // 에러
    if (error) {
        return (
            <div className={styles['error']}>
                <p>{error}</p>
                <button onClick={() => window.history.back()}>이전 페이지로</button>
            </div>
        );
    }

    // 데이터가 없는 경우
    if (!novelData) {
        return (
            <div className={styles['error']}>
                <p>작품 정보를 찾을 수 없습니다.</p>
                <button onClick={() => window.history.back()}>이전 페이지로</button>
            </div>
        );
    }

    return (
        <div className={styles['novel-viewer']}>
            {/* 블러 배경 */}
            <div
                className={styles['blur-background']}
                style={{
                    backgroundImage: `url(${novelData.coverImage})`
                }}
            />

            {/* 커버 이미지 */}
            <div className={styles['cover-container']}>
                <img
                    src={novelData.coverImage}
                    alt={novelData.title}
                    className={styles['cover-image']}
                />
                <button className={styles['heart-button']} onClick={handleBookmark}>
                    <span className={styles['heart-icon']}>
                        {isBookmarked ? '❤️' : '🤍'}
                    </span>
                </button>
            </div>

            {/* 하얀 배경 콘텐츠 */}
            <div className={styles['white-content']}>
                {/* 제목 및 작가 정보 */}
                <div className={styles['novel-info']}>
                    <h1 className={styles['novel-title']}>{novelData.title}</h1>
                    <p className={styles['novel-author']}>
                        <span className={styles['author-name']}>
                            {novelData.author} · {novelData.genre} · {novelData.status}
                        </span>
                    </p>
                    <button className={styles['detail-toggle']} onClick={toggleDetails}>
                        <span className={`${styles['toggle-icon']} ${showDetails ? styles['expanded'] : ''}`}>
                            ˅
                        </span>
                    </button>
                </div>

                {/* 상세 정보 (접힌 상태) */}
                {showDetails && (
                    <div className={styles['novel-details']}>
                        <p className={styles['genre']}>장르: {novelData.genre}</p>
                        <p className={styles['genre']}>출판사: {novelData.publisher}</p>
                        <p className={styles['genre']}>총 {novelData.episodes}화 · 무료 {novelData.free_episodes}화</p>
                        <p className={styles['genre']}>연령 제한: {novelData.age_limit}세 이상</p>
                        <p className={styles['genre']}>기다무: {novelData.gidamu?.toLocaleString()}</p>
                        {novelData.start_date && (
                            <p className={styles['genre']}>연재 시작: {new Date(novelData.start_date).toLocaleDateString()}</p>
                        )}
                        {novelData.latest_date && (
                            <p className={styles['genre']}>최근 업데이트: {new Date(novelData.latest_date).toLocaleDateString()}</p>
                        )}
                        {novelData.description && (
                            <p className={styles['description']}>{novelData.description}</p>
                        )}
                    </div>
                )}

                {/* 플랫폼 정보 */}
                {novelData.platforms.length > 0 && (
                    <div className={styles['platforms']}>
                        <h3 className={styles['platform-section-title']}>이용 가능한 플랫폼</h3>
                        {novelData.platforms.map((platform, index) => (
                            <div key={platform.id || index} className={styles['platform-card']}>
                                <div className={styles['platform-content']}>
                                    <div className={styles['platform-logo']}>
                                        <img
                                            src={platform.logo}
                                            alt={platform.name}
                                            className={styles['platform-logo']}
                                        />
                                    </div>
                                    <div className={styles['platform-info']}>
                                        <div className={styles['platform-header']}>
                                            <span className={styles['platform-name']}>{platform.name}</span>
                                            <button className={styles['platform-arrow']}>›</button>
                                        </div>
                                        <p className={styles['episode-info']}>
                                            {novelData.status} · 총 {platform.episodes}화 · 무료 {platform.freeEpisodes}화
                                        </p>
                                        <p className={styles['rent-info']}>{platform.rentInfo}</p>
                                        <p className={styles['pricing']}>
                                            소장 전체 {platform.totalPrice.toLocaleString()}{platform.currency}
                                            {platform.coinCost > 0 && (
                                                <span className={styles['coin-cost']}>
                                                    ({platform.coinCost} 코인)
                                                </span>
                                            )}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                )}

                {/* 플랫폼이 없는 경우 */}
                {novelData.platforms.length === 0 && (
                    <div className={styles['no-platforms']}>
                        <p>현재 이용 가능한 플랫폼이 없습니다.</p>
                    </div>
                )}

                {/* 주의사항 */}
                <div className={styles['notice']}>
                    <span className={styles['notice-icon']}>
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" strokeWidth="2">
                            <circle cx="12" cy="12" r="10"/>
                            <line x1="12" y1="18" x2="12" y2="11.5"/>
                            <circle cx="12" cy="8" r="0.2" fill="currentColor"/>
                        </svg>
                    </span>
                    <p className={styles['notice-text']}>
                        기재된 가격은 할인과 프로모션을 제외한 기본 가격 기준으로,<br/>
                        실결제액과 차이가 있을 수 있습니다.
                    </p>
                </div>
            </div>
        </div>
    );
};

export default ContentDetail;
