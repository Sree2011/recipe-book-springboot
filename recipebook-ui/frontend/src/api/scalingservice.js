import axios from 'axios';

const recipeApi = axios.create({
    baseURL: import.meta.env.REACT_APP_SCALING_SERVICE,
});


export const getScaledRecipe = (id, factor) =>
    recipeApi.get(`/scale/${id}`, { params: { factor } });
