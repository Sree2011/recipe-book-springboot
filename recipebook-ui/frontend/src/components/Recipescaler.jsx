import React, { useState } from 'react';
import {
    getRecipeById,
    getAllRecipes,
    getRecipesByIngredient,
    getAllMasterIngredients,   // ✅ add this import
    createRecipe,
    deleteRecipeById
} from '../api/recipeService';

import { getScaledRecipe } from "../api/scalingservice.js";

function RecipeScaler() {
    const [recipeId, setRecipeId] = useState('');
    const [ingredientName, setIngredientName] = useState('');
    const [ingredients, setIngredients] = useState([]);
    const [recipes, setRecipes] = useState([]);
    const [recipe, setRecipe] = useState(null);
    const [targetPortions, setTargetPortions] = useState(''); // ✅ new state

    // Fetch recipe by ID
    const handleFetchById = async () => {
        try {
            const response = await getRecipeById(recipeId);
            setRecipe(response.data);
        } catch (err) {
            console.error("Error fetching recipe:", err);
        }
    };

    // Fetch all master ingredients
    const handleFetchIngredients = async () => {
        try {
            const response = await getAllMasterIngredients();
            setIngredients(response.data);
        } catch (err) {
            console.error("Error fetching master ingredients:", err);
        }
    };

    // Fetch all recipes
    const handleFetchAll = async () => {
        try {
            const response = await getAllRecipes();
            setRecipes(response.data);
        } catch (err) {
            console.error("Error fetching all recipes:", err);
        }
    };

    // Fetch recipes by ingredient
    const handleFetchByIngredient = async () => {
        try {
            const response = await getRecipesByIngredient(ingredientName);
            const recipeIds = response.data;
            const recipePromises = recipeIds.map((id) => getRecipeById(id));
            const recipeResponses = await Promise.all(recipePromises);
            setRecipes(recipeResponses.map((res) => res.data));
        } catch (err) {
            console.error("Error fetching recipes by ingredient:", err);
        }
    };

    // Delete recipe by ID
    const handleDelete = async () => {
        try {
            await deleteRecipeById(recipeId);
            alert(`Recipe ${recipeId} deleted`);
        } catch (err) {
            console.error("Error deleting recipe:", err);
        }
    };

    // Create a new recipe
    const handleCreate = async () => {
        const newRecipe = {
            name: "Test Recipe",
            servings: 2,
            instructions: "Mix ingredients and cook.",
            ingredients: [
                { masterIngredient: { name: "flour" }, quantity: 2, unit: "cups" },
                { masterIngredient: { name: "water" }, quantity: 1, unit: "cup" }
            ]
        };
        try {
            const response = await createRecipe(newRecipe);
            alert(`Created recipe with ID ${response.data.id}`);
        } catch (err) {
            console.error("Error creating recipe:", err);
        }
    };

    // Scale recipe
    const handleScaleRecipe = async () => {
        try {
            const response = await getScaledRecipe(recipeId, targetPortions);
            setRecipe(response.data);
        } catch (err) {
            console.error("Error scaling recipe:", err);
        }
    };

    return (
        <div>
            <h2>Recipe Actions</h2>

            {/* Recipe by ID */}
            <div>
                <input
                    type="text"
                    placeholder="Enter recipe ID"
                    value={recipeId}
                    onChange={(e) => setRecipeId(e.target.value)}
                />
                <button onClick={handleFetchById}>Get Recipe by ID</button>
                <button onClick={handleDelete}>Delete Recipe</button>
            </div>

            {/* Scale Recipe */}
            <div>
                <input
                    type="number"
                    placeholder="Target portions"
                    value={targetPortions}
                    onChange={(e) => setTargetPortions(e.target.value)}
                />
                <button onClick={handleScaleRecipe}>Scale Recipe</button>
            </div>

            {/* Recipes by Ingredient */}
            <div>
                <input
                    type="text"
                    placeholder="Enter ingredient name"
                    value={ingredientName}
                    onChange={(e) => setIngredientName(e.target.value)}
                />
                <button onClick={handleFetchByIngredient}>Get Recipes by Ingredient</button>
            </div>

            {/* All Recipes + Create */}
            <div>
                <button onClick={handleFetchAll}>Get All Recipes</button>
                <button onClick={handleCreate}>Create Test Recipe</button>
                <button onClick={handleFetchIngredients}>Get Master Ingredients</button>
            </div>

            {/* Single Recipe */}
            {recipe && (
                <div>
                    <h3>{recipe.name}</h3>
                    <p><strong>Servings:</strong> {recipe.servings}</p>
                    <p><strong>Instructions:</strong> {recipe.instructions}</p>
                    <ul>
                        {recipe.ingredients.map((ing) => (
                            <li key={ing.id}>
                                {ing.masterIngredient.name} — {ing.quantity} {ing.unit}
                            </li>
                        ))}
                    </ul>
                </div>
            )}

            {/* Recipe List */}
            {recipes.length > 0 && (
                <div>
                    <h3>Recipe List</h3>
                    <ul>
                        {recipes.map((r) => (
                            <li key={r.id}>{r.name} ({r.servings} servings)</li>
                        ))}
                    </ul>
                </div>
            )}

            {/* Master Ingredients */}
            {ingredients.length > 0 && (
                <div>
                    <h3>Master Ingredients</h3>
                    <ul>
                        {ingredients.map((ing, idx) => (
                            <li key={idx}>{ing}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default RecipeScaler;
