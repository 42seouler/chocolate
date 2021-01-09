package chocolate.chocho.applicaiton.storeuser.command;

import java.util.UUID;

public interface StoreUserCmdService {

    UUID            registerStoreUser(StoreUserCmdDto dto);
    StoreUserCmdDto updateStoreUserInfo(UUID userId, StoreUserCmdDto dto);
    void            deleteStoreUser(UUID userId);
}
