const express = require('express');
const router = express.Router();
const bookController = require('../BookController');


// Get user by ID
router.get('/:id', bookController.fetchBook);

router.get('/',bookController.searchBooks)



router.post('/', bookController.addBook);


router.put('/:id', bookController.updateBook);

router.delete('/:id', bookController.deleteBook);



module.exports = router;
