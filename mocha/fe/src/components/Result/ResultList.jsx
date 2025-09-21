// src/components/Result/ResultList.jsx
import PropTypes from 'prop-types';
import ResultItem from './ResultItem';
import styles from './ResultList.module.css';

function ResultList({
                        results = [],
                        loading = false,
                        error = null,
                        onItemClick,
                        onRetry,
                        emptyMessage = "검색 결과가 없습니다."
                    }) {
    if (loading) {
        return (
            <div className={styles['loading']}>
                <p>검색 중...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className={styles['error']}>
                <p>{error}</p>
                <button onClick={onRetry}>다시 시도</button>
            </div>
        );
    }

    if (results.length === 0) {
        return (
            <div className={styles['no-results']}>
                <p>{emptyMessage}</p>
            </div>
        );
    }

    return (
        <div className={styles['results']}>
            {results.map((item) => (
                <ResultItem
                    key={item.id}
                    item={item}
                    onClick={onItemClick}
                />
            ))}
        </div>
    );
}

// PropTypes 정의
ResultList.propTypes = {
    results: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
            title: PropTypes.string.isRequired,
            author: PropTypes.string.isRequired,
            genre: PropTypes.string.isRequired,
            status: PropTypes.string.isRequired,
            coverImage: PropTypes.string
        })
    ),
    loading: PropTypes.bool,
    error: PropTypes.string,
    onItemClick: PropTypes.func,
    onRetry: PropTypes.func,
    emptyMessage: PropTypes.string
};

export default ResultList;
