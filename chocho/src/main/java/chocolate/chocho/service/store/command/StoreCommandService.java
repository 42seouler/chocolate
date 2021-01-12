package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;

import java.util.UUID;

public interface StoreCommandService {

   UUID        create(UUID employerId, StoreCmdDto dto);

   StoreCmdDto update(UUID storeId, StoreCmdDto dto);

   void        delete(UUID storeId);
}
