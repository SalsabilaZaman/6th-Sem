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

const updateUser = async (userId, updateData) => {
  try {
    const allowedFields = ["name", "email"];
    const validData = {};

    // Only allow updating specified fields
    allowedFields.forEach((field) => {
      if (updateData[field] !== undefined) {
        validData[field] = updateData[field];
      }
    });

    return await userRepository.updateUser(userId, validData);
  } catch (err) {
    throw new Error(err.message);
  }
};

module.exports = {
  registerUser,
  getUserProfile,updateUser
};
