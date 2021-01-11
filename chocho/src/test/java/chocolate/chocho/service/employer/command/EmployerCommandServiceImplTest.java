package chocolate.chocho.service.employer.command;

import chocolate.chocho.dto.EmployerCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.service.employer.query.EmployerQueryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@Transactional
@ExtendWith(MockitoExtension.class)
class EmployerCommandServiceImplTest {

    @Mock
    EmployerRepository employerRepository;

    @InjectMocks
    EmployerCommandServiceImpl employerCommandService;

    @Test
    public void createEmployer() throws Exception {
        //given
        Employer employer = createEmployer("seouler",
                createAddress("seoul", "gaepo-dong", "4242"));
        //when
        when(employerRepository.save(any(Employer.class))).thenReturn(employer);
        UUID uuidEmployer = employerCommandService.create(new EmployerCmdDto());
        //then
        Assertions.assertEquals(uuidEmployer, employer.getId());
    }

    @Test
    public void updateEmployer() throws Exception {
        //given
        Address address = createAddress("seoul", "songpa-dong", "4242");
        Employer employer = new Employer(UUID.randomUUID(), "busaner", address);
        Address updateAddress = createAddress("busan", "gaepon-dong", "2424");
        EmployerCmdDto employerCmdDto = new EmployerCmdDto("42seouler", updateAddress);
        //when
        when(employerRepository.findById(any(UUID.class))).thenReturn(Optional.of(employer));
        EmployerCmdDto updateEmployer = employerCommandService.update(employer.getId(), employerCmdDto);
        //then
        assertEquals(updateEmployer.getName(),employerCmdDto.getName());
        assertEquals(updateEmployer.getAddress(),employerCmdDto.getAddress());
    }

    Employer createEmployer(String name, Address address) {
        return new Employer(UUID.randomUUID(), name, address);
    }

    Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

/*
    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    EmployerCommandServiceImpl employerCommandService;
    @Test
    @Rollback(value = false)
    public void updatetest() throws Exception {
        //given
        Address address = createAddress("seoul", "songpa-dong", "4242");
        Employer employer = new Employer("seouler", address);
        employerRepository.save(employer);
        Address updateAddress = new Address("busan", "gaepo-dong", "24-24");
        EmployerCmdDto employerCmdDto = new EmployerCmdDto("42seouler", updateAddress);
        //when
        EmployerCmdDto updateEmployer = employerCommandService.update(employer.getId(), employerCmdDto);
        //then
        assertEquals(updateEmployer.getName(), employerCmdDto.getName());
        assertEquals(updateEmployer.getAddress(), employer.getAddress());
    }
*/
}