// src/pages/Artwork/ContentDetail.jsx
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import styles from './ContentDetail.module.css';

const ContentDetail = () => {
    const { id } = useParams();
    const [novelData, setNovelData] = useState(null);
    const [isBookmarked, setIsBookmarked] = useState(false);
    const [showDetails, setShowDetails] = useState(false);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [otherWorks, setOtherWorks] = useState([]);

    const fetchOtherWorks = async(authorName) => {
        try {
            const requestBody = {
                searchKeyword: authorName,
                publisher: null,
                genreList: [],
                keywordList: []
            };

            const response = await fetch(`/mc/creation/getCreationList?page=0&size=8`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            });

            if (response.ok) {
                const data = await response.json();
                if (data.creationResponsesList) {
                    const filteredWorks = data.creationResponsesList
                        .filter(work => work.id !== parseInt(id))
                        .slice(0, 4);
                    setOtherWorks(filteredWorks);
                }
            }
        } catch (error) {
            console.error('작가의 다른 작품 로딩 실패:', error);
        }
    };

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
                    coverImage: "/StrangeStory.jfif", //TODO: 작품 썸네일 이미지 추가
                    title: data.title,
                    author: Array.isArray(data.creatorList) ?
                        data.creatorList.map(creator => creator.creator_name).join(', ') :
                        '작가 미상',
                    genre: Array.isArray(data.genreList) ?
                        data.genreList.map(genre => genre.genre_name).join(', ') :
                        '기타',
                    keywords: Array.isArray(data.genreList) && data.genreList.length > 0 ?
                        data.genreList.flatMap(genre =>
                            Array.isArray(genre.keywords) ? genre.keywords : []
                        ) : [],
                    // ✅ 작가 정보 저장 (다른 작품 검색용)
                    authorList: Array.isArray(data.creatorList) ? data.creatorList : [],
                    status: data.is_end ? '완결' : '연재중',
                    description: data.description,
                    //gidamu: data.gidamu>0 ? `${data.gidamu}시간` : "기다무 없음",
                    publisher: data.publisher,
                    age_limit: data.age_limit,
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
                            coinCost: `${data.buy_cost * platform.coin_cost}`,
                            buyInfo: data.buy_cost > 0 ? `소장 전체 ${(data.buy_cost * Math.max(0, data.episodes - data.free_episodes)).toLocaleString()}원` : "소장 구매 불가"
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

    useEffect(() => {
        if (novelData && novelData.authorList.length > 0) {
            const firstAuthor = novelData.authorList[0].creator_name;
            fetchOtherWorks(firstAuthor);
        }
    }, [novelData, id]);

    //북마크 처리
    const handleBookmark = async () => {
        try {
            // TODO: 북마크 API 호출
            setIsBookmarked(!isBookmarked);
        } catch (error) {
            console.error('북마크 처리 실패:', error);
        }
    };

    //상세 정보 더보기 토글
    const toggleDetails = () => {
        setShowDetails(!showDetails);
    };

    //플랫폼 로고 가져오기
    const getPlatformLogo = (platformName) => {
        const logoMap = {
            //TODO: 플랫폼 로고 추가
        };
        return logoMap[platformName] || "/Kakaopage.png";
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
                        <span className={styles['toggle-text']}>
                            {showDetails ? '접기' : '더보기'}
                        </span>
                    </button>
                </div>

                {/* 상세 정보 (토글) */}
                {showDetails && (
                    <div className={styles['novel-details']}>
                        <h3 className={styles['platform-section-title']}>작품 소개</h3>
                        {novelData.description && (
                            <div className={styles['description']}>
                                {novelData.description
                                    .replace(/\\n/g, '<br>')  // \\n을 <br>로 교체
                                    .split('<br>')
                                    .map((line, index, array) => (
                                        <React.Fragment key={index}>
                                            {line}
                                            {index < array.length - 1 && <br/>}
                                        </React.Fragment>
                                    ))
                                }
                            </div>
                        )}

                        <h3 className={styles['platform-section-title']}>키워드</h3>
                        <div className={styles['keywords-container']}>
                            {novelData.keywords.length > 0 ? (
                                novelData.keywords.map((keyword, index) => (
                                    <span key={index} className={styles['keyword-tag']}>
                                        #{keyword.keyword_name}
                                    </span>
                                ))
                            ) : (
                                <p className={styles['no-keyword']}>키워드가 없습니다.</p>
                            )}
                        </div>

                        <h3 className={styles['platform-section-title']}>작가의 다른 작품</h3>
                        <div className={styles['other-works-container']}>
                            {otherWorks.length > 0 ? (
                                otherWorks.map((work, index) => (
                                    <div
                                        key={work.id || index}
                                        className={styles['other-work-item']}
                                        onClick={() => window.location.href = `/content/${work.id}`}
                                    >
                                        <img
                                            src={work.coverImage || "/StrangeStory.jfif"}
                                            alt={work.title}
                                            className={styles['other-work-image']}
                                        />
                                        <p className={styles['other-work-title']}>{work.title}</p>
                                    </div>
                                ))
                            ) : (
                                <p className={styles['no-data']}>다른 작품이 없습니다.</p>
                            )}
                        </div>

                        <h3 className={styles['platform-section-title']}>작품 정보</h3>
                        <p className={styles['description']}>출판사: {novelData.publisher}</p>
                        <p className={styles['description']}>연령제한: {novelData.age_limit}세 이상</p>

                    </div>
                )}


                {/* 플랫폼 정보 */}
                <h3 className={styles['platform-section-title']}>이용 가능한 플랫폼</h3>

                {/* 플랫폼 정보 있을 때 */}
                {novelData.platforms.length > 0 && (
                    <div className={styles['platforms']}>
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
                                            소장 전체 {(
                                            (platform.coinCost || 0) * Math.max(0, (platform.episodes || 0) - (platform.freeEpisodes || 0))).toLocaleString()}원
                                            {platform.coinCost > 0 && (
                                                <span className={styles['coin-cost']}>
                                                    (1화 {platform.coinCost}원)
                                                </span>
                                            )}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                )}

                {/* 플랫폼이 없을 때 */}
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
