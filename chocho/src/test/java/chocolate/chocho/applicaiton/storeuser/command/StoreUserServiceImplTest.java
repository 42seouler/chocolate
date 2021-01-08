package chocolate.chocho.applicaiton.storeuser.command;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        Address address = new Address("seoul", "gaepo-dong", "42-42");
        StoreUserCmdDto dto = new StoreUserCmdDto("nakim", address);
        StoreUser storeUser = new StoreUser("nakim", address);
       //when
        when(storeUserRepository.save(any(StoreUser.class))).thenReturn(storeUser);
        //then
        UUID registerUserId = storeUserService.registerStoreUser(dto);
        assertEquals(registerUserId, storeUser.getId());
    }
}