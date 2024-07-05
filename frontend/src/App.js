import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavHeader from './NavHeader';
import AddPlayerPage from './AddPlayerPage';
import MainPage from './MainPage';

function App() {
  return (
    <BrowserRouter>
      <NavHeader />
      <Routes>
        <Route path='new' element={
          <AddPlayerPage />
        }/>
        <Route path='/' element={
          <MainPage />
        } />
      </Routes>
    </BrowserRouter>
  
  );
}

export default App;
