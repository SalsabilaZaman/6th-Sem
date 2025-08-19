const express = require('express');
const router = express.Router();
const userController = require('../UserController');

// Register a new user
router.post('/', userController.registerUser);

// Get user by ID
router.get('/:id', userController.getUserProfile);

// Update user profile
router.put('/:id', userController.updateUserProfile);

module.exports = router;
