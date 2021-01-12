package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.JobOpening;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository       storeRepository;
    private final EmployerRepository    employerRepository;

    @Override
    public UUID create(UUID employerId, StoreCmdDto dto) {
        Employer employer = employerRepository.findById(employerId).orElseThrow();
        Store newStore = new Store(dto.getName(), dto.getAddress(), employer);
        newStore.createJobOpening();
        Store saveStore = storeRepository.save(newStore);
        return saveStore.getId();
    }

    @Override
    public StoreCmdDto update(UUID storeId, StoreCmdDto dto) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        store.update(dto.getName(), dto.getAddress());
        return entityToDto(store);
    }

    @Override
    public void delete(UUID storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        storeRepository.delete(store);
    }

    StoreCmdDto entityToDto(Store store) {
        return new StoreCmdDto(store.getName(), store.getAddress());
    }
}
