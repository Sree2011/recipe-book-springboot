import axios from 'axios';

const recipeApi = axios.create({
    baseURL: import.meta.env.VITE_RECIPE_SERVICE,
});
export const getAllMasterIngredients = () =>
    recipeApi.get("recipes/masteringredients");
// Create a new recipe
export const createRecipe = (recipe) =>
    recipeApi.post('recipes/create', recipe);

// Get recipe by ID
export const getRecipeById = (id) =>
    recipeApi.get(`recipes/${id}`);

// Delete recipe by ID
export const deleteRecipeById = (id) =>
    recipeApi.delete(`recipes/${id}`);

// Get recipes containing a specific ingredient
export const getRecipesByIngredient = (name) =>
    recipeApi.get(`recipes/ingredient/${name}/recipes`);

// Get all recipes
export const getAllRecipes = () =>
    recipeApi.get('recipes/getall');
