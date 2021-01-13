package chocolate.chocho.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Employer extends BaseUser {

    public Employer(String name, Address address) {
        super(name, address);
    }

    public Employer(UUID id, String name, Address address) {
        super(id, name, address);
    }
}
