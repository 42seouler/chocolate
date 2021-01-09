package chocolate.chocho.applicaiton.storeuser.query;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreUserQueryServiceImplTest {

    @Mock
    StoreUserRepository storeUserRepository;

    @InjectMocks
    StoreUserQueryServiceImpl storeUserQueryService;

    @Test
    public void 아이디로조회하기() throws Exception {
        //given
        Address userAddress = createAddress("seoul", "songpa-dong", "4242-42");
        StoreUser storeUser = createStoreUser("nakim", userAddress);
        //todo 연관관계 store도 가능 하도록 해줘야 한다.
//        Address storeAddress = createAddress("busan", "gaepo-dong", "2424-24");
        //when
        when(storeUserRepository.findById(any(UUID.class))).thenReturn(Optional.of(storeUser));
        StoreUserQueryDto findStoreUser = storeUserQueryService.findById(storeUser.getId());
        //then
        assertThat(findStoreUser.getName()).isEqualTo(storeUser.getName());
        assertThat(findStoreUser.getAddress()).isEqualTo(storeUser.getAddress());
    }

    private StoreUser createStoreUser(String name, Address userAddress) {
        return new StoreUser(UUID.randomUUID(), name, userAddress);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}