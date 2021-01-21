package chocolate.chocho.service;

import chocolate.chocho.dto.StoreCreateDto;
import chocolate.chocho.dto.StoreUpdateDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreCmdServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreCmdServiceImpl storeService;

    @Test
    public void create() throws Exception {
        //given
        StoreCreateDto dto = new StoreCreateDto("starbucks",
                "city",
                "street",
                "zipcode");
        Store store = createStore(1L, "starbucks", createAddress("city", "street", "zipcode"));
        given(storeRepository.save(any(Store.class))).willReturn(store);
        //when
        Long saveId = storeService.registerStore(dto);
        //then
        Assertions.assertThat(saveId).isEqualTo(1L);
    }

    @Test
    public void update() throws Exception {
        //given
        Store store = new Store(1L,
                "starbucks",
                createAddress("seoul", "songpa", "42"));
        StoreUpdateDto updateDto = new StoreUpdateDto("busan", "gaepo-dong", "2424");
        given(storeRepository.findById(any(Long.class))).willReturn(Optional.of(store));
        //when
        StoreUpdateDto storeUpdateDto = storeService.updateStore(store.getId(), updateDto);
        //then
        assertEquals(updateDto.getCity(), storeUpdateDto.getCity());
        assertEquals(updateDto.getStreet(), storeUpdateDto.getStreet());
        assertEquals(updateDto.getZipcode(), storeUpdateDto.getZipcode());
    }

    @Test
    public void delete() throws Exception {
        //given
        Store store = new Store(1L, "starbucks:", createAddress("seoul", "songpa", "42"));
        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        //when
        storeService.deleteStore(store.getId());
        //then
        verify(storeRepository, times(1)).findById(any(Long.class));
        verify(storeRepository, times(1)).delete(any(Store.class));
    }

    private Store createStore(Long id, String name, Address address) {
        return new Store(id, name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

}
