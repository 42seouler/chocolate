package chocolate.chocho.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.SecureRandom;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;
    private String  name;
    private Address address;

    public void update(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Store(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
