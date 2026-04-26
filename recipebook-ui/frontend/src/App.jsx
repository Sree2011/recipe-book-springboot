import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home'
import GetRecipeByIdPage from './pages/GetRecipeByIdPage';
import GetAllRecipesPage from './pages/GetAllRecipesPage';
import CreateRecipePage from './pages/CreateRecipePage';
import DeleteRecipePage from './pages/DeleteRecipePage';
import ByIngredientPage from './pages/ByIngredientPage';

function App() {
    return (
        <Router>
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/get-by-id" element={<GetRecipeByIdPage />} />
                <Route path="/get-all" element={<GetAllRecipesPage />} />
                <Route path="/create" element={<CreateRecipePage />} />
                <Route path="/delete" element={<DeleteRecipePage />} />
                <Route path="/by-ingredient" element={<ByIngredientPage />} />
            </Routes>
        </Router>
    );
}

export default App;
