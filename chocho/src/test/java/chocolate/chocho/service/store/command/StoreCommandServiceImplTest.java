package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class StoreCommandServiceImplTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    StoreCommandServiceImpl storeCommandService;

    @Test
    @Rollback(value = false)
    public void createStore() throws Exception {
        //given
        Address employerAddress = new Address("seoul", "songpa-dong", "42");
        Employer employer = new Employer("seouler", employerAddress);
        employerRepository.save(employer);
        Address storeAddress = new Address("busan", "gaepo-dong", "24");
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks", storeAddress);
        //when
        UUID uuid = storeCommandService.create(employer.getId(), storeCmdDto);
        //then
        Store findStore = storeRepository.findById(uuid).orElseThrow();
        assertEquals(findStore.getName(), storeCmdDto.getName());
        assertEquals(findStore.getEmployer(), employer);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}