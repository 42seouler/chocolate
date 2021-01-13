package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreManagement;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCmdServiceImpl implements StoreCmdService {

    private final EmployerRepository        employerRepository;
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
        return null;
    }

    @Override
    public void delete(UUID storeId) {

    }
}
