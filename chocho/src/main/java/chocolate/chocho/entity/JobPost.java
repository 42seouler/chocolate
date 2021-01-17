package chocolate.chocho.entity;

import chocolate.chocho.service.store.query.StoreQueryServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store   store;
    private String  title;
    private String  body;
    @Enumerated(EnumType.STRING)
    private JobOpening jobOpening;

    public JobPost(Store store, String title, String body) {
        this.store = store;
        this.title = title;
        this.body = body;
        this.jobOpening = JobOpening.OPEN;
    }
}