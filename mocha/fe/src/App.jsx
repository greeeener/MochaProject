// src/App.jsx
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Header from './components/Header/Header';
import MainHome from './pages/Home/MainHome';
import Search from './pages/Search/Search';
import ContentDetail from './pages/Artwork/ContentDetail';
import Login from './pages/Login/Login';
import MyPage from './pages/MyPage/MyPage';
import Bookmark from './pages/Bookmark/Bookmark';

import './App.css';

function AppContent() {
    const location = useLocation();

    const hideHeaderPaths = ['/password-change', '/login'];
    const shouldHideHeader = hideHeaderPaths.includes(location.pathname);

    // 페이지별 클래스 결정
    const getPageContentClass = () => {
        if (shouldHideHeader) return 'page-content--no-header';
        if (location.pathname === '/') return 'page-content--main-home';
        return 'page-content';
    };

    return (
        <div className="App">
            {!shouldHideHeader && <Header />}

            <div className={getPageContentClass()}>
                <Routes>
                    <Route path="/" element={<MainHome />} />
                    <Route path="/search" element={<Search />} />
                    <Route path="/content/:id" element={<ContentDetail />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/mypage" element={<MyPage />} />
                    <Route path="/bookmark" element={<Bookmark/>} />
                </Routes>
            </div>
        </div>
    );
}

function App() {
    return (
        <Router>
            <AppContent />
        </Router>
    );
}

export default App;
