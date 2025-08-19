// db.js
const { Pool } = require('pg');

const pool = new Pool({
  user: 'librarian',
  host: 'localhost',
  database: 'smartlibrary',
  password: 'librarian',
  port: 5432, // default PostgreSQL port
});

module.exports = pool;

