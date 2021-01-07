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

    public Store(StoreUser storeUser) {
        this.storeUser = storeUser;
    }
}
