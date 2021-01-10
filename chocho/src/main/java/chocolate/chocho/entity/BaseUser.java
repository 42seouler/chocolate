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
public class BaseUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2k")
    @Column(columnDefinition = "BINARY(16)")
    private UUID    id;
    private String  name;

    public BaseUser(String name) {
        this.name = name;
    }

    public BaseUser(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
