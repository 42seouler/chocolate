package chocolate.chocho.repository;

import chocolate.chocho.entity.JobOpening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobOpeningRepositoryTest {

    @Autowired
    JobOpeningRepository jobOpeningRepository;

    @Autowired
    TestEntityManager tm;

    @Test
    public void createJobOpening() throws Exception {
        //given
        JobOpening jobOpening = new JobOpening();
        tm.persist(jobOpening);
        //when
        JobOpening findJobOpening = jobOpeningRepository.findById(jobOpening.getId()).orElseThrow();
        //then
        Assertions.assertEquals(jobOpening.getId(), findJobOpening.getId());
    }
}