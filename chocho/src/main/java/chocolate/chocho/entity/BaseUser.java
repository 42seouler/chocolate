package chocolate.chocho.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BaseUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID    id;
    private String  name;
    private Address address;

    public BaseUser(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public BaseUser(UUID id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
