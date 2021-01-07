package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private Long id;
    @OneToOne(mappedBy = "store", fetch = FetchType.LAZY)
    private StoreUser storeUser;
    private String name;
    @Embedded
    private Address address;

    public Store(StoreUser storeUser, String name, Address address) {
        this.storeUser = storeUser;
        this.name = name;
        this.address = address;
    }
}
