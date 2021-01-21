package chocolate.chocho.service;

import chocolate.chocho.dto.StoreCreateDto;
import chocolate.chocho.dto.StoreUpdateDto;
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

    public Long registerStore(StoreCreateDto dto) {
        Store saveStore = storeRepository.save(new Store(dto.getName(),
                new Address(dto.getCity(), dto.getStreet(), dto.getZipcode())));
        return saveStore.getId();
    }

    public StoreUpdateDto updateStore(Long id, StoreUpdateDto dto) {
        Store store = storeRepository.findById(id).orElseThrow();
        store.update(dto.getCity(), dto.getStreet(), dto.getZipcode());
        return convertToUpdateDto(store.getAddress());
    }

    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        storeRepository.delete(store);
    }

    private StoreUpdateDto convertToUpdateDto(Address address) {
        return new StoreUpdateDto(address.getCity(), address.getStreet(), address.getZipcode());
    }
}
