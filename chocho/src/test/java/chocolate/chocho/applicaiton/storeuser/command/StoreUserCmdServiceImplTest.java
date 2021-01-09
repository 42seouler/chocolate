package chocolate.chocho.applicaiton.storeuser.command;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
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
class StoreUserCmdServiceImplTest {

    @Mock
    StoreUserRepository storeUserRepository;

    @InjectMocks
    StoreUserCmdServiceImpl storeUserService;

    @Test
    public void 유저등록하기() throws Exception {
        //given
        Address address = createAddress("seoul", "gaepo-dong", "42-42");
        StoreUserCmdDto dto = createCmdDto(address, "nakim");
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
        StoreUserCmdDto updateInfo = createCmdDto(address, "Hakim");
        //when
        when(storeUserRepository.findById(any(UUID.class))).thenReturn(Optional.of(storeUser));
        storeUserService.updateStoreUserInfo(storeUser.getId(), updateInfo);
        //then
        assertEquals(storeUser.getName(), updateInfo.getName());
    }

    @Test
    public void 유저삭제하기() throws Exception {
        //given
        Address busan = createAddress("busan", "songpa-dong", "4242-42");
        StoreUser user = createStoreUser(busan, "nakim");
        //when
        when(storeUserRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        //then
        storeUserService.deleteStoreUser(user.getId());
        verify(storeUserRepository).delete(user);
    }

    private StoreUserCmdDto createCmdDto(Address address, String name) {
        return new StoreUserCmdDto(name, address);
    }

    private StoreUser createStoreUser(Address address, String name) {
        return new StoreUser(UUID.randomUUID(), name, address);
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}
