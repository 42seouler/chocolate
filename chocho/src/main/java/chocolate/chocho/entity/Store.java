package chocolate.chocho.entity;

import chocolate.chocho.entity.user.User;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long        id;
    private String      name;
    private Address     address;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User        user;

    public void update(String name, Address address) {
       this.name = name;
       this.address = address;
    }

    public Store(String name, Address address, User user) {
        this.name = name;
        this.address = address;
        this.user = user;
    }
}
