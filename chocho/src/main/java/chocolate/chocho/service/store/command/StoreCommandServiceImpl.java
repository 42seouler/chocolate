package chocolate.chocho.service.store.command;

import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.JobOpening;
import chocolate.chocho.entity.PostManagement;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.JobOpeningRepository;
import chocolate.chocho.repository.PostManagementRepository;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository           storeRepository;
    private final EmployerRepository        employerRepository;
    private final JobOpeningRepository      jobOpeningRepository;
    private final PostManagementRepository  postManagementRepository;

    @Override
    public UUID create(UUID employerId, StoreCmdDto dto) {
        Employer employer = employerRepository.findById(employerId).orElseThrow();
        Store newStore = new Store(dto.getName(), dto.getAddress(), employer);
        jobOpeningRepository.save(new JobOpening(newStore));
        postManagementRepository.save(new PostManagement(newStore));
        Store saveStore = storeRepository.save(newStore);
        return saveStore.getId();
    }

    @Override
    public StoreCmdDto update(UUID storeId, StoreCmdDto dto) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        store.update(dto.getName(), dto.getAddress());
        //todo 주소값이 바뀜에 따라 설정한 지하철역이 바뀌어야 한다.
        return entityToDto(store);
    }

    @Override
    public void delete(UUID storeId) {
        Store findStore = storeRepository.findById(storeId).orElseThrow();
        JobOpening findJobOpening = jobOpeningRepository.findByStore(findStore).orElseThrow();
        PostManagement findPostManagement = postManagementRepository.findByStore(findStore).orElseThrow();
        storeRepository.delete(findStore);
        jobOpeningRepository.delete(findJobOpening);
        postManagementRepository.delete(findPostManagement);
    }

    StoreCmdDto entityToDto(Store store) {
        return new StoreCmdDto(store.getName(), store.getAddress());
    }
}
