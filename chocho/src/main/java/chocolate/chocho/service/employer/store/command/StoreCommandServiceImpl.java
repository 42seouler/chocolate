package chocolate.chocho.service.employer.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    @Override
    public UUID create(StoreCmdDto dto) {
        Store newStore = new Store(dto.getName(), dto.getAddress(), dto.getEmployer());
        Store saveStore = storeRepository.save(newStore);
        return saveStore.getId();
    }

    @Override
    public StoreCmdDto update(UUID storeId, StoreCmdDto dto) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        store.update(dto.getName(), dto.getAddress(), dto.getEmployer());
        return entityToDto(store);
    }

    @Override
    public void delete(UUID storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        storeRepository.delete(store);
    }

    StoreCmdDto entityToDto(Store store) {
        return new StoreCmdDto(store.getName(), store.getAddress(), store.getEmployer());
    }
}
