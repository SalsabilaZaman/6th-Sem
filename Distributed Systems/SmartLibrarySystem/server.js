require('dotenv').config();
const express = require('express');

const sequelize = require('./config/db');


// (async () => {
//   try {
//     await sequelize.query('DROP TYPE IF EXISTS "enum_Users_role" CASCADE;');
//     console.log("Enum type dropped successfully.");

//     await sequelize.sync({ force: true });
//     console.log("Database synced successfully.");
//   } catch (err) {
//     console.error("Error syncing database:", err);
//   }
// })();

const userRoutes = require('./api/routes/UserRoute');
const bookRoutes = require('./api/routes/BookRoute');
const loanRoutes = require('./api/routes/LoanRoute');
const statsRoute = require('./api/routes/StatsRoute');
const app = express();
app.use(express.json());

// Register Routes
app.use('/api/users', userRoutes);
app.use('/api/books', bookRoutes);
app.use('/api/loans',loanRoutes);
app.use('/api/stats',statsRoute);

// Start the server
sequelize.sync().then(() => {
  app.listen(process.env.PORT || 3000, () => {
    console.log(`Server is running on port ${process.env.PORT || 3000}`);
  });
});
