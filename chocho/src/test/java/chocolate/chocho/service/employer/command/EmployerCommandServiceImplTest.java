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

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

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

    Employer createEmployer(String name, Address address) {
        return new Employer(UUID.randomUUID(), name, address);
    }

    Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}