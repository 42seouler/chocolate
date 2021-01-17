package chocolate.chocho.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID    id;
    private String  name;
    private Address address;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
        this.userRole = UserRole.EMPLOLYEE;
    }

    public User(UUID id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.userRole = UserRole.EMPLOLYEE;
    }

    public void update(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
