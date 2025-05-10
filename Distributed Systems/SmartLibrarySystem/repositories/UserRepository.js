const User = require('../models/User');

const createUser = async (userData) => {
  return await User.create(userData);
};

const getUserById = async (userId, options = {}) => {
  return await User.findByPk(userId, options);
};
const findAll=async() => {
  return await User.findAll();
};
const count = async () => {
  return await User.count();
};


module.exports = {
  createUser,
  getUserById,
  findAll,count
};
