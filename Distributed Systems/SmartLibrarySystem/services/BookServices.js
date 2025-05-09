const bookRepository = require('../repositories/BookRepository');

const addBook = async (bookData) => {
  // Add additional business logic here (e.g., data validation)
  return await bookRepository.addBook(bookData);
};

const getBook = async (bookID) => {
  const book = await bookRepository.getBookById(bookID);
  if (!book) {
    throw new Error('Book not found');
  }
  return book;
};
const updateBook = async (bookID,bookData) => {
    const book = await bookRepository.updateBook(bookID,bookData);
    if (!book) {
      throw new Error('Book not found');
    }
    return book;
  };

const deleteBook = async (bookID) => {
    const book = await bookRepository.deleteBook(bookID);
    if (!book) {
      throw new Error('Book not found');
    }
    return book;
  };  

module.exports = {
  addBook,
  getBook,
  updateBook,
  deleteBook,
};
