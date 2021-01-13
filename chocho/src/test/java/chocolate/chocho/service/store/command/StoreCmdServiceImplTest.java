package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreManagement;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreManagementRepository;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreCmdServiceImplTest {

    @Autowired
    EmployerRepository          employerRepository;
    @Autowired
    StoreRepository             storeRepository;
    @Autowired
    StoreManagementRepository   storeManagementRepository;
    @Autowired
    StoreCmdServiceImpl         storeCmdService;
    Employer                    employer;

    @BeforeEach
    void CreateEmployer() {
        employer = new Employer("seouler",
                createAddress("busan", "gaepo", "24"));
        employerRepository.save(employer);
    }

    @Test
    @Rollback(value = false)
    public void createStore() throws Exception {
        //given
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks",
                createAddress("seoul", "songpa", "42"));
        //when
        UUID uuid = storeCmdService.create(employer.getId(), storeCmdDto);
        //then
        //스토어 생성 검증
        Store findStore = storeRepository.findById(uuid).orElseThrow();
        assertEquals(storeCmdDto.getName(), findStore.getName());
        assertEquals(storeCmdDto.getAddress().getCity(), findStore.getAddress().getCity());
        assertEquals(storeCmdDto.getAddress().getStreet(), findStore.getAddress().getStreet());
        assertEquals(storeCmdDto.getAddress().getZipcode(), findStore.getAddress().getZipcode());
        // 스토어 매니지먼트 생성 검증
        StoreManagement storeManagement = storeManagementRepository.findByStore(findStore).orElseThrow();
        assertEquals(storeManagement.getStore().getId(), findStore.getId());
    }

    @Test
    @Rollback(value = false)
    public void updateStore() throws Exception {
        //given
        Store store = new Store("starbucks",
                createAddress("busan", "songpa", "42"),
                employer);
        storeRepository.save(store);
        StoreCmdDto storeCmdDto = new StoreCmdDto("coffeeBean", createAddress("seoul", "gaepo", "24"));
        //when
        StoreCmdDto updateStore = storeCmdService.update(store.getId(), storeCmdDto);
        //then
        assertEquals(storeCmdDto.getName(), store.getName());
        assertEquals(storeCmdDto.getAddress().getZipcode(), store.getAddress().getZipcode());
        assertEquals(storeCmdDto.getAddress().getStreet(), store.getAddress().getStreet());
        assertEquals(storeCmdDto.getAddress().getCity(), store.getAddress().getCity());
    }

    Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}