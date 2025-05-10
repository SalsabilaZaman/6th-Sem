const express = require('express');
const router = express.Router();
const StatsController = require('../StatsController');

// Get the most borrowed books
router.get('/books/popular', StatsController.getPopularBooks);

// Get the most active users
router.get('/users/active', StatsController.getActiveUsers);

// Get system overview statistics
router.get('/overview', StatsController.getOverviewStats);

module.exports = router;
