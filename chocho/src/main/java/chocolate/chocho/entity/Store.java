package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "store_id", columnDefinition = "BINARY(16)")
    private UUID        id;
    private String      name;
    private Address     address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employer_id")
    private Employer    employer;
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    public Store(String name, Address address, Employer employer) {
        this.name = name;
        this.address = address;
        this.employer = employer;
    }

    public Store(UUID id, String name, Address address, Employer employer) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.employer = employer;
    }

    public void update(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    //연관 관계 메서드
    public void createJobOpening() {
        this.jobOpening = new JobOpening(this);
    }
}
