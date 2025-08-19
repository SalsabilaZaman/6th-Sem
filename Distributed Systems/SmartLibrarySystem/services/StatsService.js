const BookRepository = require('../repositories/BookRepository');
const UserRepository = require('../repositories/UserRepository');
const LoanRepository = require('../repositories/LoanRepository');

class StatsService {
  async getPopularBooks() {
    const loans = await LoanRepository.findAll();
    const books = await BookRepository.findAll();

    const bookBorrowCounts = books.map(book => {
      const borrowCount = loans.filter(loan => loan.book_id === book.id).length;
      return {
        book_id: book.id,
        title: book.title,
        author: book.author,
        borrow_count: borrowCount
      };
    }).sort((a, b) => b.borrow_count - a.borrow_count);

    return bookBorrowCounts.slice(0, 10);
  }

  async getActiveUsers() {
    const loans = await LoanRepository.findAll();
    const users = await UserRepository.findAll();

    const userActivity = users.map(user => {
      const userLoans = loans.filter(loan => loan.user_id === user.id);
      const booksBorrowed = userLoans.length;
      const currentBorrows = userLoans.filter(loan => loan.status === 'ACTIVE').length;

      return {
        user_id: user.id,
        name: user.name,
        books_borrowed: booksBorrowed,
        current_borrows: currentBorrows
      };
    }).sort((a, b) => b.books_borrowed - a.books_borrowed);

    return userActivity.slice(0, 10);
  }

  async getOverviewStats() {
    const totalBooks = await BookRepository.count();
    const totalUsers = await UserRepository.count();
    const booksBorrowed = await LoanRepository.count({ where: { status: 'ACTIVE' } });
    const overdueLoans = await LoanRepository.count({ where: { status: 'ACTIVE', due_date: { [Op.lt]: new Date() } } });
    const loansToday = await LoanRepository.count({ where: { issue_date: { [Op.gte]: new Date().setHours(0, 0, 0, 0) } } });
    const returnsToday = await LoanRepository.count({ where: { return_date: { [Op.gte]: new Date().setHours(0, 0, 0, 0) } } });
    const booksAvailable = totalBooks - booksBorrowed;

    return {
      total_books: totalBooks,
      total_users: totalUsers,
      books_available: booksAvailable,
      books_borrowed: booksBorrowed,
      overdue_loans: overdueLoans,
      loans_today: loansToday,
      returns_today: returnsToday
    };
  }
}

module.exports = new StatsService();
