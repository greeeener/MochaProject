// src/components/Result/ResultItem.jsx
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import styles from './ResultItem.module.css';

function ResultItem({ item, onClick }) {
    const navigate = useNavigate();

    const handleClick = () => {
        if (onClick) {
            onClick(item.id);
        } else {
            navigate(`/content/${item.id}`);
        }
    };

    return (
        <div className={styles['item']} onClick={handleClick}>
            <div className={styles['item-image']}>
                <img
                    src={item.coverImage || '/placeholder.jpg'}
                    alt={item.title}
                />
            </div>
            <div className={styles['item-info']}>
                <h3 className={styles['item-title']}>
                    {item.title}
                </h3>
                <p className={styles['item-meta']}>
                    {item.author} | {item.genre} | {item.status}
                </p>
            </div>
        </div>
    );
}

// PropTypes 정의
ResultItem.propTypes = {
    item: PropTypes.shape({
        id: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
        title: PropTypes.string.isRequired,
        author: PropTypes.string.isRequired,
        genre: PropTypes.string.isRequired,
        status: PropTypes.string.isRequired,
        coverImage: PropTypes.string
    }).isRequired,
    onClick: PropTypes.func
};

export default ResultItem;
