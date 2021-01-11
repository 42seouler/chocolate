package chocolate.chocho.service.employer.store.query;

import chocolate.chocho.dto.StoreQueryDto;

import java.util.UUID;

public interface StoreQueryService {

    StoreQueryDto findById(UUID storeId);
}
