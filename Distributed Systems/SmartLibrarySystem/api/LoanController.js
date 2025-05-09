const loanService = require('../services/LoanServices');

class LoanController {
  // Issue a book to a user
  async issueBook(req, res) {
    try {
      const { user_id, book_id, due_date } = req.body;
      const loan = await loanService.issueBook(user_id, book_id, due_date);
      res.status(201).json(loan); // Return the newly created loan
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }

  // Return a borrowed book
  async returnBook(req, res) {
    try {
      const { loan_id } = req.body;
      const loan = await loanService.returnBook(loan_id);
      res.status(200).json(loan); // Return the updated loan
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }

  // Get loan history for a user
  async getUserLoans(req, res) {
    try {
      const { user_id } = req.params;
      const loans = await loanService.getUserLoans(user_id);
      res.status(200).json(loans); // Return the user's loan history
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }

  // List all overdue loans
  async getOverdueLoans(req, res) {
    try {
      const overdueLoans = await loanService.getOverdueLoans();
      res.status(200).json(overdueLoans); // Return the overdue loans
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }

  // Extend the due date for a loan
  async extendLoan(req, res) {
    try {
      const { id } = req.params;
      const { extension_days } = req.body;
      const loan = await loanService.extendLoan(id, extension_days);
      res.status(200).json(loan); // Return the updated loan
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  }
}

module.exports = new LoanController();
