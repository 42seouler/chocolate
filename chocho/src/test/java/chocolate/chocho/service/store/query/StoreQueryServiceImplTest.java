package chocolate.chocho.service.store.query;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
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
      Address address = createAddress("seoul", "gaepo-dong", "4242");
      Store store = createStore(address, "starbucks");
      //when
      when(storeRepository.findById(any(UUID.class))).thenReturn(Optional.of(store));
      StoreQueryDto findStore = storeQueryService.findById(store.getId());
      //then
      assertEquals(findStore.getName(), store.getName());
      assertEquals(findStore.getAddress(), store.getAddress());
   }

   @Test
   public void findAll() throws Exception {
      //given
      List<Store> stores =  new ArrayList<>();
      Address address = createAddress("city", "street", "zipcode");
      stores.add(createStore(address, "starbucks1"));
      stores.add(createStore(address, "starbucks2"));
      stores.add(createStore(address, "starbucks3"));
      stores.add(createStore(address, "starbucks4"));
      stores.add(createStore(address, "starbucks5"));
      PageImpl<Store> PageStores = new PageImpl<>(stores.subList(0, 3), PageRequest.of(0, 3), 3);
      //when
      when(storeRepository.findAll(any(PageRequest.class))).thenReturn(PageStores);
      Page<StoreQueryDto> findStores = storeQueryService.findAll(0, 3);
      //then
      assertEquals(findStores.getTotalElements(), PageStores.getTotalElements());
      assertEquals(findStores.getSize(), PageStores.getSize());
   }

   private Store createStore(Address address, String name) {
      return new Store(UUID.randomUUID(), name, address, null);
   }

   private Address createAddress(String city, String street, String zipcode) {
      return new Address(city, street, zipcode);
   }
}
