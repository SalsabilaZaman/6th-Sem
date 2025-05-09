const userRepository = require('../repositories/UserRepository');

const registerUser = async (userData) => {
  // Add additional business logic here (e.g., data validation)
  return await userRepository.createUser(userData);
};

const getUserProfile = async (userId) => {
  const user = await userRepository.getUserById(userId, {
    attributes: { exclude: ['createdAt', 'updatedAt'] }
  });
  if (!user) {
    throw new Error('User not found');
  }
  return user;
};

module.exports = {
  registerUser,
  getUserProfile,
};
