package chocolate.chocho.service;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCmdServiceImpl {

    private final StoreRepository storeRepository;

    public Long registerStore(StoreCmdDto dto) {
        Store saveStore = storeRepository.save(new Store(dto.getName(),
                new Address(dto.getCity(), dto.getStreet(), dto.getZipcode())));
        return saveStore.getId();
    }

    public StoreCmdDto updateStore(Long id, StoreCmdDto dto) {
        return null;
    }
}
