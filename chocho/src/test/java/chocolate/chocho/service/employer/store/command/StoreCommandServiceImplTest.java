package chocolate.chocho.service.employer.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreCommandServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreCommandServiceImpl storeCommandService;

    @Test
    public void createStore() throws Exception {
        //given
        Address address = createAddress("seoul", "songpa-dong", "4242");
        Store store = new Store("starbucsks", address, null);
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks", address, null);
        //when
        when(storeRepository.save(any(Store.class))).thenReturn(store);
        UUID uuidSaveStore = storeCommandService.create(storeCmdDto);
        //then
        Assertions.assertEquals(uuidSaveStore, store.getId());
    }

    @Test
    public void updateStore() throws Exception {
        //given
        Address address = createAddress("city", "street", "zipcode");
        Store store = new Store(UUID.randomUUID(), "name", address, null);
        Address addressDto = createAddress("busan", "gaepo-dong", "2424");
        StoreCmdDto storeCmdDto = new StoreCmdDto("startbucks", addressDto, null);
        //when
        when(storeRepository.findById(any(UUID.class))).thenReturn(Optional.of(store));
        StoreCmdDto result = storeCommandService.update(store.getId(), storeCmdDto);
        //then
        assertEquals(result.getName(), storeCmdDto.getName());
        assertEquals(result.getAddress(), storeCmdDto.getAddress());
        assertEquals(result.getEmployer(), storeCmdDto.getEmployer());
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}