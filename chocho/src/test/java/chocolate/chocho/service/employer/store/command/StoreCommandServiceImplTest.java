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
        Address address = new Address("seoul", "songpa-dong", "4242");
        Store store = new Store("starbucsks", address, null);
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks", address, null);
        //when
        when(storeRepository.save(any(Store.class))).thenReturn(store);
        UUID uuidSaveStore = storeCommandService.create(storeCmdDto);
        //then
        Assertions.assertEquals(uuidSaveStore, store.getId());
    }
}