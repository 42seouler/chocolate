package chocolate.chocho.repository.store;

import chocolate.chocho.entity.Address;
import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreUser;
import chocolate.chocho.repository.storeuser.StoreUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback(value = false)
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreUserRepository storeUserRepository;

    @Test
    @Rollback(value = false)
    public void createStore() throws Exception {
        //given
        Address storeAddress = new Address("seoul", "songpa-dong", "4242-42");
        Address userAddress = new Address("seoul", "gaepo-dong", "4242-42");
        StoreUser storeUser = new StoreUser("42seouler", userAddress);
        storeUserRepository.save(storeUser);
        Store store = new Store(storeUser,"store", storeAddress);
        storeRepository.save(store);
        //when
        Store findStore = storeRepository.findById(store.getId()).orElseThrow();
        StoreUser findStoreUser = storeUserRepository.findById(storeUser.getId()).orElseThrow();
        //then
        // 스토어의 유저 검증
        assertThat(findStore.getStoreUser()).isEqualTo(findStoreUser);
        assertThat(findStore.getStoreUser().getId()).isEqualTo(findStoreUser.getId());
        // 스토어 아이디 검증
        assertThat(findStore.getId()).isEqualTo(store.getId());
        // 스토어 이름 검증
        assertThat(findStore.getName()).isEqualTo(store.getName());
        // 스토어 주소 검증
        assertThat(findStore.getAddress().getStreet()).isEqualTo(store.getAddress().getStreet());
        assertThat(findStore.getAddress().getZipcode()).isEqualTo(store.getAddress().getZipcode());
        assertThat(findStore.getAddress().getCity()).isEqualTo(store.getAddress().getCity());
    }
}
