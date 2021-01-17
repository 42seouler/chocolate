package chocolate.chocho.service.jobpost.command;

import chocolate.chocho.dto.JobPostCmdDto;
import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.JobPost;
import chocolate.chocho.entity.Store;
import chocolate.chocho.repository.EmployerRepository;
import chocolate.chocho.repository.JobPostRepository;
import chocolate.chocho.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JobPostServiceImplTest {

    @Autowired
    EmployerRepository  employerRepository;
    @Autowired
    StoreRepository     storeRepository;
    @Autowired
    JobPostRepository   jobPostRepository;
    @Autowired
    JobPostServiceImpl  jobPostService;
    Store               store;

    @BeforeEach
    void init() {
        Address employerAddress = createAddress("city", "street", "zipcode");
        Employer employer = employerRepository.save(new Employer("seouler", employerAddress));
        Address address = createAddress("seoul", "songpa-dong", "4242");
        store =  storeRepository.save(new Store("starbucks", address, employer));
    }

    @Test
    @Rollback(value = false)
    public void createJobPost() throws Exception {
        //given
        JobPostCmdDto jobPostCmdDto = new JobPostCmdDto("42seoul 이야기", "42로 오세요");
        //when
        Long jobPostId = jobPostService.create(store.getId(), jobPostCmdDto);
        //then
        JobPost findJobPost = jobPostRepository.findById(jobPostId).orElseThrow();
        assertEquals(jobPostCmdDto.getTitle(), findJobPost.getTitle());
        assertEquals(jobPostCmdDto.getBody(), findJobPost.getBody());
    }

    private Address createAddress(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

}