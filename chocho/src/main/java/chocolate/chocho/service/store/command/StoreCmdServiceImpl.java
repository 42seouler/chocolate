package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreManagement;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreManagementRepository;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCmdServiceImpl implements StoreCmdService {

    private final EmployerRepository        employerRepository;
    private final StoreRepository           storeRepository;
    private final StoreManagementRepository storeManagementRepository;

    @Override
    public UUID create(UUID employerId, StoreCmdDto dto) {
        Employer employer = employerRepository.findById(employerId).orElseThrow();
        Store store = new Store(dto.getName(), dto.getAddress(), employer);
        StoreManagement storeManagement = new StoreManagement(store);
        storeManagementRepository.save(storeManagement);
        return store.getId();
    }

    @Override
    public StoreCmdDto update(UUID storeId, StoreCmdDto dto) {
        Store findStore = storeRepository.findById(storeId).orElseThrow();
        // StoreManagement storeManagement = storeManagementRepository.findByStore(findStore).orElseThrow();
        findStore.update(dto.getName(), dto.getAddress());
        //todo storeManagement 지하철 호선 저장하는 것 추가하기
        return entityToDto(findStore);
    }

    @Override
    public void delete(UUID storeId) {
    }

    private StoreCmdDto entityToDto(Store store) {
        return new StoreCmdDto(store.getName(), store.getAddress());
    }
}
