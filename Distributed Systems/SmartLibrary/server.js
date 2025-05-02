const express = require('express');
const pool = require('./db');

const app = express();
app.use(express.json());

app.get('/', (req, res) => {
  res.json({ message: 'Welcome to our Smart Library!' });
});

app.get('/showusers', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM users');
    res.json(result.rows);
  } catch (err) {
    console.error(err);
    res.status(500).send('Server error');
  }
});
app.post('/users', async (req, res) => {
  const { name, email,role } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO users (name, email,role) VALUES ($1, $2, $3) RETURNING *',
      [name, email,role]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error creating user');
  }
});
app.get('/users/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const result = await pool.query('SELECT * FROM users where id= $1',[id]);
    res.json(result.rows);
  } catch (err) {
    console.error(err);
    res.status(500).send('Server error');
  }
});



app.post('/books', async (req, res) => {
  const { title, author,isbn,copies } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO book(title, author,isbn,copies) VALUES ($1, $2, $3,$4) RETURNING *',
      [title, author,isbn,copies]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error creating book');
  }
});

app.get('/books/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const result = await pool.query('SELECT * FROM books where id= $1',[id]);
    res.json(result.rows);
  } catch (err) {
    console.error(err);
    res.status(500).send('Server error');
  }
});

app.put('/books/:id', async (req, res) => {
  const { id } = req.params;
  const { copies, available_copies } = req.body;
  try {
    const result = await pool.query(
      'UPDATE books SET copies = $1, available_copies = $2 WHERE id = $3 RETURNING *',
      [copies, available_copies, id]
    );
    res.json(result.rows[0]);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error updating books');
  }
});


app.delete('/books/:id', async (req, res) => {
  const { id } = req.params;
  try {
    await pool.query('DELETE FROM books WHERE id = $1', [id]);
    res.sendStatus(204);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error deleting book');
  }
});

app.post('/loans', async (req, res) => {
  const { user_id,book_id,due_date } = req.body;
  try {
    const result = await pool.query(
      'INSERT INTO loan(user_id,book_id,due_date) VALUES ($1, $2, $3) RETURNING *',
      [user_id,book_id,due_date]
    );
    res.status(201).json(result.rows[0]);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error creating loan');
  }
});

app.listen(3000, () => console.log('Server running on http://localhost:3000'));

