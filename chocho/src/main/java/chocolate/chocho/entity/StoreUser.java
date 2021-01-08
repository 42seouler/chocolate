package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class StoreUser extends BaseUser {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public StoreUser(String name, Address address) {
        super(name, address);
    }

    public StoreUser(String name, Address address, Store store) {
        super(name, address);
        this.store = store;
    }

    public void registerStore(Store store) {
        this.store = store;
    }
}