package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private Address address;

    public User(Long id, Address address) {
        this.id = id;
        this.address = address;
    }

    public User(Address address) {
        this.address = address;
    }
}
