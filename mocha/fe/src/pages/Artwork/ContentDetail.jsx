// src/pages/Artwork/ContentDetail.jsx
import { useState, useEffect } from 'react';
import './ContentDetail.css';

const ContentDetail = () => {
    const [novelData, setNovelData] = useState(null);
    const [isBookmarked, setIsBookmarked] = useState(false);
    const [showDetails, setShowDetails] = useState(false);

    useEffect(() => {
        // TODO: API 호출로 소설 데이터 가져오기
        // const fetchNovelData = async () => {
        //   try {
        //     const response = await fetch('/api/novel/detail');
        //     const data = await response.json();
        //     setNovelData(data);
        //   } catch (error) {
        //     console.error('소설 데이터 로딩 실패:', error);
        //   }
        // };
        // fetchNovelData();

        // 임시 데이터
        setNovelData({
            title: "괴담에 떨어져도 출근을 하는구나",
            author: "백덕수",
            genre: "현대 판타지",
            coverImage: "https://page1.genspark.site/v1/base64_upload/1449ae6e2db19d4f6ed0dd5d3aadd93d",
            platforms: [
                {
                    name: "카카오페이지",
                    logo: "https://page1.genspark.site/v1/base64_upload/413adbce4fca493c1fdf90492a6e31a5",
                    episodes: 77,
                    freeEpisodes: 5,
                    waitFreeEpisodes: 10,
                    totalPrice: 7700,
                    currency: "원",
                    rentInfo: "대여 구매 불가"
                },
                {
                    name: "리디북스",
                    logo: "https://page1.genspark.site/v1/base64_upload/23ced6d0bacd57f909b8cfba41d8d7bc",
                    episodes: 22,
                    freeEpisodes: 3,
                    totalPrice: 5700,
                    currency: "원",
                    rentInfo: "대여 구매 불가",
                    subscription: {
                        price: 13200,
                        currency: "원"
                    }
                }
            ]
        });
    }, []);

    const handleBookmark = () => {
        setIsBookmarked(!isBookmarked);
        // TODO: 북마크 상태 API 호출
        // updateBookmark(!isBookmarked);
    };

    const toggleDetails = () => {
        setShowDetails(!showDetails);
    };

    if (!novelData) {
        return <div className="loading">로딩 중...</div>;
    }

    return (
        <div className="novel-viewer">
            {/* 블러 배경 */}
            <div
                className="blur-background"
                style={{
                    backgroundImage: `url(${novelData.coverImage})`
                }}
            />

            {/* 커버 이미지 */}
            <div className="cover-container">
                <img
                    src={novelData.coverImage}
                    alt={novelData.title}
                    className="cover-image"
                />
                <button className="heart-button" onClick={handleBookmark}>
                    <span className="heart-icon">
                        {isBookmarked ? '❤️' : '🤍'}
                    </span>
                </button>
            </div>

            {/* 하얀 배경 콘텐츠 */}
            <div className="white-content">
                {/* 제목 및 작가 정보 */}
                <div className="novel-info">
                    <h1 className="novel-title">{novelData.title}</h1>
                    <p className="novel-author">
                        <span className="author-name">{novelData.author} · {novelData.genre}</span>
                    </p>
                    <button className="detail-toggle" onClick={toggleDetails}>
                        <span className={`toggle-icon ${showDetails ? 'expanded' : ''}`}>
                            ˅
                        </span>
                    </button>
                </div>

                {/* 상세 정보 (접힌 상태) */}
                {showDetails && (
                    <div className="novel-details">
                        <p className="genre">장르: {novelData.genre}</p>
                        {/* TODO: 추가 상세 정보 표시 */}
                    </div>
                )}

                {/* 플랫폼 정보 */}
                <div className="platforms">
                    {novelData.platforms.map((platform, index) => (
                        <div key={index} className="platform-card">
                            <div className="platform-content">
                                <div className="platform-logo">
                                    <img
                                        src={platform.logo}
                                        alt={platform.name}
                                        className="platform-logo"
                                    />
                                </div>
                                <div className="platform-info">
                                    <div className="platform-header">
                                        <span className="platform-name">{platform.name}</span>
                                        <button className="platform-arrow">›</button>
                                    </div>
                                    <p className="episode-info">
                                        연재중 · 총 {platform.episodes}화 · 무료 {platform.freeEpisodes}화
                                        {platform.waitFreeEpisodes && ` · 무료대기 ${platform.waitFreeEpisodes}화`}
                                    </p>
                                    <p className="rent-info">{platform.rentInfo}</p>
                                    <p className="pricing">
                                        소장 전체 {platform.totalPrice.toLocaleString()}{platform.currency}
                                        {platform.subscription && (
                                            <span className="subscription">
                                                ({platform.subscription.price.toLocaleString()}{platform.subscription.currency})
                                            </span>
                                        )}
                                    </p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>

                {/* 주의사항 */}
                <div className="notice">
                    <span className="notice-icon">
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none"
                                                 stroke="currentColor" strokeWidth="2">
                            <circle cx="12" cy="12" r="10"/>
                            <line x1="12" y1="18" x2="12" y2="11.5"/>
                            <circle cx="12" cy="8" r="0.2" fill="currentColor"/>
                        </svg>
                    </span>
                    <p className="notice-text">
                        기재된 가격은 할인과 프로모션을 제외한 기본 가격 기준으로,<br/>
                        실결제액과 차이가 있을 수 있습니다.
                    </p>
                </div>
            </div>
        </div>
    );
};

export default ContentDetail;