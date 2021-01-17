package chocolate.chocho.service.jobpost.command;


import chocolate.chocho.dto.JobPostCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.entity.JobPost;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.JobPostRepository;
import chocolate.chocho.repository.StoreMgmtRepository;
import chocolate.chocho.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService {

    private final StoreRepository       storeRepository;
    private final JobPostRepository     jobPostRepository;
    private final StoreMgmtRepository   storeMgmtRepository;

    @Override
    public Long create(UUID storeId, JobPostCmdDto dto) {
        Store findStore = storeRepository.findById(storeId).orElseThrow();
        JobPost newJobPost = new JobPost(findStore, dto.getTitle(), dto.getBody());
        jobPostRepository.save(newJobPost);
        return newJobPost.getId();
    }


}
