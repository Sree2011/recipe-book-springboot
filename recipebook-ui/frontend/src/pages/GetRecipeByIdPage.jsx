import React, { useState } from 'react';
import { TextField, Button, Card, CardContent, Typography } from '@mui/material';
import { getRecipeById } from '../api/recipeService';

function GetRecipeByIdPage() {
    const [recipeId, setRecipeId] = useState('');
    const [recipe, setRecipe] = useState(null);

    const handleFetch = async () => {
        const response = await getRecipeById(recipeId);
        setRecipe(response.data);
    };

    return (
        <Card sx={{ mt: 2 }}>
            <CardContent>
                <Typography variant="h5">Fetch Recipe by ID</Typography>
                <TextField
                    label="Recipe ID"
                    value={recipeId}
                    onChange={(e) => setRecipeId(e.target.value)}
                    sx={{ mr: 2 }}
                />
                <Button variant="contained" onClick={handleFetch}>Get Recipe</Button>

                {recipe && (
                    <div style={{ marginTop: '1rem' }}>
                        <Typography variant="h6">{recipe.name}</Typography>
                        <Typography>Servings: {recipe.servings}</Typography>
                        <Typography>Instructions: {recipe.instructions}</Typography>
                        <ul>
                            {recipe.ingredients.map((ing) => (
                                <li key={ing.id}>
                                    {ing.masterIngredient.name} — {ing.quantity} {ing.unit}
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </CardContent>
        </Card>
    );
}

export default GetRecipeByIdPage;
