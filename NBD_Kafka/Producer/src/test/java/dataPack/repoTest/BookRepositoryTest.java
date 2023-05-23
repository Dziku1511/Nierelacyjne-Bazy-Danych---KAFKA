package dataPack.repoTest;

import dataPack.data;
import model.Book;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BookRepositoryTest {

    private BookRepository bookRepository = new BookRepository();


    @Test
    void addBookTest() {
        bookRepository.addBook(data.book1);
        bookRepository.addBook(data.book2);
        ArrayList<Book> ls = bookRepository.findAll();
        assertEquals(2, ls.size());
    }

    @Test
    void addExistingBookTest() {
        bookRepository.drop();
        bookRepository.addBook(data.book1);
        bookRepository.addBook(data.book2);
        assertFalse(bookRepository.addBook(data.book1));
        ArrayList<Book> ls = bookRepository.findAll();
        assertEquals(2, ls.size());
    }

    @Test
    void dropTest() {
        bookRepository.drop();
        ArrayList<Book> ls = bookRepository.findAll();
        assertEquals(0, ls.size());
    }

    @Test
    void removeBookTest() {
        bookRepository.drop();
        UUID id = data.book1.getBookId();

        bookRepository.addBook(data.book1);
        bookRepository.addBook(data.book2);

        Book removed = bookRepository.removeBook(id.toString());
        assertEquals(removed.getBookId(), data.book1.getBookId());

        ArrayList<Book> ls = bookRepository.findAll();
        assertEquals(1, ls.size());
    }

    @Test
    void findAllTest() {
        bookRepository.drop();

        bookRepository.addBook(data.book1);
        bookRepository.addBook(data.book2);

        ArrayList<Book> ls = bookRepository.findAll();
        assertEquals(data.book1.getBookId(), ls.get(0).getBookId());
        assertEquals(data.book2.getBookId(), ls.get(1).getBookId());
    }
}
