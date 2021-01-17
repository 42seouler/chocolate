package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreMgmt;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreMgmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCmdServiceImpl implements StoreCmdService {

    private final EmployerRepository    employerRepository;
    private final StoreMgmtRepository   storeMgmtRepository;

    @Override
    public Long create(UUID employerId, StoreCmdDto dto) {
        Employer employer = employerRepository.findById(employerId).orElseThrow();
        Store store = new Store(dto.getName(), dto.getAddress(), employer);
        StoreMgmt storeMgmt = new StoreMgmt(store);
        storeMgmtRepository.save(storeMgmt);
        return storeMgmt.getId();
    }

    @Override
    public StoreCmdDto update(Long storeMgmtId, StoreCmdDto dto) {
        StoreMgmt storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
        Store store = storeMgmt.getStore();
        store.update(dto.getName(), dto.getAddress());
        //todo storeManagement 지하철 호선 저장하는 것 추가하기
        return entityToDto(store);
    }

    @Override
    public void delete(Long storeMgmtId) {
        StoreMgmt storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
        storeMgmtRepository.delete(storeMgmt);
    }

    private StoreCmdDto entityToDto(Store store) {
        return new StoreCmdDto(store.getName(), store.getAddress());
    }
}
