const { DataTypes } = require('sequelize');
const sequelize = require('../config/db');

const Loan = sequelize.define('Loan', {
  user_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  book_id: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  issue_date: {
    type: DataTypes.DATE,
    defaultValue: DataTypes.NOW,
  },
  due_date: {
    type: DataTypes.DATE,
    allowNull: false,
  },
  return_date: {
    type: DataTypes.DATE,
    allowNull: true,
  },
  status: {
    type: DataTypes.ENUM('ACTIVE', 'RETURNED'),
    defaultValue: 'ACTIVE',
  },
  extensions_count: {
    type: DataTypes.INTEGER,
    defaultValue: 0,
  }
});

module.exports = Loan;