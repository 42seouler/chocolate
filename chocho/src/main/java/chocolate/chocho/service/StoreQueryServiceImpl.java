package chocolate.chocho.service;

import chocolate.chocho.dto.StoreQueryDto;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryServiceImpl {

    private final StoreRepository storeRepository;

    public StoreQueryDto findById(Long storeId) {
        Store findStore = storeRepository.findById(storeId).orElseThrow();
        return convertToDto(findStore);
    }

    public StoreQueryDto convertToDto(Store store) {
       return new StoreQueryDto(store.getName(),
               store.getAddress().getCity(),
               store.getAddress().getStreet(),
               store.getAddress().getZipcode());
    }
}
