package chocolate.chocho.applicaiton.storeuser.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface StoreUserQueryService {
    StoreUserQueryDto           findById(UUID userId);
    Page<StoreUserQueryDto>     findByAll(PageRequest pageRequest);
}
