package chocolate.chocho.service.store.query;

import chocolate.chocho.dto.StoreQueryDto;

public interface StoreQueryService {

    StoreQueryDto findById(Long storeMgmtId);
}
