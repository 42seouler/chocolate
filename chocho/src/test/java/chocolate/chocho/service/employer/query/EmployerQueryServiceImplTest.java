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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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
        Employer employer = createEmployer("seouler", address);
        //when
        when(employerRepository.findById(any(UUID.class))).thenReturn(Optional.of(employer));
        EmployerQueryDto findEmployer = employerQueryService.findById(employer.getId());
        //then
        assertEquals(findEmployer.getName(), employer.getName());
        assertEquals(findEmployer.getAddress(), employer.getAddress());
    }

    @Test
    public void findByAll() throws Exception {
        //given
        Address address = createAddress("seoul", "songpa-dong", "4242");
        ArrayList<Employer> employers = new ArrayList<>();
        employers.add(createEmployer("seouler1", address));
        employers.add(createEmployer("seouler2", address));
        employers.add(createEmployer("seouler3", address));
        employers.add(createEmployer("seouler4", address));
        employers.add(createEmployer("seouler5", address));
        employers.add(createEmployer("seouler6", address));
        //주의 PageImpl은 사용자 단계에서 사용하는 클레스가 아니기 때문에 pagenation이 되지 않는다.
        PageImpl<Employer> pageEmployers = new PageImpl<Employer>(employers, PageRequest.of(0, 6), 6);
        //when
        when(employerRepository.findAll(any(Pageable.class))).thenReturn(pageEmployers);
        Page<EmployerQueryDto> result = employerQueryService.findAll(0, 6);
        //then
        assertEquals(result.getTotalElements(),pageEmployers.getTotalElements());
        assertEquals(result.getSize(), pageEmployers.getSize());
    }


    private Employer createEmployer(String name, Address address) {
        return new Employer(UUID.randomUUID(), name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}