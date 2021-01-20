package chocolate.chocho.service;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        StoreCmdDto dto = new StoreCmdDto("starbucks",
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

    private Store createStore(Long id, String name, Address address) {
        return new Store(id, name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

}
