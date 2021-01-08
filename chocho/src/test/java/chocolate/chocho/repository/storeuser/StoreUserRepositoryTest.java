package chocolate.chocho.repository.storeuser;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.store.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreUserRepositoryTest {

    @Autowired
    StoreUserRepository storeUserRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    private TestEntityManager tm;

    @Test
    public void createStoreUser() throws Exception {
        //given
        Address address = new Address("seoul", "song-pa", "145-45");
        StoreUser storeUser = new StoreUser("testUser", address);
        tm.persist(storeUser);
        //when
        StoreUser findStoreUser = storeUserRepository.findById(storeUser.getId()).orElseThrow();
        //then
        assertThat(findStoreUser.getId()).isEqualTo(storeUser.getId());
        assertThat(findStoreUser.getName()).isEqualTo(storeUser.getName());
        assertThat(findStoreUser.getAddress().getCity()).isEqualTo(address.getCity());
        assertThat(findStoreUser.getAddress().getZipcode()).isEqualTo(address.getZipcode());
        assertThat(findStoreUser.getAddress().getStreet()).isEqualTo(address.getStreet());
    }

    @Test
    public void checkStore() throws Exception {
        //given
        Address storeAddress = new Address("seoul", "gaepo-dong", "4242-42");
        Address userAddress = new Address("seoul", "songpa-dong", "4242-42");
        StoreUser storeUser = new StoreUser("nakim", userAddress);
        tm.persist(storeUser);
        Store store = new Store(storeUser, "starbucks", storeAddress);
        tm.persist(store);
        storeUser.registerStore(store);
        //when
        StoreUser findStoreUser = storeUserRepository.findById(storeUser.getId()).orElseThrow();
        Store findStore = storeRepository.findById(store.getId()).orElseThrow();
        //thenkj
        // 유저 스토어 검증
        assertThat(findStoreUser.getStore().getId()).isEqualTo(findStore.getId());
    }
}
