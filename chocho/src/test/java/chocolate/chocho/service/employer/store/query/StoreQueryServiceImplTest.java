package chocolate.chocho.service.employer.store.query;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Employer;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreQueryServiceImplTest {

   @Mock
   StoreRepository storeRepository;

   @InjectMocks
   StoreQueryServiceImpl storeQueryService;

   @Test
   public void FindById() throws Exception {
      //given
      Address address = new Address("seoul", "gaepo-dong", "4242");
      Store store = new Store(UUID.randomUUID(), "starbucks", null, address);
      when(storeRepository.findById(any(UUID.class))).thenReturn(Optional.of(store));
      //when
      StoreQueryDto findStore = storeQueryService.findById(store.getId());
      //then
      assertEquals(findStore.getName(), store.getName());
      assertEquals(findStore.getAddress(), store.getAddress());
   }


}
