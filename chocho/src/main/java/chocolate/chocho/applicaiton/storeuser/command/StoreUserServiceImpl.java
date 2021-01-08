package chocolate.chocho.applicaiton.storeuser.command;

import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreUserServiceImpl implements StoreUserService {

    private final StoreUserRepository storeUserRepository;

    @Override
    public UUID registerStoreUser(StoreUserCmdDto dto) {
        StoreUser newStoreUser = transferDtoToEntity(dto);
        StoreUser saveStoreUser = storeUserRepository.save(newStoreUser);
        return saveStoreUser.getId();
    }

    @Override
    public StoreUserCmdDto updateStoreUser(UUID userId, StoreUserCmdDto dto) {
        StoreUser findStoreUser = storeUserRepository.findById(userId).orElseThrow();
        findStoreUser.update(transferDtoToEntity(dto));
        return transferEntityToDto(findStoreUser);
    }

    public StoreUser transferDtoToEntity(StoreUserCmdDto dto) {
        return new StoreUser(dto.getName(), dto.getAddress());
    }

    public StoreUserCmdDto transferEntityToDto(StoreUser storeUser) {
        return new StoreUserCmdDto(storeUser.getName(), storeUser.getAddress(), storeUser.getStore());
    }
}
