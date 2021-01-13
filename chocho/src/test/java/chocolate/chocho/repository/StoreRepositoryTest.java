package chocolate.chocho.repository;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    TestEntityManager tm;

    @Test
    public void create() throws Exception {
        //given
        //when
        //then
    }

    private Employer createEmployer(String name, Address address) {
        return new Employer(name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}