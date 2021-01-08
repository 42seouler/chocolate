package chocolate.chocho.repository.storeuser;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.store.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
class StoreUserRepositoryTest {

    @Autowired
    StoreUserRepository storeUserRepository;

    @Autowired
    StoreRepository storeRepository;

    @Test
    public void createStoreUser() throws Exception {
        //given
        Address address = new Address("seoul", "song-pa", "145-45");
        StoreUser storeUser = new StoreUser("testUser", address);
        storeUserRepository.save(storeUser);
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
    @Rollback(value = false)
    public void checkStore() throws Exception {
        //given
        Address storeAddress = new Address("seoul", "gaepo-dong", "4242-42");
        Address userAddress = new Address("seoul", "songpa-dong", "4242-42");
        StoreUser storeUser = new StoreUser("nakim", userAddress);
        storeUserRepository.save(storeUser);
        Store store = new Store(storeUser, "starbucks", storeAddress);
        storeRepository.save(store);
        storeUser.registerStore(store);
        //when
        StoreUser findStoreUser = storeUserRepository.findById(storeUser.getId()).orElseThrow();
        Store findStore = storeRepository.findById(store.getId()).orElseThrow();
        //then
        // 유저 스토어 검증
        assertThat(findStoreUser.getStore().getId()).isEqualTo(findStore.getId());
    }
}
