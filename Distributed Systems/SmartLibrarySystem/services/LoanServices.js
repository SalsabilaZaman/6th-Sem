const LoanRepository = require('../repositories/LoanRepository');
const UserRepository = require('../repositories/UserRepository');
const BookRepository = require('../repositories/BookRepository');
const Sequelize = require('sequelize');
const Loan = require('../models/Loan');


class LoanService {
  async issueBook(userId, bookId, dueDate) {
    const user = await UserRepository.getUserById(userId);
    if (!user) throw new Error('User not found');

    const book = await BookRepository.getBookById(bookId);
    if (!book) throw new Error('Book not found');

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
    const now = new Date();
    const overdueLoans = await Loan.findAll({
      where: {
        status: 'ACTIVE',
        due_date: { [Sequelize.Op.lt]: now }
      },
      include: [
        {
          model: require('../models/Book'),
          as: 'book',
          attributes: ['id', 'title', 'author']
        }
      ]
    });

    return overdueLoans.map(loan => ({
      id: loan.id,
      book: loan.book,
      issue_date: loan.issue_date,
      due_date: loan.due_date,
      return_date: loan.return_date,
      status: loan.status
    }));
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