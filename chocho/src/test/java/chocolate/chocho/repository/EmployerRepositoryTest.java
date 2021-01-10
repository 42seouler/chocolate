package chocolate.chocho.repository;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployerRepositoryTest {

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    TestEntityManager tm;

    @Test
    public void basicCRUD() throws Exception {
        //given
        Address address1 = createAddress("city", "street", "zipcode");
        Address address2 = createAddress("seoul", "songpa-dong", "4242");
        Employer employer1 = createEmployer(address1, "name");
        Employer employer2 = createEmployer(address2, "nakim");
        tm.persist(employer1);
        tm.persist(employer2);
        //when
        Employer findEmployer1 = employerRepository.findById(employer1.getId()).orElseThrow();
        Employer findEmployer2 = employerRepository.findById(employer2.getId()).orElseThrow();
        //then
        assertEquals(findEmployer1.getId(), employer1.getId());
        assertEquals(findEmployer1.getName(), employer1.getName());
        assertEquals(findEmployer1.getAddress(), employer1.getAddress());
        assertEquals(findEmployer2.getId(), employer2.getId());
        assertEquals(findEmployer2.getName(), employer2.getName());
        assertEquals(findEmployer2.getAddress(), employer2.getAddress());
    }

    private Employer createEmployer(Address address, String name) {
        return new Employer(name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

}