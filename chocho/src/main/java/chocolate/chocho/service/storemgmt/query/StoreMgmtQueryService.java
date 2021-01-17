package chocolate.chocho.service.storemgmt.query;

import chocolate.chocho.dto.StoreMgmtQueryDto;
import chocolate.chocho.dto.StoreQueryDto;

public interface StoreMgmtQueryService {

    StoreMgmtQueryDto findById(Long storeMgmtId);

}
