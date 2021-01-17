package chocolate.chocho.service.jobpost.command;

import chocolate.chocho.dto.JobPostCmdDto;
import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.JobPost;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.user.User;
import chocolate.chocho.repository.JobPostRepository;
import chocolate.chocho.repository.StoreMgmtRepository;
import chocolate.chocho.repository.StoreRepository;
import chocolate.chocho.repository.UserRepository;
import chocolate.chocho.service.storemgmt.command.StoreMgmtCmdServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JobPostServiceImplTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    JobPostRepository jobPostRepository;
    @Autowired
    StoreMgmtRepository storeMgmtRepository;
    @Autowired
    JobPostServiceImpl jobPostService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StoreMgmtCmdServiceImpl storeMgmtCmdService;
    Store   store;

    @BeforeEach
    void setup() {
        User seouler = new User("seouler", new Address());
        userRepository.save(seouler);
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks", new Address());
        storeMgmtCmdService.create(seouler.getId(), storeCmdDto);
        store = storeRepository.findByUser(seouler).orElseThrow();
    }

    @Test
    @Rollback(value = false)
    public void createJobPost() throws Exception {
        //given
        JobPostCmdDto jobPostCmdDto = new JobPostCmdDto("sample", "sample_body");
        //when
        Long postId = jobPostService.create(store.getId(), jobPostCmdDto);
        //then
        JobPost jobPost = jobPostRepository.findById(postId).orElseThrow();
        Assertions.assertEquals(jobPostCmdDto.getTitle(), jobPost.getTitle());
    }
}