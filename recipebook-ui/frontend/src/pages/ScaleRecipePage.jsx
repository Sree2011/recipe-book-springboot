import React, { useState, useEffect } from 'react';
import { getRecipeById } from '../api/recipeService';
import { getScaledRecipe } from "../api/scalingservice.js";

const RecipeScalerPage = () => {
    // New state for the ID input
    const [recipeIdInput, setRecipeIdInput] = useState("");
    const [recipe, setRecipe] = useState(null);
    const [targetPortions, setTargetPortions] = useState(1);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    // Function to fetch the initial recipe by ID from 8081
    const handleFetchRecipe = async () => {
        if (!recipeIdInput) return;
        setLoading(true);
        setError("");
        try {
            const response = await getRecipeById(recipeIdInput);
            setRecipe(response.data);
            setTargetPortions(response.data.servings);
            setLoading(false);
        } catch (err) {
            console.error("Failed to load recipe", err);
            setError("Recipe not found in database.");
            setRecipe(null);
            setLoading(false);
        }
    };

    // Function to scale the current recipe via 8082
    const handleScaleRecipe = async () => {
        try {
            // We use recipe.id here to ensure we are scaling the loaded recipe
            const response = await getScaledRecipe(recipe.id, targetPortions);
            setRecipe(response.data);
        } catch (err) {
            console.error("Error scaling recipe:", err);
            setError("Error communicating with Scaling Service.");
        }
    };

    return (
        <div style={{ padding: '20px', maxWidth: '600px', margin: 'auto', fontFamily: 'Arial' }}>
            <h2>Recipe Vault Scaler</h2>
            <h3>Get the id using the 'get all' link. if your recipe is not found,
                please create it and get that id. </h3>
            {/* --- STEP 1: LOAD RECIPE --- */}
            <div style={{ marginBottom: '20px', padding: '15px', border: '1px solid #ccc', borderRadius: '8px' }}>
                <label>Enter Recipe ID: </label>
                <input
                    type="number"
                    value={recipeIdInput}
                    onChange={(e) => setRecipeIdInput(e.target.value)}
                    placeholder="e.g. 1"
                    style={{ width: '80px', marginRight: '10px' }}
                />
                <button onClick={handleFetchRecipe} style={{ padding: '5px 15px', cursor: 'pointer' }}>
                    Load Recipe
                </button>
            </div>

            {loading && <p>Searching the vault...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            {/* --- STEP 2: DISPLAY & SCALE --- */}
            {recipe && (
                <div style={{ animation: 'fadeIn 0.5s' }}>
                    <hr />
                    <h1>{recipe.name}</h1>
                    <p><strong>Method:</strong> {recipe.instructions}</p>

                    <div style={{ background: '#eef2f3', padding: '15px', borderRadius: '8px', margin: '20px 0' }}>
                        <h3>Scale Portions</h3>
                        <label>Target Servings: </label>
                        <input
                            type="number"
                            value={targetPortions}
                            onChange={(e) => setTargetPortions(e.target.value)}
                            style={{ width: '60px', marginRight: '10px' }}
                        />
                        <button
                            onClick={handleScaleRecipe}
                            style={{ padding: '5px 15px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
                        >
                            Scale Now
                        </button>
                    </div>

                    <h3>Ingredients for {recipe.servings} servings:</h3>
                    <ul style={{ lineHeight: '1.8' }}>
                        {recipe.ingredients.map((ing) => (
                            <li key={ing.id}>
                                <b>{ing.quantity.toFixed(2)} {ing.unit}</b> — {ing.masterIngredient.name}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default RecipeScalerPage;