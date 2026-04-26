// pages/Home.jsx
import React from 'react';
import { Card, CardContent, Typography } from '@mui/material';

function Home() {
    return (
        <Card sx={{ mt: 2 }}>
            <CardContent>
                <Typography variant="h4">Welcome to Recipe Book</Typography>
                <Typography variant="body1" sx={{ mt: 2 }}>
                    Use the navbar above to explore recipes: fetch by ID, view all, search by ingredient, create, or delete.
                </Typography>
            </CardContent>
        </Card>
    );
}

export default Home;
