// pages/CreateRecipePage.jsx
import React, { useState } from 'react';
import {
    TextField, Button, Card, CardContent, Typography, IconButton
} from '@mui/material';
import { AddCircle, RemoveCircle } from '@mui/icons-material';
import { createRecipe } from '../api/recipeService';

function CreateRecipePage() {
    const [name, setName] = useState('');
    const [servings, setServings] = useState('');
    const [instructions, setInstructions] = useState(['']);
    const [ingredients, setIngredients] = useState([{ name: '', quantity: '', unit: '' }]);

    // Ingredient handlers
    const handleIngredientChange = (index, field, value) => {
        const updated = [...ingredients];
        updated[index][field] = value;
        setIngredients(updated);
    };
    const addIngredient = () => setIngredients([...ingredients, { name: '', quantity: '', unit: '' }]);
    const removeIngredient = (index) => setIngredients(ingredients.filter((_, i) => i !== index));

    // Instruction handlers
    const handleInstructionChange = (index, value) => {
        const updated = [...instructions];
        updated[index] = value;
        setInstructions(updated);
    };
    const addInstruction = () => setInstructions([...instructions, '']);
    const removeInstruction = (index) => setInstructions(instructions.filter((_, i) => i !== index));

    // Create recipe
    const handleCreate = async () => {
        const newRecipe = {
            name,
            servings: Number(servings),
            instructions: instructions.join('\n'), // backend expects string, join lines
            ingredients: ingredients.map(ing => ({
                masterIngredient: { name: ing.name },
                quantity: Number(ing.quantity),
                unit: ing.unit
            }))
        };
        try {
            const response = await createRecipe(newRecipe);
            alert(`Created recipe with ID ${response.data.id}`);
        } catch (err) {
            console.error("Error creating recipe:", err);
        }
    };

    return (
        <Card sx={{ mt: 2 }}>
            <CardContent>
                <Typography variant="h5">Create Recipe</Typography>
                <TextField label="Name" value={name} onChange={(e) => setName(e.target.value)} sx={{ mr: 2, mt: 2 }} />
                <TextField label="Servings" value={servings} onChange={(e) => setServings(e.target.value)} sx={{ mr: 2, mt: 2 }} />

                {/* Instructions */}
                <Typography variant="h6" sx={{ mt: 3 }}>Instructions</Typography>
                {instructions.map((step, index) => (
                    <div key={index} style={{ display: 'flex', alignItems: 'center', marginTop: '8px' }}>
                        <TextField
                            label={`Step ${index + 1}`}
                            value={step}
                            onChange={(e) => handleInstructionChange(index, e.target.value)}
                            fullWidth
                            sx={{ mr: 2 }}
                        />
                        <IconButton color="error" onClick={() => removeInstruction(index)}>
                            <RemoveCircle />
                        </IconButton>
                    </div>
                ))}
                <Button startIcon={<AddCircle />} onClick={addInstruction} sx={{ mt: 2 }}>
                    Add Step
                </Button>

                {/* Ingredients */}
                <Typography variant="h6" sx={{ mt: 3 }}>Ingredients</Typography>
                {ingredients.map((ing, index) => (
                    <div key={index} style={{ display: 'flex', alignItems: 'center', marginTop: '8px' }}>
                        <TextField label="Name" value={ing.name} onChange={(e) => handleIngredientChange(index, 'name', e.target.value)} sx={{ mr: 2 }} />
                        <TextField label="Quantity" value={ing.quantity} onChange={(e) => handleIngredientChange(index, 'quantity', e.target.value)} sx={{ mr: 2 }} />
                        <TextField label="Unit" value={ing.unit} onChange={(e) => handleIngredientChange(index, 'unit', e.target.value)} sx={{ mr: 2 }} />
                        <IconButton color="error" onClick={() => removeIngredient(index)}>
                            <RemoveCircle />
                        </IconButton>
                    </div>
                ))}
                <Button startIcon={<AddCircle />} onClick={addIngredient} sx={{ mt: 2 }}>
                    Add Ingredient
                </Button>

                <Button variant="contained" onClick={handleCreate} sx={{ mt: 3 }}>
                    Create Recipe
                </Button>
            </CardContent>
        </Card>
    );
}

export default CreateRecipePage;
