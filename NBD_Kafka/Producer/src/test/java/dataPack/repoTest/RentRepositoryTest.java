package dataPack.repoTest;

import dataPack.data;
import model.Rent;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.RentRepository;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RentRepositoryTest {

    BookRepository bookRepository = new BookRepository();
    RentRepository rentRepository = new RentRepository();


    @BeforeEach
    void putBooksInLibrary() {
        bookRepository.addBook(data.book1);
        bookRepository.addBook(data.book2);
        bookRepository.addBook(data.book3);
    }
    @Test
    void addRentTest() {
        rentRepository.drop();
        rentRepository.addRent(data.rent1);
        rentRepository.addRent(data.rent2);
        ArrayList<Rent> ls = rentRepository.findAll();
        assertEquals(2, ls.size());
    }

    @Test
    void addExistingBookTest() {
        rentRepository.drop();
        rentRepository.addRent(data.rent1);
        rentRepository.addRent(data.rent2);
        assertFalse(rentRepository.addRent(data.rent1));
        ArrayList<Rent> ls = rentRepository.findAll();
        assertEquals(2, ls.size());
    }

    @Test
    void dropTest() {
        rentRepository.drop();
        ArrayList<Rent> ls = rentRepository.findAll();
        assertEquals(0, ls.size());
    }

    @Test
    void removeBookTest() {
        rentRepository.drop();
        UUID id = data.rent1.getRentId();

        rentRepository.addRent(data.rent1);
        rentRepository.addRent(data.rent2);

        Rent removed = rentRepository.removeRent(id.toString());
        assertEquals(removed, data.rent1);

        ArrayList<Rent> ls = rentRepository.findAll();
        assertEquals(1, ls.size());
    }

    @Test
    void findAllTest() {
        rentRepository.drop();

        rentRepository.addRent(data.rent1);
        rentRepository.addRent(data.rent2);

        ArrayList<Rent> ls = rentRepository.findAll();
        assertEquals(data.rent1, ls.get(0));
        assertEquals(data.rent2, ls.get(1));
    }
}
