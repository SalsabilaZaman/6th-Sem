const express = require('express');
const router = express.Router();
const LoanController = require('../LoanController');

// Issue a book to a user
router.post('/', LoanController.issueBook);

// Return a borrowed book
router.post('/returns', LoanController.returnBook);


// List all overdue loans
router.get('/overdue', LoanController.getOverdueLoans);

// Get loan history for a user

router.get('/:user_id', LoanController.getUserLoans);

// Extend the due date for a loan
router.put('/:id/extend', LoanController.extendLoan);

module.exports = router;
