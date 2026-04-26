import axios from 'axios';

const recipeApi = axios.create({
    baseURL: import.meta.env.REACT_APP_SCALING_SERVICE,
});


export const getScaledRecipe = (id, targetPortions) =>
    scalingApi.get(`scale/${id}?targetPortions=${targetPortions}`);
