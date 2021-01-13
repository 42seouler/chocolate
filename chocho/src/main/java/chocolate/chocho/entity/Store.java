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
    @Column(columnDefinition = "BINARY(16)")
    private UUID        id;
    private String      name;
    private Address     address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer    employer;

    public void update(String name, Address address) {
       this.name = name;
       this.address = address;
    }

    public Store(String name, Address address, Employer employer) {
        this.name = name;
        this.address = address;
        this.employer = employer;
    }
}
