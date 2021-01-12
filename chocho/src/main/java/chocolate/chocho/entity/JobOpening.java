package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id", "store"})
public class JobOpening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store   store;
    @Enumerated(EnumType.STRING)
    private JobOpeningStatus status;

    public JobOpening(Store store) {
        this.store = store;
    }

    public JobOpening(Long id, Store store) {
        this.id = id;
        this.store = store;
    }
}
