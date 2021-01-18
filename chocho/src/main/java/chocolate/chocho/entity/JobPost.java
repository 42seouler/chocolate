package chocolate.chocho.entity;

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
    @JoinColumn(name = "store_mgmt_id")
    private StoreMgmt   storeMgmt;
    private String  title;
    private String  body;
    @Enumerated(EnumType.STRING)
    private JobOpening jobOpening;

    public JobPost(StoreMgmt storeMgmt, String title, String body) {
        this.storeMgmt = storeMgmt;
        this.title = title;
        this.body = body;
        this.jobOpening = JobOpening.OPEN;
    }

    public void update(String title, String body, JobOpening jobOpening) {
        this.title = title;
        this.body = body;
        this.jobOpening = jobOpening;
    }
}