package chocolate.chocho.service.jobpost.command;

import chocolate.chocho.dto.JobPostCmdDto;
import chocolate.chocho.dto.StoreCmdDto;
import chocolate.chocho.entity.*;
import chocolate.chocho.entity.user.User;
import chocolate.chocho.repository.JobPostRepository;
import chocolate.chocho.repository.StoreMgmtRepository;
import chocolate.chocho.repository.StoreRepository;
import chocolate.chocho.repository.UserRepository;
import chocolate.chocho.service.storemgmt.command.StoreMgmtCmdServiceImpl;
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
    StoreMgmt storeMgmt;

    @BeforeEach
    void setup() {
        User seouler = new User("seouler", new Address());
        userRepository.save(seouler);
        StoreCmdDto storeCmdDto = new StoreCmdDto("starbucks", new Address());
        Long storeMgmtId = storeMgmtCmdService.create(seouler.getId(), storeCmdDto);
        storeMgmt = storeMgmtRepository.findById(storeMgmtId).orElseThrow();
    }

    @Test
    public void createJobPost() throws Exception {
        //given
        JobPostCmdDto jobPostCmdDto = new JobPostCmdDto("sample", "sample_body");
        //when
        Long postId = jobPostService.create(storeMgmt.getId(), jobPostCmdDto);
        //then
        JobPost jobPost = jobPostRepository.findById(postId).orElseThrow();
        assertEquals(jobPostCmdDto.getTitle(), jobPost.getTitle());
        assertEquals(jobPostCmdDto.getBody(), jobPost.getBody());
        assertEquals(JobOpening.OPEN, storeMgmt.getJobOpening());
    }

    @Test
    public void updateJobPost() throws Exception {
        ///given
        JobPostCmdDto jobPostCmdDto = new JobPostCmdDto("sample", "sample_body");
        Long postId = jobPostService.create(storeMgmt.getId(), jobPostCmdDto);
        JobPostCmdDto updateDto = new JobPostCmdDto("modified", "modified_body", JobOpening.CLOSE);
        //when
        JobPostCmdDto update = jobPostService.update(postId, storeMgmt.getId(), updateDto);
        //then
        assertEquals(updateDto.getTitle(), update.getTitle());
        assertEquals(updateDto.getBody(), update.getBody());
        assertEquals(updateDto.getJobOpening(), update.getJobOpening());
        assertEquals(JobOpening.CLOSE, storeMgmt.getJobOpening());
    }

    @Test
    public void deleteJobPost() throws Exception {
        //given
        JobPostCmdDto jobPostCmdDto = new JobPostCmdDto("sample", "sample_body");
        Long postId = jobPostService.create(storeMgmt.getId(), jobPostCmdDto);
        //when
        jobPostService.delete(postId, storeMgmt.getId());
        //then
        assertEquals(0, jobPostRepository.count());
    }
}