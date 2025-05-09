const LoanRepository = require('../repositories/LoanRepository');

class LoanService {
  async issueBook(userId, bookId, dueDate) {
    return await LoanRepository.createLoan({ user_id: userId, book_id: bookId, due_date: dueDate });
  }

  async returnBook(loanId) {
    const loan = await LoanRepository.getLoanById(loanId);
    if (!loan) throw new Error('Loan not found');

    loan.status = 'RETURNED';
    loan.return_date = new Date();
    await loan.save();

    return loan;
  }

  async getUserLoans(userId) {
    return await LoanRepository.getUserLoans(userId);
  }

  async getOverdueLoans() {
    return await LoanRepository.getOverdueLoans();
  }

  async extendLoan(id, extensionDays) {
    const loan = await LoanRepository.getLoanById(id);
    if (!loan) throw new Error('Loan not found');
    if (loan.status !== 'ACTIVE') throw new Error('Only active loans can be extended');

    loan.due_date = new Date(new Date(loan.due_date).setDate(new Date(loan.due_date).getDate() + extensionDays));
    loan.extensions_count += 1;
    await loan.save();

    return loan;
  }
}

module.exports = new LoanService();
