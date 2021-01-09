package chocolate.chocho.applicaiton.storeuser.query;

import java.util.UUID;

public interface StoreUserQueryService {
    StoreUserQueryDto findById(UUID userId);
}
