package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.*;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.JobOpeningRepository;
import chocolate.chocho.repository.PostManagementRepository;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
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
    StoreRepository             storeRepository;
    @Autowired
    EmployerRepository          employerRepository;
    @Autowired
    JobOpeningRepository        jobOpeningRepository;
    @Autowired
    PostManagementRepository    postManagementRepository;
    @Autowired
    StoreCommandServiceImpl     storeCommandService;

    @Test
    public void createStore() throws Exception {
        //given
        Employer employer = new Employer("seouler",
                createAddress("seoul", "songpa", "42"));
        employerRepository.save(employer);
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks",
                createAddress("busan", "gaepo", "24"));
        //when
        UUID uuid = storeCommandService.create(employer.getId(), storeCmdDto);
        //then
        Store findStore = storeRepository.findById(uuid).orElseThrow();
        JobOpening findJobOpening = jobOpeningRepository.findByStore(findStore).orElseThrow();
        PostManagement findPostManagement = postManagementRepository.findByStore(findStore).orElseThrow();
        assertEquals(findStore.getName(), storeCmdDto.getName());
        assertEquals(findStore.getAddress(), storeCmdDto.getAddress());
        assertEquals(findStore.getId(), findJobOpening.getStore().getId());
        assertEquals(findStore.getId(), findPostManagement.getStore().getId());
    }

    @Test
    public void updateStore() throws Exception {
        Employer employer = new Employer("seouler",
                createAddress("seoul", "songpa", "42"));
        employerRepository.save(employer);
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks",
                createAddress("busan", "gaepo", "24"));
        UUID uuid = storeCommandService.create(employer.getId(), storeCmdDto);
        StoreCmdDto updateStoreCmdDto = new StoreCmdDto("coffeebean",
                createAddress("incheon", "unknown", "20"));
        //when
        StoreCmdDto updateStore = storeCommandService.update(uuid, updateStoreCmdDto);
        //then
        assertEquals(updateStore.getName(), updateStoreCmdDto.getName());
        assertEquals(updateStore.getAddress(), updateStoreCmdDto.getAddress());
    }



    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}
