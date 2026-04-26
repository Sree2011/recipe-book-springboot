// pages/GetAllRecipesPage.jsx
import React, { useState } from 'react';
import { Button, Card, CardContent, Typography, List, ListItem } from '@mui/material';
import { getAllRecipes } from '../api/recipeService';

function GetAllRecipesPage() {
    const [recipes, setRecipes] = useState([]);

    const handleFetchAll = async () => {
        try {
            const response = await getAllRecipes();
            setRecipes(response.data);
        } catch (err) {
            console.error("Error fetching all recipes:", err);
        }
    };

    return (
        <Card sx={{ mt: 2 }}>
            <CardContent>
                <Typography variant="h5">All Recipes</Typography>
                <Button variant="contained" onClick={handleFetchAll}>Get All Recipes</Button>

                {recipes.length > 0 && (
                    <List>
                        {recipes.map((r) => (
                            <ListItem key={r.id}>
                                <div className="recipe-card">
                                    <strong>Recipe ID - {r.id}</strong>
                                    <br/>
                                    <strong>{r.name}</strong> — {r.servings} servings
                                    <br/>
                                    <strong>Ingredients</strong>
                                    <ul>
                                        {r.ingredients && r.ingredients.map((ing) => (
                                            <li key={ing.id}>
                                                {ing.masterIngredient.name} — {ing.quantity} {ing.unit}
                                            </li>
                                        ))}
                                    </ul>
                                    <br/>
                                    <strong>Instructions</strong>
                                    <br />
                                    <em>{r.instructions}</em>
                                </div>
                            </ListItem>

                        ))}
                    </List>
                )}
            </CardContent>
        </Card>
    );
}

export default GetAllRecipesPage;
