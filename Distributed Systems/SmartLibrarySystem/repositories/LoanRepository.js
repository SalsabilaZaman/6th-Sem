const Loan = require('../models/Loan');

class LoanRepository {
  async createLoan(data) {
    return await Loan.create(data);
  }

  async getLoanById(id) {
    return await Loan.findByPk(id);
  }

  async updateLoan(id, data) {
    const loan = await Loan.findByPk(id);
    if (!loan) throw new Error('Loan not found');
    return await loan.update(data);
  }

  async getUserLoans(userId) {
    return await Loan.findAll({ where: { user_id: userId } });
  }

  async getOverdueLoans() {
    const now = new Date();
    return await Loan.findAll({
      where: {
        status: 'ACTIVE',
        due_date: { [Sequelize.Op.lt]: now }
      }
    });
  }
}

module.exports = new LoanRepository();
