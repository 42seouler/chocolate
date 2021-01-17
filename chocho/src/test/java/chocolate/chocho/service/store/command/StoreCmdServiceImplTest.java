package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreMgmt;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreMgmtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreCmdServiceImplTest {

    @Autowired
    EmployerRepository          employerRepository;
    @Autowired
    StoreMgmtRepository         storeMgmtRepository;
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
        Long uuid = storeCmdService.create(employer.getId(), storeCmdDto);
        //then
        // 스토어 매니지먼트 생성 검증
        StoreMgmt storeMgmt = storeMgmtRepository.findById(uuid).orElseThrow();
        assertEquals(uuid, storeMgmt.getId());
        //스토어 생성 검증
        Store findStore = storeMgmt.getStore();
        assertEquals(storeCmdDto.getName(), findStore.getName());
        assertEquals(storeCmdDto.getAddress().getCity(), findStore.getAddress().getCity());
        assertEquals(storeCmdDto.getAddress().getStreet(), findStore.getAddress().getStreet());
        assertEquals(storeCmdDto.getAddress().getZipcode(), findStore.getAddress().getZipcode());
    }

    @Test
    @Rollback(value = false)
    public void updateStore() throws Exception {
        //given
        StoreCmdDto store = new StoreCmdDto("starbucks",
                createAddress("busan", "songpa", "42"));
        Long uuid = storeCmdService.create(employer.getId(), store);
        StoreMgmt storeMgmt = storeMgmtRepository.findById(uuid).orElseThrow();
        StoreCmdDto storeCmdDto = new StoreCmdDto("coffeeBean", createAddress("seoul", "gaepo", "24"));
        //when
        StoreCmdDto updateStore = storeCmdService.update(storeMgmt.getId(), storeCmdDto);
        //then
        assertEquals(storeCmdDto.getName(), store.getName());
        assertEquals(storeCmdDto.getAddress().getZipcode(), store.getAddress().getZipcode());
        assertEquals(storeCmdDto.getAddress().getStreet(), store.getAddress().getStreet());
        assertEquals(storeCmdDto.getAddress().getCity(), store.getAddress().getCity());
    }

    @Test
    public void deleteStore() throws Exception {
        //given
        StoreCmdDto store = new StoreCmdDto("starbucks",
                createAddress("busan", "songpa", "42"));
        Long uuid = storeCmdService.create(employer.getId(), store);
        StoreMgmt storeMgmt = storeMgmtRepository.findById(uuid).orElseThrow();
        //when
        storeCmdService.delete(uuid);
        //then
        assertEquals(0,storeMgmtRepository.count());
    }

    Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}