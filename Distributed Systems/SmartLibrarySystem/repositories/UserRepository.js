const User = require('../models/User');

const createUser = async (userData) => {
  return await User.create(userData);
};

const getUserById = async (userId, options = {}) => {
  return await User.findByPk(userId, options);
};


module.exports = {
  createUser,
  getUserById,
};
