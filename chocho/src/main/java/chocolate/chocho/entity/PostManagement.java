package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@NoArgsConstructor
public class PostManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store   store;

    private int activated;
    private int deactivated;

    public PostManagement(Store store) {
        this.store = store;
        this.activated = 0;
        this.deactivated = 0;
    }
}
