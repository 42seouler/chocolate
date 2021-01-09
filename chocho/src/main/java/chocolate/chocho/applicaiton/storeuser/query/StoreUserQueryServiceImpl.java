package chocolate.chocho.applicaiton.storeuser.query;

import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreUserQueryServiceImpl implements StoreUserQueryService {

    private final StoreUserRepository storeUserRepository;

    @Override
    public StoreUserQueryDto findById(UUID userId) {
        StoreUser findByStoreUser = storeUserRepository.findById(userId).orElseThrow();
        return transferStoreUserToStoreQueryDto(findByStoreUser);
    }

    StoreUserQueryDto transferStoreUserToStoreQueryDto(StoreUser storeUser) {
        return new StoreUserQueryDto(
                storeUser.getName(),
                storeUser.getAddress(),
                storeUser.getStore());
    }
}
