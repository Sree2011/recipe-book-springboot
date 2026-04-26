// components/Navbar.jsx
import React from 'react';
import { AppBar, Toolbar, Button } from '@mui/material';
import { Link } from 'react-router-dom';
import {LogoDev} from "@mui/icons-material";

function Navbar() {
    return (
        <AppBar position="static">
            <Toolbar>
                <Button color="inherit" component={Link} to="/">Home</Button>
                <Button color="inherit" component={Link} to="/get-by-id">Get by ID</Button>
                <Button color="inherit" component={Link} to="/get-all">Get All</Button>
                <Button color="inherit" component={Link} to="/by-ingredient">By Ingredient</Button>
                <Button color="inherit" component={Link} to="/create">Create</Button>
                <Button color="inherit" component={Link} to="/delete">Delete</Button>
                <Button color="inherit" component={Link} to="/scale-recipe">Scale recipe</Button>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;
