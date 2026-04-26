// pages/ByIngredientPage.jsx
import React, { useState, useEffect } from 'react';
import {
    Card, CardContent, Typography, List, ListItem, Button, Grid
} from '@mui/material';
import { getAllMasterIngredients, getRecipesByIngredient, getRecipeById } from '../api/recipeService';


function ByIngredientPage() {
    const [ingredients, setIngredients] = useState([]);
    const [recipes, setRecipes] = useState([]);

    // Load all master ingredients on mount
    useEffect(() => {
        const fetchIngredients = async () => {
            try {
                const response = await getAllMasterIngredients();
                setIngredients(response.data); // ["bread","cheese","milk",...]
            } catch (err) {
                console.error("Error fetching ingredients:", err);
            }
        };
        fetchIngredients();
    }, []);

    // When user clicks an ingredient, fetch recipes
    const handleIngredientClick = async (ingredient) => {
        try {
            const response = await getRecipesByIngredient(ingredient);
            const recipeIds = response.data; // [1,2,3]
            const recipePromises = recipeIds.map((id) => getRecipeById(id));
            const recipeResponses = await Promise.all(recipePromises);
            setRecipes(recipeResponses.map((res) => res.data));
        } catch (err) {
            console.error("Error fetching recipes:", err);
        }
    };

    return (
        <Card sx={{ mt: 2 }}>
            <CardContent>
                <Typography variant="h5">Browse by Ingredient</Typography>

                {/* Ingredient list in 5 columns */}
                <Grid container spacing={2} sx={{ mt: 2 }}>
                    {ingredients.map((ing, index) => (
                        <Grid item xs={2.4} key={index}>
                            <Button
                                fullWidth
                                variant="outlined"
                                onClick={() => handleIngredientClick(ing)}
                            >
                                {ing}
                            </Button>
                        </Grid>
                    ))}
                </Grid>

                {/* Recipes for selected ingredient */}
                {recipes.length > 0 && (
                    <>
                        <Typography variant="h6" sx={{ mt: 3 }}>Recipes</Typography>
                        <List>
                            {recipes.map((r) => (
                                <ListItem key={r.id}>
                                    <div>
                                        <strong>{r.name}</strong> — {r.servings} servings
                                        <br />
                                        <em>{r.instructions}</em>
                                        <ul>
                                            {r.ingredients.map((ing) => (
                                                <li key={ing.id}>
                                                    {ing.masterIngredient.name} — {ing.quantity} {ing.unit}
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                </ListItem>
                            ))}
                        </List>
                    </>
                )}
            </CardContent>
        </Card>


    );
}

export default ByIngredientPage;
