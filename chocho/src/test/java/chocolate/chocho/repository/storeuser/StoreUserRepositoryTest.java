package chocolate.chocho.repository.storeuser;

import chocolate.chocho.entity.StoreUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreUserRepositoryTest {

    @Autowired
    StoreUserRepository storeUserRepository;

    @Autowired
    private TestEntityManager tm;

    @Test
    public void createStoreUser() throws Exception {
        //given
        StoreUser storeUser = new StoreUser("testUser");
        tm.persist(storeUser);
        //when
        StoreUser findStoreUser = storeUserRepository.findById(storeUser.getId()).orElseThrow();
        //then
        assertThat(findStoreUser.getId()).isEqualTo(storeUser.getId());
        assertThat(findStoreUser.getName()).isEqualTo(storeUser.getName());
    }



}