package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;

import java.util.UUID;

public interface StoreCmdService {
    Long create(UUID employerId, StoreCmdDto dto);

    StoreCmdDto update(Long storeMgmtId, StoreCmdDto dto);

    void        delete(Long storeMgmtId);
}
