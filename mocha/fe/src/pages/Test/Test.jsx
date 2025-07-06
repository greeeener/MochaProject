import { useState, useEffect } from 'react';
import './Test.css';

const Test = () => {
    const [creator, setCreator] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCreator = async () => {
            try {
                setLoading(true);
                const response = await fetch('http://localhost:8080/mc/creator/1');

                if (!response.ok) {
                    throw new Error('데이터를 불러오는데 실패했습니다.');
                }

                const data = await response.json();
                setCreator(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchCreator();
    }, []);

    if (loading) {
        return (
            <div className="test-container">
                <div className="loading">로딩 중...</div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="test-container">
                <div className="error">오류: {error}</div>
            </div>
        );
    }

    return (
        <div className="test-container">
            <div className="creator-card">
                <h2 className="title">크리에이터 정보</h2>
                <div className="creator-info">
                    <div className="info-item">
                        <span className="label">ID:</span>
                        <span className="value">{creator.creatorId}</span>
                    </div>
                    <div className="info-item">
                        <span className="label">이름:</span>
                        <span className="value">{creator.creatorName}</span>
                    </div>
                </div>
                <div className="api-info">
                    <p className="api-url">API: http://localhost:8080/mc/creator/1</p>
                </div>
            </div>
        </div>
    );
};

export default Test;
