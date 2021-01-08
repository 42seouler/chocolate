package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID    id;
    private String  name;
    @Embedded
    private Address address;

    public BaseUser(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
