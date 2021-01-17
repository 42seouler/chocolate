package chocolate.chocho.service.storemgmt.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreMgmt;
import chocolate.chocho.entity.user.User;
import chocolate.chocho.repository.StoreMgmtRepository;
import chocolate.chocho.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreMgmtCmdServiceImpl implements StoreMgmtCmdService {

    private final UserRepository userRepository;
    private final StoreMgmtRepository   storeMgmtRepository;

    @Override
    public Long create(UUID userId, StoreCmdDto dto) {
        //todo 유저의 역활이 EMPLOYER 인지 확인
        User user = userRepository.findById(userId).orElseThrow();
        Store store = new Store(dto.getName(), dto.getAddress(), user);
        StoreMgmt storeMgmt = new StoreMgmt(store);
        storeMgmtRepository.save(storeMgmt);
        return storeMgmt.getId();
    }

    @Override
    public StoreCmdDto update(Long storeMgmtId, StoreCmdDto dto) {
        StoreMgmt storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
        Store store = storeMgmt.getStore();
        store.update(dto.getName(), dto.getAddress());
        //todo storeManagement 지하철 호선 저장하는 것 추가하기
        return entityToDto(store);
    }

    @Override
    public void delete(Long storeMgmtId) {
        StoreMgmt storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
        storeMgmtRepository.delete(storeMgmt);
    }

    private StoreCmdDto entityToDto(Store store) {
        return new StoreCmdDto(store.getName(), store.getAddress());
    }
}
