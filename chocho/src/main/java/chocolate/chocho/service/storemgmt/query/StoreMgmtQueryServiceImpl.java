package chocolate.chocho.service.storemgmt.query;

import chocolate.chocho.dto.StoreMgmtQueryDto;
import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreMgmt;
import chocolate.chocho.repository.StoreMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreMgmtQueryServiceImpl implements StoreMgmtQueryService {

    private final StoreMgmtRepository   storeMgmtRepository;

    @Override
    public StoreMgmtQueryDto findById(Long storeMgmtId) {
        StoreMgmt storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
        return entityToDto(storeMgmt);
    }

    private StoreMgmtQueryDto entityToDto(StoreMgmt storeMgmt) {
        Store store = storeMgmt.getStore();
        StoreQueryDto storeQueryDto = new StoreQueryDto(store.getName(), store.getAddress(), store.getUser());
        return new StoreMgmtQueryDto(storeQueryDto, storeMgmt.getJobOpening());
    }
}
