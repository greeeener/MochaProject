// src/utils/bookmarkUtils.js
export const addBookmark = (creationId) => {
    try {
        const existingBookmarks = getBookmarks();
        if (!existingBookmarks.includes(creationId)) {
            const updatedBookmarks = [...existingBookmarks, creationId];
            localStorage.setItem('bookmarks', JSON.stringify(updatedBookmarks));
            return true;
        }
        return false;
    } catch (error) {
        console.error('북마크 추가 오류:', error);
        return false;
    }
};

export const removeBookmark = (creationId) => {
    try {
        const existingBookmarks = getBookmarks();
        const updatedBookmarks = existingBookmarks.filter(id => id !== creationId);
        localStorage.setItem('bookmarks', JSON.stringify(updatedBookmarks));
        return true;
    } catch (error) {
        console.error('북마크 제거 오류:', error);
        return false;
    }
};

export const isBookmarked = (creationId) => {
    try {
        const bookmarks = getBookmarks();
        return bookmarks.includes(creationId);
    } catch (error) {
        console.error('북마크 확인 오류:', error);
        return false;
    }
};

export const getBookmarks = () => {
    try {
        const bookmarks = localStorage.getItem('bookmarks');
        return bookmarks ? JSON.parse(bookmarks) : [];
    } catch (error) {
        console.error('북마크 목록 조회 오류:', error);
        return [];
    }
};
