package chocolate.chocho.applicaiton.storeuser.command;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreUserServiceImplTest {

    @Mock
    StoreUserRepository storeUserRepository;

    @InjectMocks
    StoreUserServiceImpl storeUserService;

    @Test
    public void 유저등록하기() throws Exception {
        //given
        Address address = createAddress("seoul", "gaepo-dong", "42-42");
        StoreUserCmdDto dto = new StoreUserCmdDto("nakim", address);
        StoreUser storeUser = createStoreUser(address, "Hakim");
       //when
        when(storeUserRepository.save(any(StoreUser.class))).thenReturn(storeUser);
        //then
        UUID registerUserId = storeUserService.registerStoreUser(dto);
        assertEquals(registerUserId, storeUser.getId());
    }


    @Test
    public void 유저수정하기() throws Exception {
        //given
        // 원본 유저 정보
        Address address = createAddress("busan", "songpa-dong", "4242-42");
        StoreUser storeUser = createStoreUser(address, "wow");
        // 수정 된 유저 정보
        StoreUserCmdDto updateInfo = new StoreUserCmdDto("Hakim", address);
        //when
        when(storeUserRepository.findById(any(UUID.class))).thenReturn(Optional.of(storeUser));
        storeUserService.updateStoreUser(storeUser.getId(), updateInfo);
        //then
        assertEquals(storeUser.getName(), updateInfo.getName());
    }

    private StoreUser createStoreUser(Address address, String name) {
        return new StoreUser(UUID.randomUUID(), name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}
