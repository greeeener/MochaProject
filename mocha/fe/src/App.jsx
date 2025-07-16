// src/App.jsx
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Header from './components/Header/Header';
import MainHome from './pages/Home/MainHome';
import ContentDetail from './pages/Artwork/ContentDetail';
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
                    <Route path="/content/:id" element={<ContentDetail />} />
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
