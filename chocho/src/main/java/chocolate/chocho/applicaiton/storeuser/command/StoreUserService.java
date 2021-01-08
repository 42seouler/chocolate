package chocolate.chocho.applicaiton.storeuser.command;

import java.util.UUID;

public interface StoreUserService {
    UUID registerStoreUser(StoreUserCmdDto dto);
}
