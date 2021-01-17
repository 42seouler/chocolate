package chocolate.chocho.entity;

import chocolate.chocho.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class StoreMgmt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long                id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store       store;
    @Enumerated(EnumType.STRING)
    private JobOpening  jobOpening;
    private int         activatedPost;

    public StoreMgmt(Store store) {
        this.store = store;
        this.jobOpening = JobOpening.CLOSE;
        this.activatedPost = 0;
    }

    public void jobOpeningChangeState(JobOpening jobOpening) {
        if (jobOpening.equals(JobOpening.OPEN))
            this.activatedPost += 1;
        if (jobOpening.equals(JobOpening.CLOSE))
           this.activatedPost -= 1;
        jobOpeningChange();
    }

    private void jobOpeningChange() {
        if (this.activatedPost == 0) {
            this.jobOpening = JobOpening.CLOSE;
        }
        if (this.activatedPost != 0)
            this.jobOpening = JobOpening.OPEN;
    }
}
