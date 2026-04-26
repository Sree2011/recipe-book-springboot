import axios from 'axios';

const scalingApi = axios.create({
    baseURL: import.meta.env.VITE_SCALING_SERVICE,
});


export const getScaledRecipe = (id, targetPortions) =>
    scalingApi.get(`scale/${id}?targetPortions=${targetPortions}`);
