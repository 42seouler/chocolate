package chocolate.chocho.service.storemgmt.command;

import chocolate.chocho.dto.StoreCmdDto;

import java.util.UUID;

public interface StoreMgmtCmdService {
    Long create(UUID userId, StoreCmdDto dto);

    StoreCmdDto update(Long storeMgmtId, StoreCmdDto dto);

    void        delete(Long storeMgmtId);
}
