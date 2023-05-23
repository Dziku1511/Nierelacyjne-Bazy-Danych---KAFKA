package dataPack.repoTest;


import org.junit.Before;
import org.junit.Test;
import repository.RentRepository;

import static dataPack.data.rent1;

public class KafkaRentRepositoryTest {
    RentRepository rentRepository;


    @Before
    public void initialize() {
        rentRepository = new RentRepository();
        rentRepository.libraryDB.drop();
    }

    @Test
    public void addRent() {
        rentRepository.addRent(rent1);
    }
}
