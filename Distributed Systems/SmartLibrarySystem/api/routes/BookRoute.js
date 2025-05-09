const express = require('express');
const router = express.Router();
const bookController = require('../BookController');

// Register a new user
router.post('/', bookController.addBook);

// Get user by ID
router.get('/:id', bookController.getBook);

// Update user profile
router.put('/:id', bookController.updateBook);

router.delete('/:id', bookController.deleteBook);

module.exports = router;
