package chocolate.chocho.service.employer.query;

import chocolate.chocho.dto.EmployerQueryDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.repository.EmployerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployerQueryServiceImplTest {

    @Mock
    EmployerRepository employerRepository;

    @InjectMocks
    EmployerQueryServiceImpl employerQueryService;

    @Test
    public void findById() throws Exception {
        //given
        Address address = createAddress("seoul", "songpa-dong", "4242");
        Employer employer = createEmployer("nakim", address);
        //when
        when(employerRepository.findById(any(UUID.class))).thenReturn(Optional.of(employer));
        EmployerQueryDto findEmployer = employerQueryService.findById(employer.getId());
        //then
        assertEquals(findEmployer.getName(), employer.getName());
        assertEquals(findEmployer.getAddress(), employer.getAddress());
    }

    private Employer createEmployer(String name, Address address) {
        return new Employer(UUID.randomUUID(), name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}