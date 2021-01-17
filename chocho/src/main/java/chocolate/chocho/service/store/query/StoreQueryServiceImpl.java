package chocolate.chocho.service.store.query;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.entity.StoreMgmt;
import chocolate.chocho.repository.StoreMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreMgmtRepository   storeMgmtRepository;

    @Override
    public StoreQueryDto findById(Long storeMgmtId) {
        StoreMgmt storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
        return null;
    }
}
