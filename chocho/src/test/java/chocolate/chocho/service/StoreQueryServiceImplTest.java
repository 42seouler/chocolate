package chocolate.chocho.service;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreQueryServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreQueryServiceImpl storeQueryService;

    @Test
    public void findById() throws Exception {
        //given
        Store store = new Store(1L, "starbucks", new Address("seoul", "songpa", "42"));
        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        //when
        StoreQueryDto findStore = storeQueryService.findById(store.getId());
        //then
        Assertions.assertEquals(store.getName(), findStore.getName());
    }


}
