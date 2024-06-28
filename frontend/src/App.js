import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavHeader from './NavHeader';
import AddBookPage from './AddBookPage';
import MainPage from './MainPage';

function App() {
  return (
    <BrowserRouter>
      <NavHeader />
      <Routes>
        <Route path='new' element={
          <AddBookPage />
        }/>
        <Route path='players' element={
          <MainPage />
        } />
      </Routes>
    </BrowserRouter>
  
  );
}

export default App;
