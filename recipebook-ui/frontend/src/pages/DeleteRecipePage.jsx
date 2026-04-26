// pages/DeleteRecipePage.jsx
import React, { useState } from 'react';
import { TextField, Button, Card, CardContent, Typography } from '@mui/material';
import { deleteRecipeById } from '../api/recipeService';

function DeleteRecipePage() {
    const [recipeId, setRecipeId] = useState('');

    const handleDelete = async () => {
        try {
            await deleteRecipeById(recipeId);
            alert(`Recipe ${recipeId} deleted`);
        } catch (err) {
            console.error("Error deleting recipe:", err);
        }
    };

    return (
        <Card sx={{ mt: 2 }}>
            <CardContent>
                <Typography variant="h5">Delete Recipe</Typography>
                <TextField
                    label="Recipe ID"
                    value={recipeId}
                    onChange={(e) => setRecipeId(e.target.value)}
                    sx={{ mr: 2 }}
                />
                <Button variant="contained" color="error" onClick={handleDelete}>Delete</Button>
            </CardContent>
        </Card>
    );
}

export default DeleteRecipePage;
