package chocolate.chocho.applicaiton.storeuser.command;

import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        StoreUser storeUser = transferDtoToEntity(dto);
        StoreUser saveStoreUser = storeUserRepository.save(storeUser);
        return saveStoreUser.getId();
    }

    public StoreUser transferDtoToEntity(StoreUserCmdDto dto) {
        return new StoreUser(dto.getName(), dto.getAddress());
    }
}
