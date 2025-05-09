const bookService = require('../services/BookServices');

exports.addBook = async (req, res) => {
  try {
    const book = await bookService.addBook(req.body);
    res.status(201).json(book);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

exports.getBook = async (req, res) => {
  try {
    const book = await bookService.getBook(req.params.id);
    res.json(book);
  } catch (err) {
    res.status(404).json({ error: err.message });
  }
};

exports.updateBook = async (req, res) => {
    try {
      const book = await bookService.updateBook(req.params.id,req.body);
      res.json(book);
    } catch (err) {
      res.status(404).json({ error: err.message });
    }
  };
  

exports.deleteBook = async (req, res) => {
  try {
    const deletedBook = await bookService.deleteBook(req.params.id);
    res.json(deletedBook);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};
